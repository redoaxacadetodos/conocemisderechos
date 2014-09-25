package mx.gob.redoaxaca.cednna.domino

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured( ['ROLE_ADMIN'])
class NivelController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [nivelInstanceList: Nivel.list(params), nivelInstanceTotal: Nivel.count()]
    }
	
    def create() {
        [nivelInstance: new Nivel(params)]
    }

    def save() {
        def nivelInstance = new Nivel(params)
        if (!nivelInstance.save(flush: true)) {
            render(view: "create", model: [nivelInstance: nivelInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'nivel.label', default: 'Nivel'), nivelInstance.id])
        redirect(action: "show", id: nivelInstance.id)
    }

    def show(Long id) {
        def nivelInstance = Nivel.get(id)
        if (!nivelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivel.label', default: 'Nivel'), id])
            redirect(action: "list")
            return
        }

        [nivelInstance: nivelInstance]
    }

    def edit(Long id) {
        def nivelInstance = Nivel.get(id)
        if (!nivelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivel.label', default: 'Nivel'), id])
            redirect(action: "list")
            return
        }

        [nivelInstance: nivelInstance]
    }

    def update(Long id, Long version) {
        def nivelInstance = Nivel.get(id)
        if (!nivelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivel.label', default: 'Nivel'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (nivelInstance.version > version) {
                nivelInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'nivel.label', default: 'Nivel')] as Object[],
                          "Another user has updated this Nivel while you were editing")
                render(view: "edit", model: [nivelInstance: nivelInstance])
                return
            }
        }

        nivelInstance.properties = params

        if (!nivelInstance.save(flush: true)) {
            render(view: "edit", model: [nivelInstance: nivelInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'nivel.label', default: 'Nivel'), nivelInstance.id])
        redirect(action: "show", id: nivelInstance.id)
    }

    def delete(Long id) {
        def nivelInstance = Nivel.get(id)
        if (!nivelInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'nivel.label', default: 'Nivel'), id])
            redirect(action: "list")
            return
        }

        try {
            nivelInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'nivel.label', default: 'Nivel'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'nivel.label', default: 'Nivel'), id])
            redirect(action: "show", id: id)
        }
    }
}
