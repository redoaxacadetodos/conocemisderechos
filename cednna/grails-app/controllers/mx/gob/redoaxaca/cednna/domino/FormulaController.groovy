package mx.gob.redoaxaca.cednna.domino

import org.springframework.dao.DataIntegrityViolationException


import grails.converters.JSON
import grails.plugins.springsecurity.Secured


@Secured(["hasRole('ROLE_ADMIN')"])
class FormulaController {

	def dataTablesService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }
	
	def dataTablesListadoFormulas = {
		
	def query=" from cat_formula	 as f  "
		
		
		
		render dataTablesService.datosParaTablaQuery(query,params,
		[
		"ctf_id as id",
		"ctf_descripcion as descripcion" ,
		"ctf_nombre as nombre",
		"ctf_sentencia as sentencia" 
		],
		[
		"ctf_descripcion" ,
		"ctf_nombre",
		"ctf_sentencia" 
		],
	
		[
		"id",
		"descripcion" ,
		"nombre",
		"sentencia" 
		],1,"text") as JSON
}
	
	
	
	

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [formulaInstanceList: Formula.list(params), formulaInstanceTotal: Formula.count()]
    }

    def create() {
        [formulaInstance: new Formula(params)]
    }

    def save() {
        def formulaInstance = new Formula(params)
        if (!formulaInstance.save(flush: true)) {
            render(view: "create", model: [formulaInstance: formulaInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'formula.label', default: 'Formula'), formulaInstance.id])
        redirect(action: "show", id: formulaInstance.id)
    }

    def show(Long id) {
        def formulaInstance = Formula.get(id)
        if (!formulaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formula.label', default: 'Formula'), id])
            redirect(action: "list")
            return
        }

        [formulaInstance: formulaInstance]
    }

    def edit(Long id) {
        def formulaInstance = Formula.get(id)
        if (!formulaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formula.label', default: 'Formula'), id])
            redirect(action: "list")
            return
        }

        [formulaInstance: formulaInstance]
    }

    def update(Long id, Long version) {
        def formulaInstance = Formula.get(id)
        if (!formulaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formula.label', default: 'Formula'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (formulaInstance.version > version) {
                formulaInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'formula.label', default: 'Formula')] as Object[],
                          "Another user has updated this Formula while you were editing")
                render(view: "edit", model: [formulaInstance: formulaInstance])
                return
            }
        }

        formulaInstance.properties = params

        if (!formulaInstance.save(flush: true)) {
            render(view: "edit", model: [formulaInstance: formulaInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'formula.label', default: 'Formula'), formulaInstance.id])
        redirect(action: "show", id: formulaInstance.id)
    }
	
	
	def calc(){
		
		
		
		
	}

    def delete(Long id) {
        def formulaInstance = Formula.get(id)
        if (!formulaInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'formula.label', default: 'Formula'), id])
            redirect(action: "list")
            return
        }

        try {
            formulaInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'formula.label', default: 'Formula'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'formula.label', default: 'Formula'), id])
            redirect(action: "show", id: id)
        }
    }
}
