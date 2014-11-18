package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured( ['ROLE_ADMIN'])
class PNDesarrolloController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [PNDesarrolloInstanceList: PNDesarrollo.list(params), PNDesarrolloInstanceTotal: PNDesarrollo.count()]
    }
	
	def dataTablesList = {
		def query = "from cat_pn_desarrollo"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"cnpd_id as id",
			"cnpd_descripcion as descripcion"
			],
			[
			"cnpd_descripcion"
			],
			[
			"id",
			"descripcion"
			],1,"text") as JSON
	}

    def create() {
        [PNDesarrolloInstance: new PNDesarrollo(params)]
    }

    def save() {
        def PNDesarrolloInstance = new PNDesarrollo(params)
        if (!PNDesarrolloInstance.save(flush: true)) {
            render(view: "create", model: [PNDesarrolloInstance: PNDesarrolloInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), PNDesarrolloInstance.id])
        redirect(action: "show", id: PNDesarrolloInstance.id)
    }

    def show(Long id) {
        def PNDesarrolloInstance = PNDesarrollo.get(id)
        if (!PNDesarrolloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), id])
            redirect(action: "list")
            return
        }

        [PNDesarrolloInstance: PNDesarrolloInstance]
    }

    def edit(Long id) {
        def PNDesarrolloInstance = PNDesarrollo.get(id)
        if (!PNDesarrolloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), id])
            redirect(action: "list")
            return
        }

        [PNDesarrolloInstance: PNDesarrolloInstance]
    }

    def update(Long id, Long version) {
        def PNDesarrolloInstance = PNDesarrollo.get(id)
        if (!PNDesarrolloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (PNDesarrolloInstance.version > version) {
                PNDesarrolloInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo')] as Object[],
                          "Another user has updated this PNDesarrollo while you were editing")
                render(view: "edit", model: [PNDesarrolloInstance: PNDesarrolloInstance])
                return
            }
        }

        PNDesarrolloInstance.properties = params

        if (!PNDesarrolloInstance.save(flush: true)) {
            render(view: "edit", model: [PNDesarrolloInstance: PNDesarrolloInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), PNDesarrolloInstance.id])
        redirect(action: "show", id: PNDesarrolloInstance.id)
    }

    def delete(Long id) {
        def PNDesarrolloInstance = PNDesarrollo.get(id)
        if (!PNDesarrolloInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), id])
            redirect(action: "list")
            return
        }

        try {
            PNDesarrolloInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'PNDesarrollo.label', default: 'PNDesarrollo'), id])
            redirect(action: "show", id: id)
        }
    }
}
