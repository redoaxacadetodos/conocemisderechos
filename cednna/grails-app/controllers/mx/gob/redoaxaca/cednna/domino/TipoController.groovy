package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured


@Secured( ['IS_AUTHENTICATED_FULLY'])

class TipoController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [tipoInstanceList: Tipo.list(params), tipoInstanceTotal: Tipo.count()]
    }

    def create() {
        [tipoInstance: new Tipo(params)]
    }

    def save() {
        def tipoInstance = new Tipo(params)
        if (!tipoInstance.save(flush: true)) {
            render(view: "create", model: [tipoInstance: tipoInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tipo.label', default: 'Tipo'), tipoInstance.id])
        redirect(action: "show", id: tipoInstance.id)
    }

    def show(Long id) {
        def tipoInstance = Tipo.get(id)
        if (!tipoInstance) {

            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipo.label', default: 'Tipo'), id])
            redirect(action: "list")
            return
        }
        def categoriaList = Categoria.findAllByTipo(tipoInstance)
        [tipoInstance: tipoInstance, categoriaList:categoriaList]
    }

    def edit(Long id) {
        def tipoInstance = Tipo.get(id)
        if (!tipoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipo.label', default: 'Tipo'), id])
            redirect(action: "list")
            return
        }

        [tipoInstance: tipoInstance]
    }

    def update(Long id, Long version) {
        def tipoInstance = Tipo.get(id)
        if (!tipoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipo.label', default: 'Tipo'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (tipoInstance.version > version) {
                tipoInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tipo.label', default: 'Tipo')] as Object[],
                          "Another user has updated this Tipo while you were editing")
                render(view: "edit", model: [tipoInstance: tipoInstance])
                return
            }
        }

        tipoInstance.properties = params

        if (!tipoInstance.save(flush: true)) {
            render(view: "edit", model: [tipoInstance: tipoInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tipo.label', default: 'Tipo'), tipoInstance.id])
        redirect(action: "show", id: tipoInstance.id)
    }

    def delete(Long id) {
        def tipoInstance = Tipo.get(id)
        if (!tipoInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tipo.label', default: 'Tipo'), id])
            redirect(action: "list")
            return
        }

        try {
            tipoInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tipo.label', default: 'Tipo'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tipo.label', default: 'Tipo'), id])
            redirect(action: "show", id: id)
        }
    }
}
