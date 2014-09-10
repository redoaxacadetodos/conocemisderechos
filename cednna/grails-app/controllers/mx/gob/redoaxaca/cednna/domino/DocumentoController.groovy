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
		def query = "from cat_documento"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"doc_id as id",
			"doc_titulo as titulo",
			"doc_nivel as nivel",
			"doc_tipo as tipo",
			"doc_url as url"
			],
			[
			"doc_tipo",
			"doc_titulo",
			"doc_nivel"
			],
			[
			"id",
			"titulo",
			"nivel",
			"tipo",
			"url"
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
        def documentoInstance = new Documento(params)
		
		try{
			def storagePath = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads + params.tipo + '/' + params.nivel
			def file = request.getFile('url')
			def name = file.originalFilename.toString().replace(' ', '-')
			
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
			
			documentoInstance.url = name
		}catch(Exception e){
			e.printStackTrace()
		}
		
//		documentoInstance.nivel = Nivel.get(params.nivel.toLong())
//		println 'nivel:'+documentoInstance.nivel
        if (!documentoInstance.save(flush: true)) {
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

        documentoInstance.properties = params
		
		try{
			def storagePath = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads + params.tipo + '/' + params.nivel
			def file = request.getFile('url')
			def name = file.originalFilename.toString().replace(' ', '-')
			
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
			
			documentoInstance.url = name
		}catch(Exception e){
			e.printStackTrace()
		}

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
			response.setContentType("application/octet-stream")
			response.setHeader("Content-disposition", "attachment;filename=${archivo.getName()}")
			response.outputStream << archivo.newInputStream()
		} catch(Exception ex){
			response.sendError(500)
		}
	}
}
