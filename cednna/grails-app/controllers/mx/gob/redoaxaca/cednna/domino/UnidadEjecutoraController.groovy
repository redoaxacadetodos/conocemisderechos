package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured(["hasRole('ROLE_ADMIN')"])
class UnidadEjecutoraController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [unidadEjecutoraInstanceList: UnidadEjecutora.list(params), unidadEjecutoraInstanceTotal: UnidadEjecutora.count()]
    }
	
	def dataTablesList = {
		def query = "from cat_unidad_ejecutora"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"cue_id as id",
			"cue_descripcion as descripcion"
			],
			[
			"cue_descripcion"
			],
			[
			"id",
			"descripcion"
			],1,"text") as JSON
	}

    def create() {
        [unidadEjecutoraInstance: new UnidadEjecutora(params)]
    }

    def save() {
        def unidadEjecutoraInstance = new UnidadEjecutora(params)
        if (!unidadEjecutoraInstance.save(flush: true)) {
            render(view: "create", model: [unidadEjecutoraInstance: unidadEjecutoraInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), unidadEjecutoraInstance.id])
        redirect(action: "show", id: unidadEjecutoraInstance.id)
    }

    def show(Long id) {
        def unidadEjecutoraInstance = UnidadEjecutora.get(id)
        if (!unidadEjecutoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), id])
            redirect(action: "list")
            return
        }

        [unidadEjecutoraInstance: unidadEjecutoraInstance]
    }

    def edit(Long id) {
        def unidadEjecutoraInstance = UnidadEjecutora.get(id)
        if (!unidadEjecutoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), id])
            redirect(action: "list")
            return
        }

        [unidadEjecutoraInstance: unidadEjecutoraInstance]
    }

    def update(Long id, Long version) {
        def unidadEjecutoraInstance = UnidadEjecutora.get(id)
        if (!unidadEjecutoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (unidadEjecutoraInstance.version > version) {
                unidadEjecutoraInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora')] as Object[],
                          "Another user has updated this UnidadEjecutora while you were editing")
                render(view: "edit", model: [unidadEjecutoraInstance: unidadEjecutoraInstance])
                return
            }
        }

        unidadEjecutoraInstance.properties = params

        if (!unidadEjecutoraInstance.save(flush: true)) {
            render(view: "edit", model: [unidadEjecutoraInstance: unidadEjecutoraInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), unidadEjecutoraInstance.id])
        redirect(action: "show", id: unidadEjecutoraInstance.id)
    }

    def delete(Long id) {
        def unidadEjecutoraInstance = UnidadEjecutora.get(id)
        if (!unidadEjecutoraInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), id])
            redirect(action: "list")
            return
        }

        try {
            unidadEjecutoraInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora'), id])
            redirect(action: "show", id: id)
        }
    }
}
