package mx.gob.redoaxaca.cednna.seguridad

import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class RolController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [rolInstanceList: Rol.list(params), rolInstanceTotal: Rol.count()]
    }

    def create() {
        [rolInstance: new Rol(params)]
    }

    def save() {
        def rolInstance = new Rol(params)
        if (!rolInstance.save(flush: true)) {
            render(view: "create", model: [rolInstance: rolInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'rol.label', default: 'Rol'), rolInstance.id])
        redirect(action: "show", id: rolInstance.id)
    }

    def show(Long id) {
        def rolInstance = Rol.get(id)
        if (!rolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rol.label', default: 'Rol'), id])
            redirect(action: "list")
            return
        }

        [rolInstance: rolInstance]
    }

    def edit(Long id) {
        def rolInstance = Rol.get(id)
        if (!rolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rol.label', default: 'Rol'), id])
            redirect(action: "list")
            return
        }

        [rolInstance: rolInstance]
    }

    def update(Long id, Long version) {
        def rolInstance = Rol.get(id)
        if (!rolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rol.label', default: 'Rol'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (rolInstance.version > version) {
                rolInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'rol.label', default: 'Rol')] as Object[],
                          "Another user has updated this Rol while you were editing")
                render(view: "edit", model: [rolInstance: rolInstance])
                return
            }
        }

        rolInstance.properties = params

        if (!rolInstance.save(flush: true)) {
            render(view: "edit", model: [rolInstance: rolInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'rol.label', default: 'Rol'), rolInstance.id])
        redirect(action: "show", id: rolInstance.id)
    }

    def delete(Long id) {
        def rolInstance = Rol.get(id)
        if (!rolInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'rol.label', default: 'Rol'), id])
            redirect(action: "list")
            return
        }

        try {
            rolInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'rol.label', default: 'Rol'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'rol.label', default: 'Rol'), id])
            redirect(action: "show", id: id)
        }
    }
}
