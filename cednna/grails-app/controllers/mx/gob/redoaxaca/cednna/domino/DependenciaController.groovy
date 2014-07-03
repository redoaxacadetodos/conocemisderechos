package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured(["hasRole('ROLE_ADMIN')"])
class DependenciaController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [dependenciaInstanceList: Dependencia.list(params), dependenciaInstanceTotal: Dependencia.count()]
    }
	
	def dataTablesList = {
		def query = "from cat_dependencia"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"cdp_id as id",
			"cdp_clave as clave",
			"cdp_descripcion as descripcion"
			],
			[
			"cdp_clave",
			"cdp_descripcion"
			],
			[
			"id",
			"clave",
			"descripcion"
			],1,"text") as JSON
	}

    def create() {
        [dependenciaInstance: new Dependencia(params)]
    }

    def save() {
        def dependenciaInstance = new Dependencia(params)
        if (!dependenciaInstance.save(flush: true)) {
            render(view: "create", model: [dependenciaInstance: dependenciaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), dependenciaInstance.id])
        redirect(action: "show", id: dependenciaInstance.id)
    }

    def show(Long id) {
        def dependenciaInstance = Dependencia.get(id)
        if (!dependenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), id])
            redirect(action: "list")
            return
        }

        [dependenciaInstance: dependenciaInstance]
    }

    def edit(Long id) {
        def dependenciaInstance = Dependencia.get(id)
        if (!dependenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), id])
            redirect(action: "list")
            return
        }

        [dependenciaInstance: dependenciaInstance]
    }

    def update(Long id, Long version) {
        def dependenciaInstance = Dependencia.get(id)
        if (!dependenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (dependenciaInstance.version > version) {
                dependenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'dependencia.label', default: 'Dependencia')] as Object[],
                          "Another user has updated this Dependencia while you were editing")
                render(view: "edit", model: [dependenciaInstance: dependenciaInstance])
                return
            }
        }

        dependenciaInstance.properties = params

        if (!dependenciaInstance.save(flush: true)) {
            render(view: "edit", model: [dependenciaInstance: dependenciaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), dependenciaInstance.id])
        redirect(action: "show", id: dependenciaInstance.id)
    }

    def delete(Long id) {
        def dependenciaInstance = Dependencia.get(id)
        if (!dependenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), id])
            redirect(action: "list")
            return
        }

        try {
            dependenciaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'dependencia.label', default: 'Dependencia'), id])
            redirect(action: "show", id: id)
        }
    }
}
