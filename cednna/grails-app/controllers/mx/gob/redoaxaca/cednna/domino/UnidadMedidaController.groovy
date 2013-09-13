package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured


@Secured(["hasRole('ROLE_ADMIN')"])
class UnidadMedidaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [unidadMedidaInstanceList: UnidadMedida.list(params), unidadMedidaInstanceTotal: UnidadMedida.count()]
    }

    def create() {
        [unidadMedidaInstance: new UnidadMedida(params)]
    }

    def save() {
        def unidadMedidaInstance = new UnidadMedida(params)
        if (!unidadMedidaInstance.save(flush: true)) {
            render(view: "create", model: [unidadMedidaInstance: unidadMedidaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), unidadMedidaInstance.id])
        redirect(action: "show", id: unidadMedidaInstance.id)
    }

    def show(Long id) {
        def unidadMedidaInstance = UnidadMedida.get(id)
        if (!unidadMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), id])
            redirect(action: "list")
            return
        }

        [unidadMedidaInstance: unidadMedidaInstance]
    }

    def edit(Long id) {
        def unidadMedidaInstance = UnidadMedida.get(id)
        if (!unidadMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), id])
            redirect(action: "list")
            return
        }

        [unidadMedidaInstance: unidadMedidaInstance]
    }

    def update(Long id, Long version) {
        def unidadMedidaInstance = UnidadMedida.get(id)
        if (!unidadMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (unidadMedidaInstance.version > version) {
                unidadMedidaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'unidadMedida.label', default: 'UnidadMedida')] as Object[],
                          "Another user has updated this UnidadMedida while you were editing")
                render(view: "edit", model: [unidadMedidaInstance: unidadMedidaInstance])
                return
            }
        }

        unidadMedidaInstance.properties = params

        if (!unidadMedidaInstance.save(flush: true)) {
            render(view: "edit", model: [unidadMedidaInstance: unidadMedidaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), unidadMedidaInstance.id])
        redirect(action: "show", id: unidadMedidaInstance.id)
    }

    def delete(Long id) {
        def unidadMedidaInstance = UnidadMedida.get(id)
        if (!unidadMedidaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), id])
            redirect(action: "list")
            return
        }

        try {
            unidadMedidaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'unidadMedida.label', default: 'UnidadMedida'), id])
            redirect(action: "show", id: id)
        }
    }
}
