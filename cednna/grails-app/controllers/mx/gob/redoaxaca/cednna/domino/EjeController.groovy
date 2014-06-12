package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured


@Secured( ['ROLE_ADMIN'])
class EjeController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [ejeInstanceList: Eje.list(params), ejeInstanceTotal: Eje.count()]
    }

    def create() {
        [ejeInstance: new Eje(params)]
    }

    def save() {
        def ejeInstance = new Eje(params)
        if (!ejeInstance.save(flush: true)) {
            render(view: "create", model: [ejeInstance: ejeInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'eje.label', default: 'Eje'), ejeInstance.id])
        redirect(action: "show", id: ejeInstance.id)
    }

    def show(Long id) {
        def ejeInstance = Eje.get(id)
        if (!ejeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eje.label', default: 'Eje'), id])
            redirect(action: "list")
            return
        }

        [ejeInstance: ejeInstance]
    }

    def edit(Long id) {
        def ejeInstance = Eje.get(id)
        if (!ejeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eje.label', default: 'Eje'), id])
            redirect(action: "list")
            return
        }

        [ejeInstance: ejeInstance]
    }

    def update(Long id, Long version) {
        def ejeInstance = Eje.get(id)
        if (!ejeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eje.label', default: 'Eje'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (ejeInstance.version > version) {
                ejeInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'eje.label', default: 'Eje')] as Object[],
                          "Another user has updated this Eje while you were editing")
                render(view: "edit", model: [ejeInstance: ejeInstance])
                return
            }
        }

        ejeInstance.properties = params

        if (!ejeInstance.save(flush: true)) {
            render(view: "edit", model: [ejeInstance: ejeInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'eje.label', default: 'Eje'), ejeInstance.id])
        redirect(action: "show", id: ejeInstance.id)
    }

    def delete(Long id) {
        def ejeInstance = Eje.get(id)
        if (!ejeInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'eje.label', default: 'Eje'), id])
            redirect(action: "list")
            return
        }

        try {
            ejeInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'eje.label', default: 'Eje'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'eje.label', default: 'Eje'), id])
            redirect(action: "show", id: id)
        }
    }
}
