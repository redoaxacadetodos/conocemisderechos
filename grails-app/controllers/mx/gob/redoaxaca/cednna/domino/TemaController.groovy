package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured( ['ROLE_ADMIN'])
class TemaController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [temaInstanceList: Tema.list(params), temaInstanceTotal: Tema.count()]
    }
	
	def dataTablesList = {
		def query = "from tema t join cat_pn_desarrollo pnd on (t.ped_id = pnd.cnpd_id)"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"t.id as id",
			"t.descripcion as tema",
			"cnpd_descripcion as eje"
			],
			[
			"t.descripcion",
			"cnpd_descripcion"
			],
			[
				"id",
				"tema",
				"eje"
			],1, "text") as JSON
	}

    def create() {
        [temaInstance: new Tema(params)]
    }

    def save() {
        def temaInstance = new Tema(params)
        if (!temaInstance.save(flush: true)) {
            render(view: "create", model: [temaInstance: temaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'tema.label', default: 'Tema'), temaInstance.id])
        redirect(action: "show", id: temaInstance.id)
    }

    def show(Long id) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "list")
            return
        }

        [temaInstance: temaInstance]
    }

    def edit(Long id) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "list")
            return
        }

        [temaInstance: temaInstance]
    }

    def update(Long id, Long version) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (temaInstance.version > version) {
                temaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'tema.label', default: 'Tema')] as Object[],
                          "Another user has updated this Tema while you were editing")
                render(view: "edit", model: [temaInstance: temaInstance])
                return
            }
        }

        temaInstance.properties = params

        if (!temaInstance.save(flush: true)) {
            render(view: "edit", model: [temaInstance: temaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'tema.label', default: 'Tema'), temaInstance.id])
        redirect(action: "show", id: temaInstance.id)
    }

    def delete(Long id) {
        def temaInstance = Tema.get(id)
        if (!temaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "list")
            return
        }

        try {
            temaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'tema.label', default: 'Tema'), id])
            redirect(action: "show", id: id)
        }
    }
}
