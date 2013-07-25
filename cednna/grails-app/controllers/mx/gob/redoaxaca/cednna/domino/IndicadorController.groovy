package mx.gob.redoaxaca.cednna.domino

import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.dao.DataIntegrityViolationException

class IndicadorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [indicadorInstanceList: Indicador.list(params), indicadorInstanceTotal: Indicador.count()]
    }

    def create() {
        [indicadorInstance: new Indicador(params)]
    }

    def save() {
        def indicadorInstance = new Indicador(params)
        if (!indicadorInstance.save(flush: true)) {
            render(view: "create", model: [indicadorInstance: indicadorInstance])
            return
        }

		
		
		
		
				def sentencia= indicadorInstance?.formula?.variables
				def variables= sentencia.split("|")
				
				
				
				if(sentencia){
					
									
					
					
					
											 ScriptEngineManager script = new ScriptEngineManager();
											 ScriptEngine js = script.getEngineByName("JavaScript");
											 try {
												 
												 
												 System.out.println(js.eval("eval('')"));
												 
												 
											 
											 } catch (ScriptException e) {
												 // TODO Auto-generated catch block
												 e.printStackTrace();
											 }
					
					
					
				}
		
		
		
		
		
		
		
		
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'indicador.label', default: 'Indicador'), indicadorInstance.id])
        redirect(action: "show", id: indicadorInstance.id)
    }

    def show(Long id) {
        def indicadorInstance = Indicador.get(id)
        if (!indicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
            return
        }

        [indicadorInstance: indicadorInstance]
    }

    def edit(Long id) {
        def indicadorInstance = Indicador.get(id)
        if (!indicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
            return
        }

        [indicadorInstance: indicadorInstance]
    }

    def update(Long id, Long version) {
        def indicadorInstance = Indicador.get(id)
        if (!indicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (indicadorInstance.version > version) {
                indicadorInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'indicador.label', default: 'Indicador')] as Object[],
                          "Another user has updated this Indicador while you were editing")
                render(view: "edit", model: [indicadorInstance: indicadorInstance])
                return
            }
        }

        indicadorInstance.properties = params

        if (!indicadorInstance.save(flush: true)) {
            render(view: "edit", model: [indicadorInstance: indicadorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicador.label', default: 'Indicador'), indicadorInstance.id])
        redirect(action: "show", id: indicadorInstance.id)
    }

	
	
	def buscadorVariable(){
		
		
		def formula = Formula.get(params.id);
		def var
		if(formula){
			
			var= formula.variables.split("\\|")

							
			System.out.println(formula);
		}		
		
		
		
		[variable:var,sentencia:formula.sentencia,descripcion:formula.descripcion]
	}
	
	
	def resultadoVariable(){
		
		
		def variable=params.var
		
		def localidad=  Localidad.get(params.getAt("localidad_"+variable))
		def municipio=  Municipio.get(params.getAt("municipio_"+variable))
		def region =    Region.get(params.getAt("region_"+variable)) 
		def estado =    Estado.get(params.getAt("estado_"+variable)) 
		def rInicial =  params.getAt("edadDe_"+variable)
		def rFinal =    params.getAt("edadHasta_"+variable)
		def poblacion = params.getAt("poblacion_"+variable)

		def var = Variable.list();
	
		System.out.println(var);
		
		[var:var]
		
			
	}
	
	
    def delete(Long id) {
        def indicadorInstance = Indicador.get(id)
        if (!indicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
            return
        }

        try {
            indicadorInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "show", id: id)
        }
    }
}
