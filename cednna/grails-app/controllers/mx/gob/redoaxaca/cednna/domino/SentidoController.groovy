package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured


@Secured(["hasRole('ROLE_ADMIN')"])
class SentidoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [sentidoInstanceList: Sentido.list(params), sentidoInstanceTotal: Sentido.count()]
    }

    def create() {
        [sentidoInstance: new Sentido(params)]
    }

    def save() {
        def sentidoInstance = new Sentido(params)
        if (!sentidoInstance.save(flush: true)) {
            render(view: "create", model: [sentidoInstance: sentidoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'sentido.label', default: 'Sentido'), sentidoInstance.id])
        redirect(action: "show", id: sentidoInstance.id)
    }

    def show(Long id) {
        def sentidoInstance = Sentido.get(id)
        if (!sentidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sentido.label', default: 'Sentido'), id])
            redirect(action: "list")
            return
        }

        [sentidoInstance: sentidoInstance]
    }

    def edit(Long id) {
        def sentidoInstance = Sentido.get(id)
        if (!sentidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sentido.label', default: 'Sentido'), id])
            redirect(action: "list")
            return
        }

        [sentidoInstance: sentidoInstance]
    }

    def update(Long id, Long version) {
        def sentidoInstance = Sentido.get(id)
        if (!sentidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sentido.label', default: 'Sentido'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (sentidoInstance.version > version) {
                sentidoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'sentido.label', default: 'Sentido')] as Object[],
                          "Another user has updated this Sentido while you were editing")
                render(view: "edit", model: [sentidoInstance: sentidoInstance])
                return
            }
        }

        sentidoInstance.properties = params

        if (!sentidoInstance.save(flush: true)) {
            render(view: "edit", model: [sentidoInstance: sentidoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'sentido.label', default: 'Sentido'), sentidoInstance.id])
        redirect(action: "show", id: sentidoInstance.id)
    }

    def delete(Long id) {
        def sentidoInstance = Sentido.get(id)
        if (!sentidoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'sentido.label', default: 'Sentido'), id])
            redirect(action: "list")
            return
        }

        try {
            sentidoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'sentido.label', default: 'Sentido'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'sentido.label', default: 'Sentido'), id])
            redirect(action: "show", id: id)
        }
    }
}
