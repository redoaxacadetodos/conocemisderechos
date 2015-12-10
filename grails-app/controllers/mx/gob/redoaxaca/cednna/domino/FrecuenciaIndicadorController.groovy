package mx.gob.redoaxaca.cednna.domino

import static java.util.Calendar.YEAR
import grails.converters.deep.JSON
import grails.plugins.springsecurity.Secured
import groovy.time.TimeCategory

import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_DEP','ROLE_ADMIN'])
class FrecuenciaIndicadorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def springSecurityService

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
		def usuario = springSecurityService.currentUser
		def dependencia =  usuario.dependencia
		def fecuencias = []
        def frecuenciaIndicadorInstanceList = FrecuenciaIndicador.list(params)
		
		if(!esAdmin()){
			frecuenciaIndicadorInstanceList.each{ frecuenciaIndicadorInstance ->
				def catOrigenDatosInstance = CatOrigenDatos.findByClave(frecuenciaIndicadorInstance.variable.claveVar)
				if(catOrigenDatosInstance && catOrigenDatosInstance.dependencia == dependencia){
					fecuencias.add(frecuenciaIndicadorInstance)
				}
			}
		}else{
			fecuencias = frecuenciaIndicadorInstanceList
		}
		
		
        [frecuenciaIndicadorInstanceList: fecuencias]
    }

    def create() {
		def user =springSecurityService.currentUser
		def dependencia = user.dependencia
		
        [frecuenciaIndicadorInstance: new FrecuenciaIndicador(params), dependencia:dependencia, admin:esAdmin()]
    }

    def save() {
		if(params.idVariable instanceof String){
			params.idVariable = [params.idVariable]
			params.frecuencia = [params.frecuencia]
			params.fechaActualizacion = [params.fechaActualizacion]
			params.ano = [params.ano]
		}
		
		for(int i=0; i<params.idVariable.size();i++){
			def frecuenciaIndicadorInstance = new FrecuenciaIndicador()
			frecuenciaIndicadorInstance.variable = DVariable.get(params.idVariable[i].toLong())
			frecuenciaIndicadorInstance.frecuencia = Frecuencia.get(params.frecuencia[i].toLong())
			frecuenciaIndicadorInstance.fechaActualizacion = new Date().parse("yyyy-M-d", params.fechaActualizacion[i])
			frecuenciaIndicadorInstance.ano = params.ano[i].toInteger()
			
			if (!frecuenciaIndicadorInstance.save(flush: true)) {
				render(view: "create", model: [frecuenciaIndicadorInstance: frecuenciaIndicadorInstance])
				return
			}
		}
		
        redirect(action: "list")
    }

    def show(Long id) {
        def frecuenciaIndicadorInstance = FrecuenciaIndicador.get(id)
        if (!frecuenciaIndicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), id])
            redirect(action: "list")
            return
        }

        [frecuenciaIndicadorInstance: frecuenciaIndicadorInstance]
    }

    def edit(Long id) {
        def frecuenciaIndicadorInstance = FrecuenciaIndicador.get(id)
        if (!frecuenciaIndicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), id])
            redirect(action: "list")
            return
        }

        [frecuenciaIndicadorInstance: frecuenciaIndicadorInstance]
    }

    def update(Long id, Long version) {
        def frecuenciaIndicadorInstance = FrecuenciaIndicador.get(id)
        if (!frecuenciaIndicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (frecuenciaIndicadorInstance.version > version) {
                frecuenciaIndicadorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador')] as Object[],
                          "Another user has updated this FrecuenciaIndicador while you were editing")
                render(view: "edit", model: [frecuenciaIndicadorInstance: frecuenciaIndicadorInstance])
                return
            }
        }

		frecuenciaIndicadorInstance.frecuencia = Frecuencia.get(params.frecuencia.id.toLong())
		frecuenciaIndicadorInstance.fechaActualizacion = new Date().parse("yyyy-M-d", params.fechaActualizacion)
		frecuenciaIndicadorInstance.ano = params.ano.toInteger()

        if (!frecuenciaIndicadorInstance.save(flush: true)) {
            render(view: "edit", model: [frecuenciaIndicadorInstance: frecuenciaIndicadorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), frecuenciaIndicadorInstance.id])
        redirect(action: "show", id: frecuenciaIndicadorInstance.id)
    }

    def delete(Long id) {
        def frecuenciaIndicadorInstance = FrecuenciaIndicador.get(id)
        if (!frecuenciaIndicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), id])
            redirect(action: "list")
            return
        }

        try {
            frecuenciaIndicadorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador'), id])
            redirect(action: "show", id: id)
        }
    }
	
	def actualizarVariables(){
		if(params.idIndicador!='null'){
			def user =springSecurityService.currentUser
			def dependencia = user.dependencia
			def indicadorInstance = Indicador.get(params.idIndicador.toLong())
			def variables = indicadorInstance?.variables.sort{it.id}
			render(template:'variables', model:[variables:variables, dependencia:dependencia, admin:esAdmin()])
		}else{
			render(text:'')
		}
	}
	
	def calendario(){
		def user =springSecurityService.currentUser
		[dependencia:user.dependencia]
	}
	
	@Secured(['ROLE_DEP', 'ROLE_ADMIN'])
	def actualizarCalendario(){
		def frecuenciasActualizacion = FrecuenciaIndicador.getAll()
		def eventos = []
		def dependencia =  Dependencia.get(params.id.toLong())
		
		frecuenciasActualizacion.each{ frecuenciaIndicadorInstance ->
			def catOrigenDatosInstance = CatOrigenDatos.findByClave(frecuenciaIndicadorInstance.variable.claveVar)
			if(catOrigenDatosInstance && catOrigenDatosInstance.dependencia == dependencia){
				def variable = catOrigenDatosInstance.descripcion
				def fechaActual = frecuenciaIndicadorInstance.fechaActualizacion
				def fechaActualizacion = frecuenciaIndicadorInstance.fechaActualizacion
				while(fechaActualizacion[YEAR]<=fechaActual[YEAR]){
					eventos.add([description:variable, start:fechaActualizacion?.format("yyyy-MM-dd")])
					fechaActualizacion = use(TimeCategory) {
						if(frecuenciaIndicadorInstance?.frecuencia?.mes){
							fechaActualizacion + frecuenciaIndicadorInstance?.frecuencia?.nmeses?.month
						}else{
							fechaActualizacion + frecuenciaIndicadorInstance?.frecuencia?.nmeses?.days
						}
					}
				}
			}
		}
		
		render (template:'calendario', model:[eventos:eventos as JSON])
	}
	
	def esAdmin(){
		def user =springSecurityService.currentUser
		boolean admin = false
		user.authorities.each{
			if(it.authority.equals('ROLE_ADMIN')){
				admin = true
			}
		}
		return admin
	}
	
}
