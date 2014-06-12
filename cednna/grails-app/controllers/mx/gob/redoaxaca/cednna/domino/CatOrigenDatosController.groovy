package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured( ['ROLE_ADMIN'])
class CatOrigenDatosController {
	
	def tablasService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list() {
       
    }
	
	def getTablaVariables(){
		int sEcho = 0
		if(params.sEcho){
			sEcho = params.sEcho.toInteger()
			sEcho++
		}
		
		def datos = tablasService.getTablaVariables(params, false)
		def totalRecords = tablasService.getTablaVariables(params, true)
		
		def result = ['sEcho':sEcho, 'iTotalRecords':totalRecords, 'iTotalDisplayRecords':totalRecords, 'aaData':datos]
		
		render result as JSON
	}

    def create() {
        [catOrigenDatosInstance: new CatOrigenDatos(params)]
    }

    def save() {
        def catOrigenDatosInstance = new CatOrigenDatos(params)
        if (!catOrigenDatosInstance.save(flush: true)) {
            render(view: "create", model: [catOrigenDatosInstance: catOrigenDatosInstance])
            return
        }

        flash.message = message(code: 'default.created.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), catOrigenDatosInstance.id])
        redirect(action: "show", id: catOrigenDatosInstance.id)
    }

    def show(Long id) {
        def catOrigenDatosInstance = CatOrigenDatos.get(id)
        if (!catOrigenDatosInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), id])
            redirect(action: "list")
            return
        }

        [catOrigenDatosInstance: catOrigenDatosInstance]
    }

    def edit(Long id) {
        def catOrigenDatosInstance = CatOrigenDatos.get(id)
        if (!catOrigenDatosInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), id])
            redirect(action: "list")
            return
        }

        [catOrigenDatosInstance: catOrigenDatosInstance]
    }

    def update(Long id, Long version) {
        def catOrigenDatosInstance = CatOrigenDatos.get(id)
        if (!catOrigenDatosInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (catOrigenDatosInstance.version > version) {
                catOrigenDatosInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos')] as Object[],
                          "Another user has updated this CatOrigenDatos while you were editing")
                render(view: "edit", model: [catOrigenDatosInstance: catOrigenDatosInstance])
                return
            }
        }

        catOrigenDatosInstance.properties = params

        if (!catOrigenDatosInstance.save(flush: true)) {
            render(view: "edit", model: [catOrigenDatosInstance: catOrigenDatosInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), catOrigenDatosInstance.id])
        redirect(action: "show", id: catOrigenDatosInstance.id)
    }

    def delete(Long id) {
        def catOrigenDatosInstance = CatOrigenDatos.get(id)
        if (!catOrigenDatosInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), id])
            redirect(action: "list")
            return
        }

        try {
            catOrigenDatosInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos'), id])
            redirect(action: "show", id: id)
        }
    }
}
