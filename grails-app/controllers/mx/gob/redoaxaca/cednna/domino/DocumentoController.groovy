package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException
import org.springframework.web.multipart.MultipartFile


@Secured( ['ROLE_ADMIN'])
class DocumentoController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def dataTablesList = {
		def query = "from cat_documento left join cat_tipo_eje on (cat_documento.tipo_documento_id = cat_tipo_eje.eje_id) left join cat_nivel on (cat_documento.doc_nivel = cat_nivel.id)"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"doc_id as id",
			"doc_titulo as titulo",
			"niv_nivel as nivel",
			"eje_tipo as tipo",
			"doc_url as url",
			"tipo_documento_id as tipoid",
			"doc_nivel as nivelid",
			],
			[
			"eje_tipo",
			"doc_titulo",
			"niv_nivel"
			],
			[
			"id",
			"titulo",
			"nivel",
			"tipo",
			"url",
			"tipoid",
			"nivelid"
			],1,"text") as JSON
	}

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [documentoInstanceList: Documento.list(params), documentoInstanceTotal: Documento.count(), directorio:grailsApplication.config.mx.indesti.cednna.valores.directoriouploads]
    }

    def create() {
        [documentoInstance: new Documento(params)]
    }

    def save() {
        def documentoInstance = new Documento()
		
		def file = request.getFile('url')
		def ruta = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads + params.tipoDocumento.id + '/' + params.nivel.id
		def nombreArchivo = guardarArchivo(ruta, file)
		
		documentoInstance.url = nombreArchivo
		documentoInstance.titulo = params.titulo
		documentoInstance.nivel = Nivel.get(params.nivel.id.toLong())
		documentoInstance.tipoDocumento = TipoEje.get(params.tipoDocumento.id.toLong())
		
        if (!documentoInstance.save(flush: true)) {
			println 'error!!'
            render(view: "create", model: [documentoInstance: documentoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])
        redirect(action: "show", id: documentoInstance.id)
    }
	
    def show(Long id) {
        def documentoInstance = Documento.get(id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), id])
            redirect(action: "list")
            return
        }

        [documentoInstance: documentoInstance]
    }

    def edit(Long id) {
        def documentoInstance = Documento.get(id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), id])
            redirect(action: "list")
            return
        }

        [documentoInstance: documentoInstance]
    }

    def update(Long id, Long version) {
        def documentoInstance = Documento.get(id)
		
		
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (documentoInstance.version > version) {
                documentoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'documento.label', default: 'Documento')] as Object[],
                          "Another user has updated this Documento while you were editing")
                render(view: "edit", model: [documentoInstance: documentoInstance])
                return
            }
        }

        documentoInstance.titulo = params.titulo
		documentoInstance.nivel = Nivel.get(params.nivel.id.toLong())
		documentoInstance.tipoDocumento = TipoEje.get(params.tipoDocumento.id.toLong())
		
		def file = request.getFile('url')
		def ruta = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads + params.tipoDocumento.id + '/' + params.nivel.id
		def nombreArchivo = guardarArchivo(ruta, file)
		documentoInstance.url = nombreArchivo

        if (!documentoInstance.save(flush: true)) {
            render(view: "edit", model: [documentoInstance: documentoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'documento.label', default: 'Documento'), documentoInstance.id])
        redirect(action: "show", id: documentoInstance.id)
    }

    def delete(Long id) {
        def documentoInstance = Documento.get(id)
        if (!documentoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'documento.label', default: 'Documento'), id])
            redirect(action: "list")
            return
        }

        try {
            documentoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'documento.label', default: 'Documento'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def descargarDocumento(){
		try {
			def path = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads + params.tipo + "/" + params.nivel + "/" + params.documento
			def archivo = new File (path)
			response.setContentType("application/octet-stream; charset=UTF-8")
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition", "attachment;filename*=UTF-8''${URLEncoder.encode(archivo.getName(), 'UTF-8')}")
			response.outputStream << archivo.newInputStream()
		} catch(Exception ex){
			println params
			response.sendError(500)
		}
	}
	
	def actualizarNivel(){
		def tipoEjeInstance = TipoEje.get(params.tipo.toInteger())
		def niveles = Nivel.findAllByTipoNivel(tipoEjeInstance)
		render template:'niveles', model:[niveles:niveles, nivelSeleccionado:params.nivel]
	}
	
	def manual(){
		
	}
	
	def editarManual(Long id){
		def manual
		if(id==1)
			manual = Valor.findByKey("rutaManual")
		else
			manual = Valor.findByKey("rutaManualPublico")
		[id:id, manual:manual]	
	}
	
	def guardarManual(){
		def id = params.id.toInteger()
		def manual
		if(id==1)
			manual = Valor.findByKey("rutaManual")
		else if(id==2)
			manual = Valor.findByKey("rutaManualPublico")
		else
			manual = Valor.findByKey("rutaCatalogoIndicadores")
		
		if(manual){
			manual.valor = params.ruta
			
			def file = request.getFile('url')
			def ruta = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads
			def nombreArchivo = guardarArchivo(ruta, file)
			if(nombreArchivo.length()>0){
				manual.valor = ruta + nombreArchivo
				if(manual.save(flush:true))
					flash.message = "Datos guardados correctamente"
				else
					flash.message = "Error al guardar el manual"
			}else{
				flash.message = "Error al guardar el manual, no se pudo obtener el archivo"
			}
		}else{
			flash.message = "Error al guardar el manual"
		}
		
		redirect (action:"manual")
	}
	
	String guardarArchivo(String ruta, def file){
		String name = ""
		try{
			if(!file.isEmpty()){
				println 'file:'+file.isEmpty()
				def storagePath = ruta
				name = file.originalFilename.toString().replace(' ', '-')
				
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
					file.transferTo(new File("${storagePath}/${name}"))
					println "Archivo guardado ${storagePath}/${name}"
				}else{
					println "Archivo ${file.inspect()} estaba vacio"
				}
			}
		}catch(Exception e){
			name = ""
			e.printStackTrace()
		}
		return name
	}
}
