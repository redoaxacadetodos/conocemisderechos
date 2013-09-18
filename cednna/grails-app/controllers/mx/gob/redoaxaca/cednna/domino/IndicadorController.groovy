package mx.gob.redoaxaca.cednna.domino

import com.redoaxaca.java.Combo
import com.redoaxaca.java.ComboVariable
import com.redoaxaca.java.LeeArchivo
import com.redoaxaca.java.RVariable;
import com.redoaxaca.java.Resultado
import com.redoaxaca.java.ResultadoIndicador
import com.redoaxaca.java.ResultadoTemporal
import grails.converters.JSON
import javax.script.ScriptEngine;
import javax.script.ScriptEngineManager;
import javax.script.ScriptException;

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import groovy.sql.Sql

@Secured(["hasRole('ROLE_ADMIN')"])
class IndicadorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def sessionFactory
	def dataTablesService
	
    def index() {
        
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
		
		
		def indicadorInstance = Indicador.get(5);
		def opcion= 1;
	
		def formula =  indicadorInstance?.formula?.sentencia
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		def List resultados= new ArrayList<ResultadoIndicador>()
		def List<RVariable> rVariables = new ArrayList<RVariable>()
	
		for(anio in 2005..2020){
		
			boolean  b = true
			for(v in variables){
			
				RVariable temVar= new RVariable()
				
				temVar.letra=v
				
				for(vari in indicadorInstance.variables){
				
						if(v==vari.clave){
							
										switch (opcion) {
											
											case 1:
													def sql = new Sql(sessionFactory.currentSession.connection())
												
												
													def query = "SELECT o.region_id,"+
																"COALESCE(cr.crg_descripcion, ''::character varying) AS region,"+
																"sum(o.mujeres) as mujeres, "+
																"sum(o.hombres) as hombres , "+
																"sum(o.total)  as total "+
																"FROM (SELECT cat_variable.cvv_clave AS clave, "+
																				"	cat_variable.cvv_descripcion AS descripcion, "+
																				"	cat_variable.cvv_region AS region_id, "+
																				"	cat_variable.cvv_municipio AS municipio_id, "+
																				"	cat_variable.cvv_localidad AS localidad_id, "+
																				"    cat_variable.cvv_mujeres AS mujeres, "+
																				"	cat_variable.cvv_hombres AS hombres, "+
																				"	cat_variable.cvv_poblacion_total AS total "+
																" FROM cat_variable "+
																"where "+
																" cvv_clave='"+vari.claveVar+"' and   cvv_region is not null     and   cvv_anio="+anio+" and   cvv_municipio is not null  "
																
																if(vari.categorias){
																	
																	query=query+" and "+
																	"("
																	
																}
																def queryTipo="select ctt_id from cat_categoria ca ,cat_tipo ct where ca.cct_ctt_id=ct.ctt_id "+
																 " and ca.cct_id in ( select cdc_cct_id from cat_dvariable_categoria where cdc_cdv_id = "+ vari.id+") group by ctt_id"
													
																def resultTipo
																def result = sql.rows(queryTipo.toString())
																
																
																								def tamTipo =result.size()
																								def cc=1
																								result?.each
																								{
																									
																								
																									def queryCat="select cct_id from cat_categoria ca ,cat_tipo ct where ca.cct_ctt_id=ct.ctt_id "+
																									" and ca.cct_id in ( select cdc_cct_id from cat_dvariable_categoria where cdc_cdv_id = "+  vari.id+" ) and ctt_id ="+ it.ctt_id
																									resultTipo= sql.rows(queryCat.toString())
																									def tam =resultTipo.size()
																									def c=1
																											resultTipo?.each
																											{
																												 query=query+" cvv_id in (select  cvc_cvv_id from cat_variable_categoria where cvc_cct_id = "+it.cct_id+")  "
																												 if(c!=tam)
																												 	query=query+" or "
																												 c++
																											}
																									
																									if(cc!=tam)
																									query=query+" and "
																									
																									cc++
																								
																								}
																								
																								if(vari.categorias){
																									
																									query=query+"  ) "
																									
																								}
																								
																query=query+") o LEFT JOIN cat_region cr ON cr.crg_id = o.region_id LEFT JOIN cat_municipio cm ON cm.mun_id = o.municipio_id LEFT JOIN cat_localidad cl ON cl.ctl_id = o.localidad_id "+
																"GROUP BY "+
																"o.region_id,  "+ 
																"region"
													
													
																//System.out.println("LA CONSULTA ES : "+query);
																def resultTotal = sql.rows(query.toString())
																resultTotal?.each
																{
																	System.out.println("LA CONSULTA ES : "+query);
																	System.out.println("Variable "+vari.clave+" Region-ID : "+it.region_id + " Region : "+it.region + " Mujeres : "+it.mujeres+" Hombres : "+it.hombres +" -- "+anio)
																	ResultadoTemporal valorTem = new ResultadoTemporal()
																	switch (it.cdv_pob_id) {
																	case 1:
																					valorTem.region=it.region
																					valorTem.idRegion =it.region_id
																					
																					temVar.valores.add(valorTem)
																		break;

																	case 2:			
																					temVar
																		break;
																					
																	case 3:
																					temVar
																		break;
																	default:
																		break;
																	}
																}
														
																
											break;
											
											case 2:
									
											break;
											
											case 3:
							
											break;
											
											case 4:
											
									
											
											break;
										
										}
									
							
							
							
							
							
									
												
						}
		
				}
	
			
			}
			
			
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
		[indicadorInstance:indicadorInstance,resultados:resultados]
	}
	
	
	
	def getOrigenDatos(clave,indicador){
		
		
		
		
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
	
	
	def  descripciones(){
		
	  
		def cvar= new ArrayList<ComboVariable>()
		
		def sql = new Sql(sessionFactory.currentSession.connection())

		def query = "select cvv_clave as clave ,cvv_descripcion as descripcion  from cat_variable group by cvv_clave,cvv_descripcion"
		
		
					def result = sql.rows(query.toString())
					
					
					
								cvar=result?.each
								{
								
									
								  def v= new  Combo();
								  
								  System.out.println("Variable  : "+it.clave);
								  
								
								  v.descripcion=it.clave+"-"+it.descripcion;
								  v.clave=it.clave
									
								  cvar.add(v)
								}
					 
					
		render cvar as JSON
	}
	
	
	
    def save() {
        def indicadorInstance = new Indicador(params)
        
		
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		
		if(params.fechaActua){
			Date finicial = Date.parse("dd/MM/yyyy", params.fechaActua)
			
			indicadorInstance.fechaActualizacion=finicial
		
		}
		def resultado
		
		for(v in variables){

				def numCategorias= params.getAt("numCategorias_"+v)
				
								
				
				
				
				def poblacion = Poblacion.get(params.getAt("poblacion_"+v))
			
				def dVariable = new  DVariable()
				dVariable.clave=v
				dVariable.descripcion=params.getAt("descripcion_"+v)
				dVariable.claveVar=params.getAt("claveVar_"+v)
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
		
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		
		
		if(params.fechaActua){
			Date finicial = Date.parse("dd/MM/yyyy", params.fechaActua)
			
			indicadorInstance.fechaActualizacion=finicial
		
		}
		def resultado
		for(v in variables){
			
							def numCategorias= params.getAt("numCategorias_"+v)
							
								
							def poblacion = Poblacion.get(params.getAt("poblacion_"+v))
						
							def dVariable = new  DVariable()
							dVariable.clave=v
							dVariable.descripcion=params.getAt("descripcion_"+v)
							dVariable.claveVar=params.getAt("claveVar_"+v)
							
							dVariable.poblacion=poblacion
							
							indicadorInstance.variables.removeAll{ id!=null }
//							if (!indicadorInstance.save(flush: true)) {
//								render(view: "edit", model: [indicadorInstance: indicadorInstance])
//								return
//							}
							
							
//							for(i in 1 .. numCategorias){
//								
//								
//									 def categoria = Categoria.get(params.getAt("categoria_"+i+"_"+v))
//									if(categoria)
//									{
//										dVariable.addToCategorias(categoria)
//									}
//							}
//							
//							indicadorInstance.addToVariables(dVariable)
							
					}
		
		
        if (!indicadorInstance.save(flush: true)) {
            render(view: "edit", model: [indicadorInstance: indicadorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicador.label', default: 'Indicador'), indicadorInstance.id])
        redirect(action: "show", id: indicadorInstance.id)
    }

	
	
	
	def categorias(){
		
		def var =params.id
		def con= params.con
		
		[var:var,con:con]
	}
	
	
	def buscadorVariable(){
		
		
		def formula = Formula.get(params.id);
		def var
		def resultado 
		if(formula)
		{
			
			var= formula.variables.split("\\|")

			if(params.idIndicador!="undefined"){
				def indicador =  Indicador.get(params.idIndicador);
				if(indicador){
					
					if(indicador.formula.id==formula.id){
						var= indicador.variables
					[variable:var,sentencia:formula.sentencia,descripcion:formula.descripcion]
					
					}
					else{
						
						def vare = new ArrayList<DVariable>()
						for(s in var){
							
							def tem= new  DVariable()
							tem.clave=s
							vare.add(tem);
							
						}
						[variable:vare,sentencia:formula.sentencia,descripcion:formula.descripcion]
					}
				}
				
				
			}
			else{
				def vare = new ArrayList<DVariable>()
						for(s in var){
							
							def tem= new  DVariable()
							tem.clave=s
							vare.add(tem);
							
						}
				[variable:vare,sentencia:formula.sentencia,descripcion:formula.descripcion]
			}
		}		
		
		
		
	
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

	


		def var = Variable.findAllByLocalidadAndMunicipioAndRegionAndEstadoAndAnio(localidad,municipio,region,estado,anio);
	

		
		[var:var,nomVar:params.id]
		
			
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
	
	
	
	def semaforo(){
		
		
	}
	
	
	def actualizarSemaforo(){
		def dependencia = Dependencia.get(params.id)
		def indicadores = Indicador.findAllByDependencia(dependencia)
		render(template:"indicadorSemaforo", model:[indicadores:indicadores])
	}
}
