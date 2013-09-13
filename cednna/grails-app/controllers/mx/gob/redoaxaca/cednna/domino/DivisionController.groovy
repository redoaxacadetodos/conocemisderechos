package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured


@Secured( ['IS_AUTHENTICATED_FULLY'])
class DivisionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [divisionInstanceList: Division.list(params), divisionInstanceTotal: Division.count()]
    }

    def create() {
        [divisionInstance: new Division(params)]
    }

    def save() {
        def divisionInstance = new Division(params)
        if (!divisionInstance.save(flush: true)) {
            render(view: "create", model: [divisionInstance: divisionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'division.label', default: 'Division'), divisionInstance.id])
        redirect(action: "show", id: divisionInstance.id)
    }

    def show(Long id) {
        def divisionInstance = Division.get(id)
        if (!divisionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'division.label', default: 'Division'), id])
            redirect(action: "list")
            return
        }

        [divisionInstance: divisionInstance]
    }

    def edit(Long id) {
        def divisionInstance = Division.get(id)
        if (!divisionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'division.label', default: 'Division'), id])
            redirect(action: "list")
            return
        }

        [divisionInstance: divisionInstance]
    }

    def update(Long id, Long version) {
        def divisionInstance = Division.get(id)
        if (!divisionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'division.label', default: 'Division'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (divisionInstance.version > version) {
                divisionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'division.label', default: 'Division')] as Object[],
                          "Another user has updated this Division while you were editing")
                render(view: "edit", model: [divisionInstance: divisionInstance])
                return
            }
        }

        divisionInstance.properties = params

        if (!divisionInstance.save(flush: true)) {
            render(view: "edit", model: [divisionInstance: divisionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'division.label', default: 'Division'), divisionInstance.id])
        redirect(action: "show", id: divisionInstance.id)
    }

    def delete(Long id) {
        def divisionInstance = Division.get(id)
        if (!divisionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'division.label', default: 'Division'), id])
            redirect(action: "list")
            return
        }

        try {
            divisionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'division.label', default: 'Division'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'division.label', default: 'Division'), id])
            redirect(action: "show", id: id)
        }
    }
}
