package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.apache.commons.io.FilenameUtils
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasRole('ROLE_ADMIN')"])
class InfografiaController {

    def dataTablesService
	def archivoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def list(Integer max) {
		
	}
	
	def dataTablesList = {
		def query = "from cat_infografia"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"inf_id as id",
			"inf_titulo as inf_titulo",
			"inf_orden as inf_orden"
			],
			[
			"inf_titulo",
			"inf_orden"
			],
			[
			"id",
			"inf_titulo",
			"inf_orden"
			],2,"text") as JSON
	}
	
	def create() {
		[infografiaInstance: new Infografia(params)]
	}

	def save() {
		def infografiaInstance = new Infografia()
		
		infografiaInstance.titulo = params.titulo
		infografiaInstance.orden = 0
		
		def file = request.getFile('ruta')
		def ruta = grailsApplication.config.mx.indesti.cednna.valores.directorioInfografias
		def nombreArchivo = guardarArchivo(ruta, file)
		
		if(nombreArchivo.length()>0){
			infografiaInstance.ruta = nombreArchivo
			if(infografiaInstance.save(flush:true))
				flash.message = "Datos guardados correctamente"
			else{
				flash.message = "Error al guardar el archivo"
				render(view: "create", model: [infografiaInstance: infografiaInstance])
				return
			}
		}else{
			flash.message = "Error al guardar el archivo"
			render(view: "create", model: [infografiaInstance: infografiaInstance])
			return
		}
		
		redirect(action: "show", id: infografiaInstance.id)
	}

	def show(Long id) {
		def infografiaInstance = Infografia.get(id)
		if (!infografiaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'infografia.label', default: 'Infografía'), id])
			redirect(action: "list")
			return
		}
		
		[infografiaInstance: infografiaInstance]
	}
	
	def ordenar(){
		def infografias = Infografia.getAll().sort{it.orden}
		[infografias:infografias]
	}
	
	def renderImage(Long id){
		def image
		try {
			image = archivoService.cargarImagen(params.ruta)
			if (image!=null){
				String ext = FilenameUtils.getExtension(params.ruta)
				render archivoService.mostrarImagen(image, ext, response.getOutputStream())
				return
			}else{
				throw new Exception()
			}
		}
		catch(Exception ex){
			ex.printStackTrace()
		}
	}

	def edit(Long id) {
		def infografiaInstance = Infografia.get(id)
		if (!infografiaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'infografia.label', default: 'Infografía'), id])
			redirect(action: "list")
			return
		}

		[infografiaInstance: infografiaInstance]
	}

	def update(Long id, Long version) {
		def infografiaInstance = Infografia.get(id)
		if (!infografiaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'infografia.label', default: 'Infografía'), id])
			redirect(action: "list")
			return
		}

		infografiaInstance.titulo = params.titulo
		def file = request.getFile('ruta')
		def ruta = grailsApplication.config.mx.indesti.cednna.valores.directorioInfografias 
		def nombreArchivo = ""
		
		if(file?.isEmpty() && params.tieneDocumento.equals("true")){
			if(infografiaInstance.save(flush:true))
				flash.message = "Datos guardados correctamente"
			else{
				flash.message = "Error al guardar el archivo"
				render(view: "create", model: [infografiaInstance: infografiaInstance])
				return
			}
		}else{
			nombreArchivo = guardarArchivo(ruta, file)
			if(nombreArchivo.length()>0){
				infografiaInstance.ruta = nombreArchivo
				if(infografiaInstance.save(flush:true))
					flash.message = "Datos guardados correctamente"
				else{
					flash.message = "Error al guardar el archivo"
					render(view: "create", model: [infografiaInstance: infografiaInstance])
					return
				}
			}else{
				flash.message = "Error al guardar el archivo"
				render(view: "create", model: [infografiaInstance: infografiaInstance])
				return
			}
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'infografia.label', default: 'Infografía'), infografiaInstance.id])
		redirect(action: "show", id: infografiaInstance.id)
	}

	def delete(Long id) {
		def infografiaInstance = Infografia.get(id)
		if (!infografiaInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'infografia.label', default: 'Infografía'), id])
			redirect(action: "list")
			return
		}

		try {
			infografiaInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'infografia.label', default: 'Infografía'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'infografia.label', default: 'Infografía'), id])
			redirect(action: "show", id: id)
		}
	}
	
	def ordenarInfografias(){
		def	infografias = params.infografias
		
		if(infografias){
			def tamanio = infografias.size()
			infografias.eachWithIndex{ infografia, index ->
				try{
					def infografiaInstance = Infografia.get(infografia.toLong())
					infografiaInstance.orden = index
					infografiaInstance.save(flush:true)
					flash.message = "Orden guardado correctamente"
				}catch(Exception e){
					e.printStackTrace()
					flash.message = "Orden guardado correctamente"
				}
			}
		}
		redirect (action:'list')
	}
	
	String guardarArchivo(String ruta, def file){
		String name = ""
		String direccion = ""
		try{
			if(!file?.isEmpty()){
				def storagePath = ruta
				name = file.originalFilename.toString().replace(' ', '-')
				println 'name:'+name
				
				//Crea la ruta de almacenamiento si no existe
				def storagePathDirectory = new File(storagePath)
				if(!storagePathDirectory.exists()){
					if(storagePathDirectory.mkdirs()){
						println "Directorio Creado correctamente"
					}else{
						println "Error al crear el directorio"
					}
				}
				//Almacenar el archivo
				if(!file.isEmpty()){
					int i = 0
					def fileAux = new File("${storagePath}${name}")
					println '.fileAux.exists():'+fileAux.exists()
					direccion = "${storagePath}${name}"
					while(fileAux.exists()){
						i++
						fileAux = new File("${storagePath}"+i+"${name}")
						direccion = "${storagePath}"+i+"${name}"
					}
					file.transferTo(fileAux)
				}else{
					println "Archivo ${file.inspect()} estaba vacio"
				}
			}
		}catch(Exception e){
			direccion = ""
			e.printStackTrace()
		}
		return direccion
	}
}
