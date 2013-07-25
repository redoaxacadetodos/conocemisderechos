package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException

class RegionController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [regionInstanceList: Region.list(params), regionInstanceTotal: Region.count()]
    }

    def create() {
        [regionInstance: new Region(params)]
    }

    def save() {
        def regionInstance = new Region(params)
        if (!regionInstance.save(flush: true)) {
            render(view: "create", model: [regionInstance: regionInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])
        redirect(action: "show", id: regionInstance.id)
    }

    def show(Long id) {
        def regionInstance = Region.get(id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), id])
            redirect(action: "list")
            return
        }

        [regionInstance: regionInstance]
    }

    def edit(Long id) {
        def regionInstance = Region.get(id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), id])
            redirect(action: "list")
            return
        }

        [regionInstance: regionInstance]
    }

    def update(Long id, Long version) {
        def regionInstance = Region.get(id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (regionInstance.version > version) {
                regionInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'region.label', default: 'Region')] as Object[],
                          "Another user has updated this Region while you were editing")
                render(view: "edit", model: [regionInstance: regionInstance])
                return
            }
        }

        regionInstance.properties = params

        if (!regionInstance.save(flush: true)) {
            render(view: "edit", model: [regionInstance: regionInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'region.label', default: 'Region'), regionInstance.id])
        redirect(action: "show", id: regionInstance.id)
    }

    def delete(Long id) {
        def regionInstance = Region.get(id)
        if (!regionInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'region.label', default: 'Region'), id])
            redirect(action: "list")
            return
        }

        try {
            regionInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'region.label', default: 'Region'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'region.label', default: 'Region'), id])
            redirect(action: "show", id: id)
        }
    }
}
