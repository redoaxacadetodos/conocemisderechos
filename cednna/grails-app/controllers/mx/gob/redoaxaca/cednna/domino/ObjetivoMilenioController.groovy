package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException

import grails.plugins.springsecurity.Secured


@Secured( ['IS_AUTHENTICATED_FULLY'])
class ObjetivoMilenioController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [objetivoMilenioInstanceList: ObjetivoMilenio.list(params), objetivoMilenioInstanceTotal: ObjetivoMilenio.count()]
    }

    def create() {
        [objetivoMilenioInstance: new ObjetivoMilenio(params)]
    }

    def save() {
        def objetivoMilenioInstance = new ObjetivoMilenio(params)
        if (!objetivoMilenioInstance.save(flush: true)) {
            render(view: "create", model: [objetivoMilenioInstance: objetivoMilenioInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), objetivoMilenioInstance.id])
        redirect(action: "show", id: objetivoMilenioInstance.id)
    }

    def show(Long id) {
        def objetivoMilenioInstance = ObjetivoMilenio.get(id)
        if (!objetivoMilenioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), id])
            redirect(action: "list")
            return
        }

        [objetivoMilenioInstance: objetivoMilenioInstance]
    }

    def edit(Long id) {
        def objetivoMilenioInstance = ObjetivoMilenio.get(id)
        if (!objetivoMilenioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), id])
            redirect(action: "list")
            return
        }

        [objetivoMilenioInstance: objetivoMilenioInstance]
    }

    def update(Long id, Long version) {
        def objetivoMilenioInstance = ObjetivoMilenio.get(id)
        if (!objetivoMilenioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (objetivoMilenioInstance.version > version) {
                objetivoMilenioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio')] as Object[],
                          "Another user has updated this ObjetivoMilenio while you were editing")
                render(view: "edit", model: [objetivoMilenioInstance: objetivoMilenioInstance])
                return
            }
        }

        objetivoMilenioInstance.properties = params

        if (!objetivoMilenioInstance.save(flush: true)) {
            render(view: "edit", model: [objetivoMilenioInstance: objetivoMilenioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), objetivoMilenioInstance.id])
        redirect(action: "show", id: objetivoMilenioInstance.id)
    }

    def delete(Long id) {
        def objetivoMilenioInstance = ObjetivoMilenio.get(id)
        if (!objetivoMilenioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), id])
            redirect(action: "list")
            return
        }

        try {
            objetivoMilenioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio'), id])
            redirect(action: "show", id: id)
        }
    }
}
