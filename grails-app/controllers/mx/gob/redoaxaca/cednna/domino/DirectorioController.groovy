package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class DirectorioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	
	def dataTablesService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [directorioInstanceList: Directorio.list(params), directorioInstanceTotal: Directorio.count()]
    }
	
	def datosdirectorio = {
		def query="  from cat_directorio "
		
		render dataTablesService.datosParaTablaQuery('from cat_directorio',params,
		[
		'dir_id',
		'dir_nombre',
		'dir_cargo',
		'dir_dependencia',
		'dir_correo',
		'dir_telefono',
		'dir_website',
		],
		[
		'dir_id',
		'dir_nombre',
		'dir_cargo',
		'dir_dependencia',
		'dir_correo',
		'dir_telefono',
		'dir_website',
		],1,"text") as JSON

	}

    def create() {
        [directorioInstance: new Directorio(params)]
    }

    def save() {
        def directorioInstance = new Directorio(params)
        if (!directorioInstance.save(flush: true)) {
            render(view: "create", model: [directorioInstance: directorioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'directorio.label', default: 'Directorio'), directorioInstance.id])
        redirect(action: "show", id: directorioInstance.id)
    }

    def show(Long id) {
        def directorioInstance = Directorio.get(id)
        if (!directorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'directorio.label', default: 'Directorio'), id])
            redirect(action: "list")
            return
        }

        [directorioInstance: directorioInstance]
    }

    def edit(Long id) {
        def directorioInstance = Directorio.get(id)
        if (!directorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'directorio.label', default: 'Directorio'), id])
            redirect(action: "list")
            return
        }

        [directorioInstance: directorioInstance]
    }

    def update(Long id, Long version) {
        def directorioInstance = Directorio.get(id)
        if (!directorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'directorio.label', default: 'Directorio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (directorioInstance.version > version) {
                directorioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'directorio.label', default: 'Directorio')] as Object[],
                          "Another user has updated this Directorio while you were editing")
                render(view: "edit", model: [directorioInstance: directorioInstance])
                return
            }
        }

        directorioInstance.properties = params

        if (!directorioInstance.save(flush: true)) {
            render(view: "edit", model: [directorioInstance: directorioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'directorio.label', default: 'Directorio'), directorioInstance.id])
        redirect(action: "show", id: directorioInstance.id)
    }

    def delete(Long id) {
        def directorioInstance = Directorio.get(id)
        if (!directorioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'directorio.label', default: 'Directorio'), id])
            redirect(action: "list")
            return
        }

        try {
            directorioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'directorio.label', default: 'Directorio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'directorio.label', default: 'Directorio'), id])
            redirect(action: "show", id: id)
        }
    }
}
