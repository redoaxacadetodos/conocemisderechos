package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException

class LocalidadController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [localidadInstanceList: Localidad.list(params), localidadInstanceTotal: Localidad.count()]
    }

    def create() {
        [localidadInstance: new Localidad(params)]
    }

    def save() {
        def localidadInstance = new Localidad(params)
        if (!localidadInstance.save(flush: true)) {
            render(view: "create", model: [localidadInstance: localidadInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'localidad.label', default: 'Localidad'), localidadInstance.id])
        redirect(action: "show", id: localidadInstance.id)
    }

    def show(Long id) {
        def localidadInstance = Localidad.get(id)
        if (!localidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), id])
            redirect(action: "list")
            return
        }

        [localidadInstance: localidadInstance]
    }

    def edit(Long id) {
        def localidadInstance = Localidad.get(id)
        if (!localidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), id])
            redirect(action: "list")
            return
        }

        [localidadInstance: localidadInstance]
    }

    def update(Long id, Long version) {
        def localidadInstance = Localidad.get(id)
        if (!localidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (localidadInstance.version > version) {
                localidadInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'localidad.label', default: 'Localidad')] as Object[],
                          "Another user has updated this Localidad while you were editing")
                render(view: "edit", model: [localidadInstance: localidadInstance])
                return
            }
        }

        localidadInstance.properties = params

        if (!localidadInstance.save(flush: true)) {
            render(view: "edit", model: [localidadInstance: localidadInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'localidad.label', default: 'Localidad'), localidadInstance.id])
        redirect(action: "show", id: localidadInstance.id)
    }

    def delete(Long id) {
        def localidadInstance = Localidad.get(id)
        if (!localidadInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'localidad.label', default: 'Localidad'), id])
            redirect(action: "list")
            return
        }

        try {
            localidadInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'localidad.label', default: 'Localidad'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'localidad.label', default: 'Localidad'), id])
            redirect(action: "show", id: id)
        }
    }
}
