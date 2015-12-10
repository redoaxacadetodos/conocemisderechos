package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.apache.commons.io.FilenameUtils
import org.springframework.dao.DataIntegrityViolationException

@Secured(["hasRole('ROLE_ADMIN')"])
class LogotipoController {

    def dataTablesService
	def archivoService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def list(Integer max) {
		
	}
	
	def dataTablesList = {
		def query = "from cat_logotipo"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"log_id as id",
			"log_titulo as log_titulo",
			"log_orden as log_orden"
			],
			[
			"log_titulo",
			"log_orden"
			],
			[
			"id",
			"log_titulo",
			"log_orden"
			],2,"text") as JSON
	}
	
	def create() {
		[logotipoInstance: new Logotipo(params)]
	}

	def save() {
		def logotipoInstance = new Logotipo()
		
		logotipoInstance.titulo = params.titulo
		logotipoInstance.orden = 0
		
		def file = request.getFile('ruta')
		def ruta = grailsApplication.config.mx.indesti.cednna.valores.directorioLogotipos
		def nombreArchivo = guardarArchivo(ruta, file)
		
		if(nombreArchivo.length()>0){
			logotipoInstance.ruta = nombreArchivo
			if(logotipoInstance.save(flush:true))
				flash.message = "Datos guardados correctamente"
			else{
				flash.message = "Error al guardar el archivo"
				render(view: "create", model: [logotipoInstance: logotipoInstance])
				return
			}
		}else{
			flash.message = "Error al guardar el archivo"
			render(view: "create", model: [logotipoInstance: logotipoInstance])
			return
		}
		
		redirect(action: "show", id: logotipoInstance.id)
	}

	def show(Long id) {
		def logotipoInstance = Logotipo.get(id)
		if (!logotipoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), id])
			redirect(action: "list")
			return
		}
		
		[logotipoInstance: logotipoInstance]
	}
	
	def ordenar(){
		def logotipos = Logotipo.getAll().sort{it.orden}
		[logotipos:logotipos]
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
		def logotipoInstance = Logotipo.get(id)
		if (!logotipoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), id])
			redirect(action: "list")
			return
		}

		[logotipoInstance: logotipoInstance]
	}

	def update(Long id, Long version) {
		def logotipoInstance = Logotipo.get(id)
		if (!logotipoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), id])
			redirect(action: "list")
			return
		}

		logotipoInstance.titulo = params.titulo
		def file = request.getFile('ruta')
		def ruta = grailsApplication.config.mx.indesti.cednna.valores.directorioLogotipos 
		def nombreArchivo = ""
		
		if(file?.isEmpty() && params.tieneDocumento.equals("true")){
			if(logotipoInstance.save(flush:true))
				flash.message = "Datos guardados correctamente"
			else{
				flash.message = "Error al guardar el archivo"
				render(view: "create", model: [logotipoInstance: logotipoInstance])
				return
			}
		}else{
			nombreArchivo = guardarArchivo(ruta, file)
			if(nombreArchivo.length()>0){
				logotipoInstance.ruta = nombreArchivo
				if(logotipoInstance.save(flush:true))
					flash.message = "Datos guardados correctamente"
				else{
					flash.message = "Error al guardar el archivo"
					render(view: "create", model: [logotipoInstance: logotipoInstance])
					return
				}
			}else{
				flash.message = "Error al guardar el archivo"
				render(view: "create", model: [logotipoInstance: logotipoInstance])
				return
			}
		}

		flash.message = message(code: 'default.updated.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), logotipoInstance.id])
		redirect(action: "show", id: logotipoInstance.id)
	}

	def delete(Long id) {
		def logotipoInstance = Logotipo.get(id)
		if (!logotipoInstance) {
			flash.message = message(code: 'default.not.found.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), id])
			redirect(action: "list")
			return
		}

		try {
			logotipoInstance.delete(flush: true)
			flash.message = message(code: 'default.deleted.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), id])
			redirect(action: "list")
		}
		catch (DataIntegrityViolationException e) {
			flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'logotipo.label', default: 'Logotipo'), id])
			redirect(action: "show", id: id)
		}
	}
	
	def ordenarLogotipos(){
		def	logotipos = params.logotipos
		
		if(logotipos){
			def tamanio = logotipos.size()
			logotipos.eachWithIndex{ logotipo, index ->
				try{
					def logotipoInstance = Logotipo.get(logotipo.toLong())
					logotipoInstance.orden = index
					logotipoInstance.save(flush:true)
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
