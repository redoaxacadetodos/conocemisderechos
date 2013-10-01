package mx.gob.redoaxaca.cednna.publico

import com.redoaxaca.java.DetalleIndicador
import com.redoaxaca.java.RVariable
import com.redoaxaca.java.Resultado
import com.redoaxaca.java.ResultadoIndicador
import com.redoaxaca.java.ResultadoTemporal
import com.redoaxaca.kml.GuardarCoordenadas
import com.redoaxaca.kml.ObtenerCoordenadas
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.sql.Sql

import java.text.DecimalFormat
import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

import mx.gob.redoaxaca.cednna.domino.*


@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
class PublicoController {
	
	def sessionFactory
	def dataSource

    def index() { 
		
	}
	
	def semaforo = {
		
	}
	
	def calendario = {
		
	}
	
	def directorio = {
	}
	
	def indicadores = {
		
	}
		
	def insertarCoordenadas = {		
		/*
		GuardarCoordenadas gc = new GuardarCoordenadas(Municipio) 				
		gc.guardarCoordenadas(grailsAttributes.getApplicationContext().getResource("kml/municipios.kml").getFile())
		
		GuardarCoordenadas gc = new GuardarCoordenadas(Estado)
		gc.guardarCoordenadas(grailsAttributes.getApplicationContext().getResource("kml/entidad.kml").getFile())
		 */		
		
		GuardarCoordenadas gcr = new GuardarCoordenadas(Region)
		gcr.guardarCoordenadas(grailsAttributes.getApplicationContext().getResource("kml/regiones.kml").getFile())
	}
	
	def contacto = {
		
	}
	
	def ayuda = {
		
	}	
	
	def actualizarMapa(Long id) {
		int tipo = params.idTipo.toInteger()
		
		DetalleIndicador detalleIndicador = visorIndicador(id,tipo)
		def resultadosIndicador = detalleIndicador.resultados
		
		def ubicaciones = []
		def prueba = []
		def coordenadas = []
		def nombreCoordenadas = []
		def aux = [:]
		def coordenadasList = []
		def sql = ""
		def sqlNombre=""		
						
		resultadosIndicador.each { resultado ->
			
			switch(tipo){
				case 1:					
					def idEstado = 20
					sqlNombre = "select ent_descripcion descripcion from cat_entidad where ent_id = "+idEstado					
					sql = "select coor.latitud, coor.longitud from coordenada coor join cat_entidad_coordenada ccoo on (coor.id = ccoo.coordenada_id) where ccoo.estado_coordenadas_id = "+idEstado									
					break
				case 2:
					sqlNombre = "select crg_descripcion descripcion from cat_region where crg_id ="+resultado.idRegion
					sql = "select coor.latitud, coor.longitud from coordenada coor join cat_region_coordenada regi on (coor.id = regi.coordenada_id) where regi.region_coordenadas_id = "+resultado.idRegion										
					break
				case 3:					
					sqlNombre = "select mun_descripcion descripcion from cat_municipio where mun_id = "+resultado.idMunicipio
					sql = "select coor.latitud, coor.longitud from coordenada coor join cat_municipio_coordenada muni on (coor.id = muni.coordenada_id) where muni.municipio_coordenadas_id = "+resultado.idMunicipio									
					break				
			}
			def db = new Sql(dataSource)
			def result  = db.rows(sql)
			result.each {
				coordenadas.add("new google.maps.LatLng(" + it?.latitud + ","+it?.longitud+")")
			}
			coordenadasList.add(coordenadas)
			
			def db2 = new Sql(dataSource)			
			def nombre  = db2.rows(sqlNombre)
			def datos = []
			def anios = []
			resultado.resultados.each { r ->				
				anios.add(r?.anio)
				datos.add(r?.indicador==0 ? 0 : (Math.round( (r?.indicador) * 100.0 ) / 100.0) )
				Double indi = new Double(r?.indicador)				
				System.out.println("resultado: "+indi);				
			}
			System.out.println("anio: "+anios)
			nombre.each {
				nombreCoordenadas.add("'"+it.descripcion+"'")
				ubicaciones.add(["descripcion": it.descripcion, "anios":anios, "datos": datos])
				//aux.put("ubicacion",["descripcion": it.descripcion, "anios":anios, "datos": datos])
			}
			
						
		}		
		aux.put("lugar",["ubicaciones":ubicaciones])
		//System.out.println("tam: "+aux.size())
		def jsodata = aux as JSON
		/*
		aux.each {
			System.out.println(it)
		}
		
		*/						
		Double p = 2.1231239034953409534345345345345345223423423432423534353453453454
		 
		System.out.println(p)
		render(template:"mapa", model:[ubicaciones:prueba, nombreCoordenadas: nombreCoordenadas, coordenadasList:coordenadasList, resultadosIndicador:resultadosIndicador, aux:jsodata])
	}
	
	def actualizarTablaIndicador(Long id){
		int tipo = params.idTipo.toInteger()
		def indicador = Indicador.get(id)		
		
		DetalleIndicador detalleIndicador = visorIndicador(id,tipo)
		def resultadosIndicador = detalleIndicador.resultados
		
		resultadosIndicador.each{ re ->
			System.out.println("tam: "+re.resultados.size() + " region: "+ re.region)
			re.resultados.each { r ->
				System.out.println("indi anio: "+r.anio + " "+ r.indicador)
			}
		}
		
		render (template:"tablaIndicador", model:[tipo:params.idTipo, resultadosIndicador:resultadosIndicador])	
	}
	
	def actualizarDatosCalculo(Long id){
		int tipo = params.idTipo.toInteger()
		def indicador = Indicador.get(id)
		
		DetalleIndicador detalleIndicador = visorIndicador(id,tipo)
		def resultadosIndicador = detalleIndicador.resultados
		
		resultadosIndicador.each{ re ->
			System.out.println("tam: "+re.resultados.size() + " region: "+ re.region)
			re.resultados.each { r ->
				System.out.println("indi anio: "+r.anio + " "+ r.indicador)
			}
		}
		
		render (template:"datosCalculo", model:[indicadorInstance: indicador, datosCalculo: detalleIndicador.rVariables])
	}
	
	def enviarCorreo(Long id) {
		def indicador = Indicador.get(id)
		
		 sendMail {
			 to indicador?.mailResponsable		 	
			subject "Actualización"
			body 'Estimado '+ indicador?.nombreResponsable +
				' le recordamos que debe actualizar el indicador ' +
				indicador?.nombre + "."
		 }
	}
	
	def actualizarSemaforo(){
		def dependencia = Dependencia.get(params.id)
		def indicadores = Indicador.findAllByDependencia(dependencia)
		render(template:"indicadorSemaforo", model:[indicadores:indicadores])
	}
	
	def infoIndicador (Long id) {
		def eje = Eje.get(id)
		if(eje){
			def sql = "select * from division where eje_id = " + eje.id
			def db = new Sql(dataSource)
			def divisiones  = db.rows(sql)			
			render(template:"division", model: [divisiones: divisiones])
		}else{
		redirect(action:"indicadores")
		}		
	}
	
	def detalleIndicador (Long id){		
		if(params.infoIndicador=='true'){			
			def eje = Eje.get(id)
			if(eje){								
				def sql = "select * from division where eje_id = " + eje.id
				def db = new Sql(dataSource)
				def divisiones  = db.rows(sql)				
				[divisiones: divisiones]
			}else{
				redirect(action:"indicadores")
			}
			
		}else{
			def indicador = Indicador.get(id)
			if(indicador){
						
			DetalleIndicador detalleIndicador = visorIndicador(id,1)
			def resultadosIndicador = detalleIndicador.resultados
			def resultados = []
			def coordenadasList = []
			
			resultadosIndicador.each { r ->
				resultados = r.resultados
			}
			
			//Creación de arreglo para Highcharts
			def series = []
			def categorias = []
			def datos = []			
			def a = [title: [text: indicador?.nombre?.toString(), x: -20]]					
			a.put("yAxis", [title: [text: '%']])	
			a.put("tooltip", [valueSuffix: '%'])
			a.put("legend", [layout: "vertical", align: "right", verticalAlign: "middle", borderWidth: 0])		
			resultados.each { result ->
				categorias.add(result?.anio)			
				datos.add(result?.indicador)					
			}		
			a.put("xAxis", [categories: categorias] )		
			def serie = [name: "Indicador", data: datos]
			series << serie
			a.put("series", series)
			
			//Convertir el arreglo a JSON
			def jsodata = a as JSON
			
			//Buscar datos para Google Maps
			def ubicaciones = []
			def aux = [:]
			def ubicacioneString = []
			def coordenadas = []	
			def nombreCoordenadas = []
						
			resultadosIndicador.each { resultado ->		
				def idEstado = 20
				def sqlNombre = "select ent_descripcion descripcion from cat_entidad where ent_id = "+idEstado
				def sql = "select coor.latitud, coor.longitud from coordenada coor join cat_entidad_coordenada ccoo on (coor.id = ccoo.coordenada_id) where ccoo.estado_coordenadas_id = "+idEstado
				def db = new Sql(dataSource)
				def result  = db.rows(sql)						
				
				result.each {
					coordenadas.add("new google.maps.LatLng(" + it?.latitud + ","+it?.longitud+")")					
				}
				coordenadasList.add(coordenadas)				
				
				def db2 = new Sql(dataSource)
				def nombre  = db2.rows(sqlNombre)
				def datosIndicador = []
				def anios = []
				resultado.resultados.each { r ->
					anios.add(r?.anio)
					datosIndicador.add(r?.indicador)
				}
				
				nombre.each {
					nombreCoordenadas.add("'"+it.descripcion+"'")
					ubicaciones.add(["descripcion": it.descripcion, "anios":anios, "datos": datosIndicador, "coordenadas": coordenadas])
				}
			}
			
			aux.put("lugar",["ubicaciones":ubicaciones])
			
			def jsondata = aux as JSON
			
			System.out.println("variables: "+detalleIndicador.rVariables)
			
					
			[aux: jsondata, indicadorInstance: indicador, resultados:resultados, tablaJSON: jsodata, ubicaciones: ubicacioneString, resultadosIndicador:resultadosIndicador, tipo:'1',coordenadasList:coordenadasList, nombreCoordenadas:nombreCoordenadas, datosCalculo: detalleIndicador.rVariables]
			}
			else{
				redirect(action:"indicadores")
			}
		}
	}
	
	def DetalleIndicador visorIndicador(Long id, int idTipo){
		
		def indicadorInstance = Indicador.get(id);
		def opcion= idTipo;
			
		def formula =  indicadorInstance?.formula?.sentencia
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		def List<ResultadoIndicador> resultados= new ArrayList<ResultadoIndicador>()
		
		def List<RVariable> resutaldoVariables = new ArrayList<RVariable>()
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
																	resutaldoVariables.add(temVar)
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
													
																		rTemp.indicador =js.eval("eval('"+formula+"')")
																		rTemp.anio=anio
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
//												System.out.println("Valor final : "+listTemp.size()+ "anio"+anio );
												listTemp.each {
												actual->
												def ban=0
															if(resultados.size()>0){
																
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.indicador
																resultados.get(0).resultados.add(res)
//																System.out.println("Veces que entro al sistema 1 ");
																
															}else{
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.indicador
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
													
													
																//System.out.println("LA CONSULTA ES : "+query);
																def resultTotal = sql.rows(query.toString())
																
																if(resultTotal.size()>0){
																	//System.out.println("LA CONSULTA ES : "+query);
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
																	resutaldoVariables.add(temVar)
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
													
																		rTemp.indicador =js.eval("eval('"+formula+"')")
																		rTemp.region= base.region
																		rTemp.idRegion= base.idRegion
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
																	
																			if(it.idRegion==actual.idRegion){
																				Resultado res= new Resultado()
																				res.anio=actual.anio
																				res.indicador=actual.indicador
																				it.resultados.add(res)
																				ban=1
																			}
																}
																
																
																if(ban==1){
																	Resultado res= new Resultado()
																	res.anio=actual.anio
																	res.indicador=actual.indicador
																	ResultadoIndicador ri =  new  ResultadoIndicador()
																	ri.region=actual.region
																	ri.idRegion=actual.idRegion
																	ri.resultados.add(res)
																	resultados.add(ri)
																	 
																}
															}else{
																Resultado res= new Resultado()
																res.anio=actual.anio
																res.indicador=actual.indicador
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
																	resutaldoVariables.add(temVar)
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
													
																		rTemp.indicador =js.eval("eval('"+formula+"')")
																		rTemp.region= base.region
																		rTemp.idRegion= base.idRegion
																		rTemp.municipio= base.municipio
																		rTemp.idMunicipio= base.idMunicipio
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
																	
																			if(it.idMunicipio==actual.idMunicipio){
																				Resultado res= new Resultado()
																				res.anio=actual.anio
																				res.indicador=actual.indicador
																				it.resultados.add(res)
																				ban=1
																			}
																}
																
																
																if(ban==1){
																	Resultado res= new Resultado()
																	res.anio=actual.anio
																	res.indicador=actual.indicador
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
																res.indicador=actual.indicador
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
																	resutaldoVariables.add(temVar)
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
													
																		rTemp.indicador =js.eval("eval('"+formula+"')")
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
																				res.indicador=actual.indicador
																				it.resultados.add(res)
																				ban=1
																			}
																}
																
																
																if(ban==1){
																	Resultado res= new Resultado()
																	res.anio=actual.anio
																	res.indicador=actual.indicador
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
																res.indicador=actual.indicador
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
		
		System.out.println("rVariable tam: "+resutaldoVariables.size())
		
		DetalleIndicador detalleIndicador = new DetalleIndicador()		
		detalleIndicador.resultados = resultados
		detalleIndicador.rVariables = resutaldoVariables 
		
		
		return detalleIndicador
						
		//[indicadorInstance:indicadorInstance,resultados:resultados]
		
		
	}
}	