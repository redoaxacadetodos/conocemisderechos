package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured( ['ROLE_ADMIN'])
class TipoEjeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def dataTablesService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoEjeInstanceList: TipoEje.list(params), tipoEjeInstanceTotal: TipoEje.count()]
    }
	
	def dataTablesList = {
		def query = "from cat_tipo_eje"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"eje_id as id",
			"eje_tipo as tipo",
			],
			[
			"eje_tipo"
			],
			[
			"id",
			"tipo",
			],1,"text") as JSON
	}

    def create() {
        [tipoEjeInstance: new TipoEje(params)]
    }

    def save() {
        def tipoEjeInstance = new TipoEje(params)
        if (!tipoEjeInstance.save(flush: true)) {
            render(view: "create", model: [tipoEjeInstance: tipoEjeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), tipoEjeInstance.id])
        redirect(action: "show", id: tipoEjeInstance.id)
    }

    def show(Long id) {
        def tipoEjeInstance = TipoEje.get(id)
        if (!tipoEjeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), id])
            redirect(action: "list")
            return
        }

        [tipoEjeInstance: tipoEjeInstance]
    }

    def edit(Long id) {
        def tipoEjeInstance = TipoEje.get(id)
        if (!tipoEjeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), id])
            redirect(action: "list")
            return
        }

        [tipoEjeInstance: tipoEjeInstance]
    }

    def update(Long id, Long version) {
        def tipoEjeInstance = TipoEje.get(id)
        if (!tipoEjeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoEjeInstance.version > version) {
                tipoEjeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipoEje.label', default: 'TipoEje')] as Object[],
                          "Another user has updated this TipoEje while you were editing")
                render(view: "edit", model: [tipoEjeInstance: tipoEjeInstance])
                return
            }
        }

        tipoEjeInstance.properties = params

        if (!tipoEjeInstance.save(flush: true)) {
            render(view: "edit", model: [tipoEjeInstance: tipoEjeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), tipoEjeInstance.id])
        redirect(action: "show", id: tipoEjeInstance.id)
    }

    def delete(Long id) {
        def tipoEjeInstance = TipoEje.get(id)
        if (!tipoEjeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), id])
            redirect(action: "list")
            return
        }

        try {
            tipoEjeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipoEje.label', default: 'TipoEje'), id])
            redirect(action: "show", id: id)
        }
    }
}
