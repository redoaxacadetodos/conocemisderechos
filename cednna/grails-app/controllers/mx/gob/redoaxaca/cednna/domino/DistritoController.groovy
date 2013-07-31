package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException

class DistritoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [distritoInstanceList: Distrito.list(params), distritoInstanceTotal: Distrito.count()]
    }

    def create() {
        [distritoInstance: new Distrito(params)]
    }

    def save() {
        def distritoInstance = new Distrito(params)
        if (!distritoInstance.save(flush: true)) {
            render(view: "create", model: [distritoInstance: distritoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'distrito.label', default: 'Distrito'), distritoInstance.id])
        redirect(action: "show", id: distritoInstance.id)
    }

    def show(Long id) {
        def distritoInstance = Distrito.get(id)
        if (!distritoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'distrito.label', default: 'Distrito'), id])
            redirect(action: "list")
            return
        }

        [distritoInstance: distritoInstance]
    }

    def edit(Long id) {
        def distritoInstance = Distrito.get(id)
        if (!distritoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'distrito.label', default: 'Distrito'), id])
            redirect(action: "list")
            return
        }

        [distritoInstance: distritoInstance]
    }

    def update(Long id, Long version) {
        def distritoInstance = Distrito.get(id)
        if (!distritoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'distrito.label', default: 'Distrito'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (distritoInstance.version > version) {
                distritoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'distrito.label', default: 'Distrito')] as Object[],
                          "Another user has updated this Distrito while you were editing")
                render(view: "edit", model: [distritoInstance: distritoInstance])
                return
            }
        }

        distritoInstance.properties = params

        if (!distritoInstance.save(flush: true)) {
            render(view: "edit", model: [distritoInstance: distritoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'distrito.label', default: 'Distrito'), distritoInstance.id])
        redirect(action: "show", id: distritoInstance.id)
    }

    def delete(Long id) {
        def distritoInstance = Distrito.get(id)
        if (!distritoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'distrito.label', default: 'Distrito'), id])
            redirect(action: "list")
            return
        }

        try {
            distritoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'distrito.label', default: 'Distrito'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'distrito.label', default: 'Distrito'), id])
            redirect(action: "show", id: id)
        }
    }
}
