package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured


@Secured( ['IS_AUTHENTICATED_FULLY'])
class CategoriaController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

/*    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [categoriaInstanceList: Categoria.list(params), categoriaInstanceTotal: Categoria.count()]
    }
*/
    def create() {
        def tipoInstance = Tipo.findById(params.idtipo)
        
       [categoriaInstance: new Categoria(params), tipoInstance: tipoInstance]
    }

    def save() {
        def categoriaInstance = new Categoria(params)
        if (!categoriaInstance.save(flush: true)) {
            render(view: "create", model: [categoriaInstance: categoriaInstance, idTipo:params.idTipo])
            return
        }
        flash.message = message(code: 'default.created.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoriaInstance.id])
        redirect(action: "show", id: categoriaInstance.id, params:params)
    }

    def show(Long id) {
        def categoriaInstance = Categoria.get(id)
		def tipo = params.idTipo
        if (!categoriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
            redirect(action: "list")
            return
        }

        [categoriaInstance: categoriaInstance, idTipo: tipo]
    }

    def edit(Long id) {
        def categoriaInstance = Categoria.get(id)
        if (!categoriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
            redirect(action: "list")
            return
        }
        def tipoInstance = categoriaInstance.tipo

        [categoriaInstance: categoriaInstance,tipoInstance:tipoInstance]
    }

    def update(Long id, Long version) {
        def categoriaInstance = Categoria.get(id)
        if (!categoriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (categoriaInstance.version > version) {
                categoriaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'categoria.label', default: 'Categoria')] as Object[],
                          "Another user has updated this Categoria while you were editing")
                render(view: "edit", model: [categoriaInstance: categoriaInstance])
                return
            }
        }

        categoriaInstance.properties = params

        if (!categoriaInstance.save(flush: true)) {
            render(view: "edit", model: [categoriaInstance: categoriaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'categoria.label', default: 'Categoria'), categoriaInstance.id])
        redirect(action: "show", id: categoriaInstance.id)
    }

    def delete(Long id) {
        def categoriaInstance = Categoria.get(id)
		def idTipo = params.idTipo
        if (!categoriaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
            redirect(action: "list")
            return
        }

        try {
            categoriaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
            redirect(action: "show", controller:'tipo', id:idTipo)
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'categoria.label', default: 'Categoria'), id])
            redirect(action: "show", id: id)
        }
    }
}
