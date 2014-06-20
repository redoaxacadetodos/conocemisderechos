package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException


@Secured(["hasRole('ROLE_ADMIN')"])
class FrecuenciaController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [frecuenciaInstanceList: Frecuencia.list(params), frecuenciaInstanceTotal: Frecuencia.count()]
    }
	
	def dataTablesList = {
		def query = "from cat_frecuencia"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"cfr_id as id",
			"cfr_descripcion as descripcion"
			],
			[
			"cfr_descripcion"
			],
			[
			"id",
			"descripcion"
			],1, "text") as JSON
	}

    def create() {
        [frecuenciaInstance: new Frecuencia(params)]
    }

    def save() {
        def frecuenciaInstance = new Frecuencia(params)
        if (!frecuenciaInstance.save(flush: true)) {
            render(view: "create", model: [frecuenciaInstance: frecuenciaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), frecuenciaInstance.id])
        redirect(action: "show", id: frecuenciaInstance.id)
    }

    def show(Long id) {
        def frecuenciaInstance = Frecuencia.get(id)
        if (!frecuenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
            redirect(action: "list")
            return
        }

        [frecuenciaInstance: frecuenciaInstance]
    }

    def edit(Long id) {
        def frecuenciaInstance = Frecuencia.get(id)
        if (!frecuenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
            redirect(action: "list")
            return
        }

        [frecuenciaInstance: frecuenciaInstance]
    }

    def update(Long id, Long version) {
        def frecuenciaInstance = Frecuencia.get(id)
        if (!frecuenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (frecuenciaInstance.version > version) {
                frecuenciaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'frecuencia.label', default: 'Frecuencia')] as Object[],
                          "Another user has updated this Frecuencia while you were editing")
                render(view: "edit", model: [frecuenciaInstance: frecuenciaInstance])
                return
            }
        }

        frecuenciaInstance.properties = params

        if (!frecuenciaInstance.save(flush: true)) {
            render(view: "edit", model: [frecuenciaInstance: frecuenciaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), frecuenciaInstance.id])
        redirect(action: "show", id: frecuenciaInstance.id)
    }

    def delete(Long id) {
        def frecuenciaInstance = Frecuencia.get(id)
        if (!frecuenciaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
            redirect(action: "list")
            return
        }

        try {
            frecuenciaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'frecuencia.label', default: 'Frecuencia'), id])
            redirect(action: "show", id: id)
        }
    }
}
