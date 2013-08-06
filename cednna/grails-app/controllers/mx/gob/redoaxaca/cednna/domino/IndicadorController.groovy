package mx.gob.redoaxaca.cednna.domino

import com.redoaxaca.java.Resultado
import grails.converters.JSON
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.dao.DataIntegrityViolationException

class IndicadorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

	def dataTablesService
	
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

	
	def dataTablesListadoIndicadores = {
		
	def query="  from idn_indicador	 as i "
		
		
		
		render dataTablesService.datosParaTablaQuery(query,params,
	    [
		'i.idn_id as id',
		'i.idn_nombre as  nombre',
		'i.idn_objetivo  as objetivo',
		'i.idn_nombre_responsable as nomr',
		'i.idn_medios_verificacion as meca',
		],  
		[
		'i.idn_id ',
		'i.idn_nombre ',
		'i.idn_objetivo ',
		'i.idn_nombre_responsable ',
		'i.idn_medios_verificacion ',
		],
	
		[
		'id',
		'nombre',
		'objetivo',
		'nomr',
		'meca',
		],1,"text") as JSON
}

	
	
	
	
	def tabla(){
		
		[dependencia:params.id]
		
		
	}
	
	
	def visor(){
		
		
		def indicadorInstance = Indicador.get(params.id);
		
		
	
		def formula =  indicadorInstance?.formula?.sentencia
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		def List resultados= new ArrayList<Resultado>() 
		for(anio in 2005..2020){
		
			boolean  b = true
			for(v in variables){
			
				
				for(vari in indicadorInstance.variables){
				
						if(v==vari.clave){
							
									
									def bandera= true
									def origenDatos = Variable.findByLocalidadAndEstadoAndMunicipioAndRegionAndAnio(vari.localidad,vari.estado,vari.municipio,vari.region,anio)
									
									if(origenDatos){
									
											for(cat in  vari.categorias){
											
												if(!encuentraVariablesAndCategoria(origenDatos,cat))
													bandera=false
											}
											
											
											if(bandera){
													System.out.println("numero "+origenDatos.poblacionTotal);
													switch (vari.poblacion.clave) {
													case "T":
																formula=formula.replaceAll(String.valueOf(v), String.valueOf(origenDatos.poblacionTotal))
														break;
													case "H":
																formula=formula.replaceAll(String.valueOf(v),String.valueOf(origenDatos.hombres))
													break;
													case "M":
																formula=formula.replaceAll(String.valueOf(v),String.valueOf(origenDatos.mujeres))
													break;
													}
											}else{
												b=false
											}
									}			
									else{
										b=false
									}
									
												
						}
		
				}
	
			
			}
			
			if(b){
				System.out.println(formula);
				def resultado = new Resultado()
				ScriptEngineManager script = new ScriptEngineManager();
				ScriptEngine js = script.getEngineByName("JavaScript");
				try {
					
					resultado.indicador =js.eval("eval('"+formula+"')")
					System.out.println(resultado.indicador);
					resultado.anio=anio
					resultados.add(resultado)
					
				} catch (ScriptException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
			}
			
			
		}
		[indicadorInstance:indicadorInstance,resultados:resultados]
	}
	
	
	def encuentraVariablesAndCategoria(Variable v, Categoria cat){
	
		def ban = false
		System.out.println(" id variable  " +v.id);
		for(c in v.categorias){
			
			System.out.println("categoria de la variable : "+ c.descripcion+"  categoria p : "+cat.descripcion);
			if(c.id==cat.id)
				ban=true
		}

	return ban;
	}
	
    def save() {
        def indicadorInstance = new Indicador(params)
        
		
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		
		def resultado
		
		for(v in variables){

				def numCategorias= params.getAt("numCategorias_"+v)
				
				def localidad=null
				def municipio=null
				def region=null
				def estado=null
				
				if(params.getAt("localidad_"+v)!="null")
					localidad=  Localidad.get(params.getAt("localidad_"+v).toInteger())
				
				
				if(params.getAt("municipio_"+v)!="null")
					municipio=  Municipio.get(params.getAt("municipio_"+v).toInteger())
			
				
				
				if(params.getAt("region_"+v)!="null")
					region =    Region.get(params.getAt("region_"+v).toInteger())
				
				
				
				if(params.getAt("estado_"+v)!="null")
					estado =    Estado.get(params.getAt("estado_"+v).toInteger())
				
				
				
				
				
				def poblacion = Poblacion.get(params.getAt("poblacion_"+v))
			
				def dVariable = new  DVariable()
				dVariable.clave=v
				dVariable.descripcion=params.getAt("descripcion_"+v)
				dVariable.localidad=localidad
				dVariable.municipio=municipio
				dVariable.region=region
				dVariable.estado=estado
				dVariable.poblacion=poblacion
				
				
				for(i in 1 .. numCategorias){
					
					
				     	def categoria = Categoria.get(params.getAt("categoria_"+i+"_"+v))
						if(categoria)
						{
							dVariable.addToCategorias(categoria)
						}
				}
				
				indicadorInstance.addToVariables(dVariable)
				
		}
		
				if (!indicadorInstance.save(flush: true)) {
					render(view: "create", model: [indicadorInstance: indicadorInstance])
					return
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

	
	
	
	def categorias(){
		
		def var =params.var
		def con= params.con
		
		[var:var,con:con]
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
		
		
		def variable=params.id
		
		
		
		
		def localidad=  Localidad.get(params.getAt("localidad_"+variable))
		def municipio=  Municipio.get(params.getAt("municipio_"+variable))		
		def region =    Region.get(params.getAt("region_"+variable)) 
		def estado =    Estado.get(params.getAt("estado_"+variable)) 
		def rInicial =  params.getAt("edadDe_"+variable)
		def rFinal =    params.getAt("edadHasta_"+variable)
		def poblacion = params.getAt("poblacion_"+variable)

	
		
		//def rango = RangoEdad.findAllByMinimoBetweenAndMaximo(rInicial,rFinal);
		
		
	//	System.out.println(rango);
		
		def var = Variable.findAllByLocalidadAndMunicipioAndRegionAndEstadoAndAnio(localidad,municipio,region,estado,anio);
	

		
		[var:var,nomVar:params.id]
		
			
	}
	
	
	
	
	
	def subirArchivo(){
		
		
	
			try{
			def path = grailsApplication.config.mx.indesti.ace.valores.directoriouploads
			def fBase = request.getFile('fileBase')
			if(!fBase.empty) {
				fBase.transferTo( new File(path + fBase.originalFilename.toString()) )
			}
	
			File archivo_ = new File(path + fBase.originalFilename.toString())
			def archivo = new LeeArchivo(archivo_);
			
				
				}catch (Exception e) {
					println(e.getMessage())
					e.printStackTrace()
					renglonesMalos.add(row);
					
					contadorMalos++
				}
				
		
			
			
			[aerolinea : aerolinaInstance, total : renglones.size(), buenos : contadorBuenos, malos : contadorMalos ,rMalos:renglonesMalos,claveSHA:nomImage,mensaje:mensaje]
			
	
			
		}
	Á
	
	
	
	
	
	
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
