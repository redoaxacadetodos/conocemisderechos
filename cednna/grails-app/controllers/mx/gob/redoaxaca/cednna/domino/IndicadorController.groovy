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

import mx.gob.redoaxaca.cednna.seguridad.Usuario;

import org.springframework.dao.DataIntegrityViolationException
import grails.plugins.springsecurity.Secured
import groovy.sql.Sql

@Secured(['ROLE_DEP'])
class IndicadorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def sessionFactory
	def dataTablesService
	def springSecurityService
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
    def index() {
        
    }
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		def usuario = springSecurityService.currentUser
		def dependencia =  usuario.dependencia
		
        [indicadorInstanceList: Indicador.list(params), indicadorInstanceTotal: Indicador.count(),dependencia:dependencia?.id]
    }

    def create() {
		
	
        [indicadorInstance: new Indicador(params)]
    }

	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
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

	
	
	
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
	def tabla(){
		
		[dependencia:params.id]
		
		
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
	def visor(){
		
		
		def indicadorInstance = Indicador.get(15901);
		def opcion= 3;
	
		def formula =  indicadorInstance?.formula?.sentencia
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		def List<ResultadoIndicador> resultados= new ArrayList<ResultadoIndicador>()
		
		def List<RVariable> rVariables = new ArrayList<RVariable>()
		def List<ResultadoTemporal> listTemp = new ArrayList<ResultadoTemporal>()
		RVariable temVar
		
		def num=0
		def letra
		def valorBase
		
		
		for(anio in 2005..2020){
		
			boolean  b = true
				
										switch (opcion) {
											
											case 1:
											
											
											/***
											 * PROCESO DE SALIDA POR ESTADO
											 *
											 * */
											
												
											/***
											 * Comienza la busqueda en el origen de datos en base a las variable
											 * */
											
											for(vari in indicadorInstance.variables){
												
													def sql = new Sql(sessionFactory.currentSession.connection())
												
													def query = "SELECT "+
																"clave, "+
																"sum(o.mujeres) as mujeres, "+
																"sum(o.hombres) as hombres , "+
																"sum(o.total) as total "+
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
																" cvv_clave='"+vari.claveVar+"'    and   cvv_anio="+anio+" "
																
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
																								
																query=query+") o LEFT JOIN cat_region cr ON cr.crg_id = o.region_id LEFT JOIN cat_municipio cm ON cm.mun_id = o.municipio_id LEFT JOIN cat_localidad cl ON cl.ctl_id = o.localidad_id  group by clave"
															
															
													
																//System.out.println("LA CONSULTA ES : "+query);
																def resultTotal = sql.rows(query.toString())
																
																if(resultTotal.size()>0){
//																	System.out.println("LA CONSULTA ES : "+query);
																	temVar= new RVariable()
																	temVar.letra=vari.clave
																
																		resultTotal?.each
																		{
																			//System.out.println("LA CONSULTA ES : "+query);
																		System.out.println("Variable "+vari.clave+" Mujeres : "+it.mujeres+" Hombres : "+it.hombres +" -- "+anio)
																			ResultadoTemporal valorTem = new ResultadoTemporal()
																			switch (vari.poblacion.clave) {
																			case "H":
																			
																							
																							valorTem.indicador=it.hombres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
		
																			case "M":
																						
																							valorTem.indicador=it.mujeres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																							
																			case "T":
																							
																							valorTem.indicador=it.total
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																			default:
																				break;
																			}
																		}
														
																	rVariables.add(temVar)
																}
																
												}
														
											/***
											 * Comienza el calculo del indicador en base a las variables
											 * */
//											System.out.println("ANIO : "+anio+ "  -   variables "+rVariables.size());
											if(rVariables.size()>0){
												
												
													rVariables.each {
													var->
															
																		formula=formula.replaceAll(var.letra, String.valueOf(var.valores.get(0).indicador))
												
													}
													System.out.println(formula);
													
													ResultadoTemporal rTemp = new ResultadoTemporal()
													
													
																	ScriptEngineManager script = new ScriptEngineManager();
																	ScriptEngine js = script.getEngineByName("JavaScript");
																	try {
													
																		rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
																		rTemp.anio=anio
																		listTemp.add(rTemp)
													
																	} catch (Exception e) {
																		// TODO Auto-generated catch block
																		//e.printStackTrace();
																	}
													
													
													formula= indicadorInstance?.formula?.sentencia
													
													
													
												}
												
												/***
												 * Comienza el proceso de ordenamiento para salida
												 * */
//												System.out.println("Valor final : "+listTemp.size()+ "anio"+anio );
												listTemp.each {
												actual->
												def ban=0
															if(resultados.size()>0){
																
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.resultadoIndicador
																resultados.get(0).resultados.add(res)
//																System.out.println("Veces que entro al sistema 1 ");
																
															}else{
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.resultadoIndicador
																ResultadoIndicador ri =  new  ResultadoIndicador()
																
																ri.resultados.add(res)
																resultados.add(ri)
//																System.out.println("Veces que entro al sistema 2 ");
															}
													}
												
												
												
												
											
											
											
											num=0
											rVariables= new ArrayList<RVariable>()
											listTemp = new ArrayList<ResultadoTemporal>()
											
											
											
													
											break;
											
											case 2:
									
											/***
											 * PROCESO DE SALIDA POR REGIONES
											 * 
											 * */
											
												
											/***
											 * Comienza la busqueda en el origen de datos en base a las variable
											 * */
											
											for(vari in indicadorInstance.variables){
												
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
													
													
															//	System.out.println("LA CONSULTA ES : "+query);
																def resultTotal = sql.rows(query.toString())
																
																if(resultTotal.size()>0){
																	//System.out.println("LA CONSULTA ES : "+query);
																	temVar= new RVariable()
																	temVar.letra=vari.clave
																
																		resultTotal?.each
																		{
																			
																			//System.out.println("Variable "+vari.clave+" Region-ID : "+it.region_id + " Region : "+it.region + " Mujeres : "+it.mujeres+" Hombres : "+it.hombres +" -- "+anio)
																			ResultadoTemporal valorTem = new ResultadoTemporal()
																			switch (vari.poblacion.clave) {
																			case "H":
																							valorTem.region=it.region
																							valorTem.idRegion = it.region_id
																							valorTem.indicador=it.hombres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
		
																			case "M":
																							valorTem.region=it.region
																							valorTem.idRegion =it.region_id
																							valorTem.indicador=it.mujeres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																							
																			case "T":
																							valorTem.region=it.region
																							valorTem.idRegion =it.region_id
																							valorTem.indicador=it.total
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																			default:
																				break;
																			}
																		}
														
																	rVariables.add(temVar)
																}
																
												}
														
											/***
											 * Comienza el calculo del indicador en base a las variables
											 * */
											
											if(rVariables.size()>0){
												
												num=rVariables.get(0).letra
												letra=rVariables.get(0).valores.size()
												
											
							
												rVariables.each {
													if( it.valores.size()<num){
														num=it.valores.size()
														letra=it.letra
														valorBase=it.valores
													}
												}
											
												valorBase.each {
												base->
												
											
													formula=formula.replaceAll(letra, String.valueOf(base.indicador))
													rVariables.each {
													var->
															if(var.letra!=letra){
																var.valores.each {
																	
																	if(base.idRegion==it.idRegion){
																		
																		formula=formula.replaceAll(var.letra, String.valueOf(it.indicador))
																	}
																}
															}
														
													}
													System.out.println(formula);
													
													ResultadoTemporal rTemp = new ResultadoTemporal()
													
													
																	ScriptEngineManager script = new ScriptEngineManager();
																	ScriptEngine js = script.getEngineByName("JavaScript");
																	try {
													
																		rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
																		rTemp.region= base.region
																		rTemp.idRegion= base.idRegion
																		rTemp.anio=base.anio
																		
																		//System.out.println("Region: "+base.idRegion+"-- "+base.region+"  Resultado indicador : "+ rTemp.resultadoIndicador);
																		listTemp.add(rTemp)
													
																	} catch (Exception e) {
																		// TODO Auto-generated catch block
																		//e.printStackTrace();
																	}
													
													
													formula= indicadorInstance?.formula?.sentencia
													
													
													
												}
												
												/***
												 * Comienza el proceso de ordenamiento para salida
												 * */
												
												
												
												listTemp.each {
												actual->
												def ban=0
															if(resultados.size()>0){
																
																resultados.each {
																	
																			if(it.idRegion==actual.idRegion){
																				Resultado res= new Resultado()
																				res.anio=actual.anio
																				res.indicador=actual.resultadoIndicador
																				it.resultados.add(res)
																				ban=1
																			}
																}
																
																
																if(ban!=1){
																	Resultado res= new Resultado()
																	res.anio=actual.anio
																	res.indicador=actual.resultadoIndicador
																	ResultadoIndicador ri =  new  ResultadoIndicador()
																	ri.region=actual.region
																	ri.idRegion=actual.idRegion
																	ri.resultados.add(res)
																	resultados.add(ri)
																	 
																}
															}else{
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.resultadoIndicador
																ResultadoIndicador ri =  new  ResultadoIndicador()
																ri.region=actual.region
																ri.idRegion=actual.idRegion
																ri.resultados.add(res)
																resultados.add(ri)
															
															}
													}
												
												
												
												
											
											
											}
											
											num=0
											rVariables= new ArrayList<RVariable>()
											listTemp = new ArrayList<ResultadoTemporal>()
											
											
											
											break;
											
											case 3:
							
											
											
											
											/***
											 * PROCESO DE SALIDA POR MUNICIPIOS
											 *
											 * */
											
												
											/***
											 * Comienza la busqueda en el origen de datos en base a las variable
											 * */
											
											for(vari in indicadorInstance.variables){
												
													def sql = new Sql(sessionFactory.currentSession.connection())
												
													def query = "SELECT o.region_id,"+
																"COALESCE(cr.crg_descripcion, ''::character varying) AS region,"+
																"o.municipio_id,"+
																"COALESCE(cm.mun_descripcion, ''::character varying) AS municipio,"+
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
																"region,"+
																"o.municipio_id, " +
																"municipio"
													
													
																//System.out.println("LA CONSULTA ES : "+query);
																def resultTotal = sql.rows(query.toString())
																
																if(resultTotal.size()>0){
																	//System.out.println("LA CONSULTA ES : "+query);
																	temVar= new RVariable()
																	temVar.letra=vari.clave
																
																		resultTotal?.each
																		{
																			
																			//System.out.println("Variable "+vari.clave+" Municipio-ID : "+it.municipio_id + " Municipio : "+it.municipio + " Mujeres : "+it.mujeres+" Hombres : "+it.hombres +" -- "+anio)
																			ResultadoTemporal valorTem = new ResultadoTemporal()
																			switch (vari.poblacion.clave) {
																			case "H":
																							valorTem.region=it.region
																							valorTem.idRegion = it.region_id
																							valorTem.municipio=it.municipio
																							valorTem.idMunicipio = it.municipio_id
																							valorTem.indicador=it.hombres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
		
																			case "M":
																							valorTem.region=it.region
																							valorTem.idRegion =it.region_id
																							valorTem.municipio=it.municipio
																							valorTem.idMunicipio = it.municipio_id
																							valorTem.indicador=it.mujeres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																							
																			case "T":
																							valorTem.region=it.region
																							valorTem.idRegion =it.region_id
																							valorTem.municipio=it.municipio
																							valorTem.idMunicipio = it.municipio_id
																							valorTem.indicador=it.total
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																			default:
																				break;
																			}
																		}
														
																	rVariables.add(temVar)
																}
																
												}
														
											/***
											 * Comienza el calculo del indicador en base a las variables
											 * */
											//System.out.println("Numero de resultados por a–o : "+rVariables.size());
											if(rVariables.size()>0){
												
												letra=rVariables.get(0).letra
												num=rVariables.get(0).valores.size()
												
											
							
												rVariables.each {
													if( it.valores.size()!=0){
														num=it.valores.size()
														letra=it.letra
														valorBase=it.valores
														
														
													}
												}
											
												valorBase.each {
												base->
												
											
													formula=formula.replaceAll(letra, String.valueOf(base.indicador))
													rVariables.each {
													var->
															if(var.letra!=letra){
																var.valores.each {
																	
																	if(base.idMunicipio==it.idMunicipio){
																		
																		formula=formula.replaceAll(var.letra, String.valueOf(it.indicador))
																	}
																}
															}
														
													}
													System.out.println(formula);
													
													ResultadoTemporal rTemp = new ResultadoTemporal()
													
													
																	ScriptEngineManager script = new ScriptEngineManager();
																	ScriptEngine js = script.getEngineByName("JavaScript");
																	try {
													
																		rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
																		rTemp.region= base.region
																		rTemp.idRegion= base.idRegion
																		rTemp.municipio= base.municipio
																		rTemp.idMunicipio= base.idMunicipio
																		rTemp.anio=base.anio
																		listTemp.add(rTemp)
													
																		//System.out.println("Region: "+base.idMunicipio+"-- "+base.municipio+"  Resultado indicador : "+ rTemp.resultadoIndicador);
																		
																	} catch (Exception e) {
																		// TODO Auto-generated catch block
																	//	e.printStackTrace();
																	}
													
													
													formula= indicadorInstance?.formula?.sentencia
													
													
													
												}
												 
												/***
												 * Comienza el proceso de ordenamiento para salida
												 * */
												
												listTemp.each {
												actual->
												def ban=0
															if(resultados.size()>0){
																
																resultados.each {
																	
																			if(it.idMunicipio==actual.idMunicipio){
																				Resultado res= new Resultado()
																				res.anio=actual.anio
																				res.indicador=actual.resultadoIndicador
																				it.resultados.add(res)
																				ban=1
																			}
																}
																
																
																if(ban!=1){
																	Resultado res= new Resultado()
																	res.anio=actual.anio
																	res.indicador=actual.resultadoIndicador
																	ResultadoIndicador ri =  new  ResultadoIndicador()
																	ri.region=actual.region
																	ri.idRegion=actual.idRegion
																	ri.municipio= actual.municipio
																	ri.idMunicipio= actual.idMunicipio
																	ri.resultados.add(res)
																	resultados.add(ri)
																	 
																}
															}else{
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.resultadoIndicador
																ResultadoIndicador ri =  new  ResultadoIndicador()
																ri.region=actual.region
																ri.idRegion=actual.idRegion
																ri.municipio= actual.municipio
																ri.idMunicipio= actual.idMunicipio
																ri.resultados.add(res)
																resultados.add(ri)
															
															}
													}
												
												
												
												
											
											
											}
											
											num=0
											rVariables= new ArrayList<RVariable>()
											
											listTemp = new ArrayList<ResultadoTemporal>()
											
											
											
											
											
											
											break;
											
											case 4:
											
											
											
											/***
											 * PROCESO DE SALIDA POR LOCALIDADES
											 *
											 * */
											
												
											/***
											 * Comienza la busqueda en el origen de datos en base a las variable
											 * */
											
											for(vari in indicadorInstance.variables){
												
													def sql = new Sql(sessionFactory.currentSession.connection())
												
													def query = "SELECT o.region_id,"+
																"COALESCE(cr.crg_descripcion, ''::character varying) AS region,"+
																"o.municipio_id,"+
																"COALESCE(cm.mun_descripcion, ''::character varying) AS municipio,"+
																"o.localidad_id,"+
																"COALESCE(cl.ctl_descripcion, ''::character varying) AS localidad,"+
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
																" cvv_clave='"+vari.claveVar+"' and   cvv_region is not null     and   cvv_anio="+anio+" and   cvv_municipio is not null  and   cvv_localidad is not null  "
																
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
																"region,"+
																"o.municipio_id, " +
																"municipio,"+
																"o.localidad_id,"+
																"localidad"
													
													
																//System.out.println("LA CONSULTA ES : "+query);
																def resultTotal = sql.rows(query.toString())
																
																if(resultTotal.size()>0){
																	System.out.println("LA CONSULTA ES : "+query);
																	temVar= new RVariable()
																	temVar.letra=vari.clave
																
																		resultTotal?.each
																		{
																			
																		//	System.out.println("Variable "+vari.clave+" Region-ID : "+it.region_id + " Region : "+it.region + " Mujeres : "+it.mujeres+" Hombres : "+it.hombres +" -- "+anio)
																			ResultadoTemporal valorTem = new ResultadoTemporal()
																			switch (vari.poblacion.clave) {
																			case "H":
																							valorTem.region=it.region
																							valorTem.idRegion = it.region_id
																							valorTem.municipio=it.municipio
																							valorTem.idMunicipio = it.municipio_id
																							valorTem.localidad=it.localidad
																							valorTem.idLocalidad = it.localidad_id
																							valorTem.indicador=it.hombres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
		
																			case "M":
																							valorTem.region=it.region
																							valorTem.idRegion =it.region_id
																							valorTem.municipio=it.municipio
																							valorTem.idMunicipio = it.municipio_id
																							valorTem.localidad=it.localidad
																							valorTem.idLocalidad = it.localidad_id
																							valorTem.indicador=it.mujeres
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																							
																			case "T":
																							valorTem.region=it.region
																							valorTem.idRegion =it.region_id
																							valorTem.municipio=it.municipio
																							valorTem.idMunicipio = it.municipio_id
																							valorTem.localidad=it.localidad
																							valorTem.idLocalidad = it.localidad_id
																							valorTem.indicador=it.total
																							valorTem.anio=anio
																							temVar.valores.add(valorTem)
																				break;
																			default:
																				break;
																			}
																		}
														
																	rVariables.add(temVar)
																}
																
												}
														
											/***
											 * Comienza el calculo del indicador en base a las variables
											 * */
											
											if(rVariables.size()>0){
												
												num=rVariables.get(0).letra
												letra=rVariables.get(0).valores.size()
												
											
							
												rVariables.each {
													if( it.valores.size()<num){
														num=it.valores.size()
														letra=it.letra
														valorBase=it.valores
													}
												}
											
												valorBase.each {
												base->
												
											
													formula=formula.replaceAll(letra, String.valueOf(base.indicador))
													rVariables.each {
													var->
															if(var.letra!=letra){
																var.valores.each {
																	
																	if(base.idLocalidad==it.idLocalidad){
																		
																		formula=formula.replaceAll(var.letra, String.valueOf(it.indicador))
																	}
																}
															}
														
													}
													System.out.println(formula);
													
													ResultadoTemporal rTemp = new ResultadoTemporal()
													
													
																	ScriptEngineManager script = new ScriptEngineManager();
																	ScriptEngine js = script.getEngineByName("JavaScript");
																	try {
													
																		rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
																		rTemp.region= base.region
																		rTemp.idRegion= base.idRegion
																		rTemp.municipio= base.municipio
																		rTemp.idMunicipio= base.idMunicipio
																		rTemp.localidad= base.localidad
																		rTemp.idLocalidad= base.idLocalidad
																		rTemp.anio=base.anio
																		listTemp.add(rTemp)
													
																	} catch (ScriptException e) {
																		// TODO Auto-generated catch block
																		e.printStackTrace();
																	}
													
													
													formula= indicadorInstance?.formula?.sentencia
													
													
													
												}
												
												/***
												 * Comienza el proceso de ordenamiento para salida
												 * */
											
												listTemp.each {
												actual->
												def ban=0
															if(resultados.size()>0){
																
																resultados.each {
																	
																			if(it.idLocalidad==actual.idLocalidad){
																				Resultado res= new Resultado()
																				res.anio=actual.anio
																				res.indicador=actual.resultadoIndicador
																				it.resultados.add(res)
																				ban=1
																			}
																}
																
																
																if(ban==1){
																	Resultado res= new Resultado()
																	res.anio=actual.anio
																	res.indicador=actual.resultadoIndicador
																	ResultadoIndicador ri =  new  ResultadoIndicador()
																	ri.region=actual.region
																	ri.idRegion=actual.idRegion
																	ri.municipio= actual.municipio
																	ri.idMunicipio= actual.idMunicipio
																	ri.localidad= actual.localidad
																	ri.idLocalidad= actual.idLocalidad

																	ri.resultados.add(res)
																	resultados.add(ri)
																	 
																}
															}else{
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.resultadoIndicador
																ResultadoIndicador ri =  new  ResultadoIndicador()
																ri.municipio= actual.municipio
																ri.idMunicipio= actual.idMunicipio
																ri.localidad= actual.localidad
																ri.idLocalidad= actual.idLocalidad
																ri.resultados.add(res)
																resultados.add(ri)
															
															}
													}
												
												
												
												
											
											
											}
											
											num=0
											rVariables= new ArrayList<RVariable>()
											listTemp = new ArrayList<ResultadoTemporal>()
											
									
											
											break;
										
										}
									
							
						
		}
		
		
					 
		resultados.each {
			
			System.out.println(it.idRegion + " : "+it.region+"    "+ it.idMunicipio + " : "+it.municipio);
			System.out.println("Tama–o  "+it.resultados.size());
			it.resultados.each {
				an->
					
					an.each {
							
						System.out.println("A–o : "+it.anio + " :Indicador  :"+it.indicador);
						
					}
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
	
	
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
	def categoriasByModulo(){
		
		def modulo = Eje.get(params.id)
		def divisiones
		if(modulo){
			
		divisiones=modulo.division
			
		}else{
		divisiones =Division.list()
		}
		render divisiones as JSON
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
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
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
		


		indicadorInstance.variables.each{
			it.delete()
		}
		indicadorInstance.variables.clear()
			
		
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
            render(view: "edit", model: [indicadorInstance: indicadorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicador.label', default: 'Indicador'), indicadorInstance.id])
        redirect(action: "show", id: indicadorInstance.id)
    }

	
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO'])
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
						
						System.out.println("Indicador variables : "+ var);
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
	
	@Secured(['ROLE_ADMIN'])
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
