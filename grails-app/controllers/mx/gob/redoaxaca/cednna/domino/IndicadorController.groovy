package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.sql.Sql
import groovy.time.TimeCategory

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import javax.script.ScriptException

import org.springframework.dao.DataIntegrityViolationException

import com.redoaxaca.java.Combo
import com.redoaxaca.java.ComboVariable
import com.redoaxaca.java.RVariable
import com.redoaxaca.java.Resultado
import com.redoaxaca.java.ResultadoIndicador
import com.redoaxaca.java.ResultadoTemporal

@Secured(['ROLE_DEP', 'ROLE_ADMIN'])
class IndicadorController {

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def sessionFactory
	def dataTablesService
	def springSecurityService
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
    def index() {
        
    }
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
		def usuario = springSecurityService.currentUser
		def dependencia =  usuario.dependencia
		
        [indicadorInstanceList: Indicador.list(params), indicadorInstanceTotal: Indicador.count(),dependencia:dependencia?.id]
    }
	
	def enviarCorreo(Long id) {
		def indicador = Indicador.get(id)
		def asunto = Valor.findByKey('asunto')
		def cuerpo = Valor.findByKey('cuerpo')
		
		sendMail {
			 to indicador?.mailResponsable
			subject asunto?.valor
			html  """
					${cuerpo?.valor} <br><br>
					Indicador: ${indicador?.nombre}.<br>
					Responsable: ${indicador?.nombreResponsable}.
					""" 
		 }
		 render (text: "enviado", contentType: "text/html", encoding: "UTF-8")
	}

    def create() {
		def user =springSecurityService.currentUser
		
		def dep = user.dependencia
		
		def decimalesList = [0,1,2,3,4]
	
        [indicadorInstance: new Indicador(params),dep:dep,decimalesList:decimalesList]
    }

	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def dataTablesListadoIndicadores = {
		
	def query="  from idn_indicador	 as i "
		
		render dataTablesService.datosParaTablaQuery(query,params,
	    [
		'i.idn_id as id',
		'i.idn_nombre as  nombre',
		'i.idn_objetivo  as objetivo',
		'i.idn_nombre_responsable as nomr'
		],  
		[
		'i.idn_id ',
		'i.idn_nombre ',
		'i.idn_objetivo ',
		'i.idn_nombre_responsable '
		],
	
		[
		'id',
		'nombre',
		'objetivo',
		'nomr'
		],1,"text") as JSON
		
}

	
	
	
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def tabla(){
		
		[dependencia:params.id]
		
		
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def visor(){

		def indicadorInstance = Indicador.get(params.id);
		def opcion= 1;

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
								"descripcion,"+
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
						result?.each {

							def queryCat="select cct_id from cat_categoria ca ,cat_tipo ct where ca.cct_ctt_id=ct.ctt_id "+
									" and ca.cct_id in ( select cdc_cct_id from cat_dvariable_categoria where cdc_cdv_id = "+  vari.id+" ) and ctt_id ="+ it.ctt_id
							resultTipo= sql.rows(queryCat.toString())
							def tam =resultTipo.size()
							def c=1
							resultTipo?.each {
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

						query=query+") o LEFT JOIN cat_region cr ON cr.crg_id = o.region_id LEFT JOIN cat_municipio cm ON cm.mun_id = o.municipio_id LEFT JOIN cat_localidad cl ON cl.ctl_id = o.localidad_id  group by clave,descripcion"

						def resultTotal = sql.rows(query.toString())

						if(resultTotal.size()>0){
							temVar= new RVariable()
							temVar.letra=vari.clave

							resultTotal?.each
							{
								ResultadoTemporal valorTem = new ResultadoTemporal()
								switch (vari.poblacion.clave) {
									case "H":
										valorTem.indicador=it.hombres
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;

									case "M":
										valorTem.indicador=it.mujeres
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;

									case "T":
										valorTem.indicador=it.total
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;
									default:
										break;
								}
							}

							rVariables.add(temVar)
						}
						sql.close()
					}

				/***
				 * Comienza el calculo del indicador en base a las variables
				 * */
					if(rVariables.size()>0){
						rVariables.each { var->
							formula=formula.replaceAll(var.letra, String.valueOf(var.valores.get(0).indicador))
						}
						
						ResultadoTemporal rTemp = new ResultadoTemporal()

						ScriptEngineManager script = new ScriptEngineManager();
						ScriptEngine js = script.getEngineByName("JavaScript");
						try {

							rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
							rTemp.anio=anio
							listTemp.add(rTemp)

						} catch (Exception e) {
							e.printStackTrace();
						}

						formula= indicadorInstance?.formula?.sentencia
					}

				/***
				 * Comienza el proceso de ordenamiento para salida
				 * */
					listTemp.each { actual->
						def ban=0
						if(resultados.size()>0){

							Resultado res= new Resultado()
							res.anio=actual.anio
							if(!Double.isNaN(actual.resultadoIndicador)){
								res.indicador=actual.resultadoIndicador
							}else{
								res=null;
							}
							resultados.get(0).resultados.add(res)
						}else{
							Resultado res= new Resultado()
							res.anio=actual.anio
							if(!Double.isNaN(actual.resultadoIndicador)){
								res.indicador=actual.resultadoIndicador
							}else{
								res=null;
							}
							ResultadoIndicador ri =  new  ResultadoIndicador()

							ri.resultados.add(res)
							resultados.add(ri)
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
								"descripcion,"+
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

						def resultTotal = sql.rows(query.toString())

						if(resultTotal.size()>0){
							temVar= new RVariable()
							temVar.letra=vari.clave

							resultTotal?.each
							{
								ResultadoTemporal valorTem = new ResultadoTemporal()
								switch (vari.poblacion.clave) {
									case "H":
										valorTem.region=it.region
										valorTem.idRegion = it.region_id
										valorTem.indicador=it.hombres
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;

									case "M":
										valorTem.region=it.region
										valorTem.idRegion =it.region_id
										valorTem.indicador=it.mujeres
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;

									case "T":
										valorTem.region=it.region
										valorTem.idRegion =it.region_id
										valorTem.indicador=it.total
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;
									default:
										break;
								}
							}

							rVariables.add(temVar)
						}
						sql.close()
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

						valorBase.each { base->


							formula=formula.replaceAll(letra, String.valueOf(base.indicador))
							rVariables.each { var->
								if(var.letra!=letra){
									var.valores.each {

										if(base.idRegion==it.idRegion){

											formula=formula.replaceAll(var.letra, String.valueOf(it.indicador))
										}
									}
								}

							}

							ResultadoTemporal rTemp = new ResultadoTemporal()

							ScriptEngineManager script = new ScriptEngineManager();
							ScriptEngine js = script.getEngineByName("JavaScript");
							try {

								rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
								rTemp.region= base.region
								rTemp.idRegion= base.idRegion
								rTemp.anio=base.anio
								listTemp.add(rTemp)

							} catch (Exception e) {
								e.printStackTrace();
							}


							formula= indicadorInstance?.formula?.sentencia



						}

						/***
					 * Comienza el proceso de ordenamiento para salida
					 * */



						listTemp.each { actual->
							def ban=0
							if(resultados.size()>0){

								resultados.each {

									if(it.idRegion==actual.idRegion){
										Resultado res= new Resultado()
										res.anio=actual.anio
										if(!Double.isNaN(actual.resultadoIndicador)){
											res.indicador=actual.resultadoIndicador
										}else{
											res=null;
										}
										it.resultados.add(res)
										ban=1
									}
								}


								if(ban!=1){
									Resultado res= new Resultado()
									res.anio=actual.anio
									if(!Double.isNaN(actual.resultadoIndicador)){
										res.indicador=actual.resultadoIndicador
									}else{
										res=null;
									}
									ResultadoIndicador ri =  new  ResultadoIndicador()
									ri.region=actual.region
									ri.idRegion=actual.idRegion
									ri.resultados.add(res)
									resultados.add(ri)

								}
							}else{
								Resultado res= new Resultado()
								res.anio=actual.anio
								if(!Double.isNaN(actual.resultadoIndicador)){
									res.indicador=actual.resultadoIndicador
								}else{
									res=null;
								}
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
								"descripcion,"+
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

						def resultTotal = sql.rows(query.toString())

						if(resultTotal.size()>0){
							temVar= new RVariable()
							temVar.letra=vari.clave

							resultTotal?.each
							{
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
										temVar.descripcion=it.descripcion
										break;

									case "M":
										valorTem.region=it.region
										valorTem.idRegion =it.region_id
										valorTem.municipio=it.municipio
										valorTem.idMunicipio = it.municipio_id
										valorTem.indicador=it.mujeres
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;

									case "T":
										valorTem.region=it.region
										valorTem.idRegion =it.region_id
										valorTem.municipio=it.municipio
										valorTem.idMunicipio = it.municipio_id
										valorTem.indicador=it.total
										valorTem.anio=anio
										temVar.valores.add(valorTem)
										temVar.descripcion=it.descripcion
										break;
									default:
										break;
								}
							}

							rVariables.add(temVar)
						}
						sql.close()
					}

				/***
				 * Comienza el calculo del indicador en base a las variables
				 * */
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

						valorBase.each { base->
							formula=formula.replaceAll(letra, String.valueOf(base.indicador))
							rVariables.each { var->
								if(var.letra!=letra){
									var.valores.each {

										if(base.idMunicipio==it.idMunicipio){

											formula=formula.replaceAll(var.letra, String.valueOf(it.indicador))
										}
									}
								}

							}

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
							} catch (Exception e) {
									e.printStackTrace();
							}


							formula= indicadorInstance?.formula?.sentencia



						}

						/***
					 * Comienza el proceso de ordenamiento para salida
					 * */

						listTemp.each { actual->
							def ban=0
							if(resultados.size()>0){

								resultados.each {

									if(it.idMunicipio==actual.idMunicipio){
										Resultado res= new Resultado()
										res.anio=actual.anio
										if(!Double.isNaN(actual.resultadoIndicador)){
											res.indicador=actual.resultadoIndicador
										}else{
											res=null;
										}
										it.resultados.add(res)
										ban=1
									}
								}


								if(ban!=1){
									Resultado res= new Resultado()
									res.anio=actual.anio
									if(!Double.isNaN(actual.resultadoIndicador)){
										res.indicador=actual.resultadoIndicador
									}else{
										res=null;
									}
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
								if(!Double.isNaN(actual.resultadoIndicador)){
									res.indicador=actual.resultadoIndicador
								}else{
									res=null;
								}
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
								"descripcion,"+
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


						def resultTotal = sql.rows(query.toString())

						if(resultTotal.size()>0){
							temVar= new RVariable()
							temVar.letra=vari.clave

							resultTotal?.each
							{
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
										temVar.descripcion=it.descripcion
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
										temVar.descripcion=it.descripcion
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
										temVar.descripcion=it.descripcion
										break;
									default:
										break;
								}
							}

							rVariables.add(temVar)
						}
						sql.close()
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

						valorBase.each { base->


							formula=formula.replaceAll(letra, String.valueOf(base.indicador))
							rVariables.each { var->
								if(var.letra!=letra){
									var.valores.each {

										if(base.idLocalidad==it.idLocalidad){

											formula=formula.replaceAll(var.letra, String.valueOf(it.indicador))
										}
									}
								}

							}

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
								e.printStackTrace();
							}


							formula= indicadorInstance?.formula?.sentencia



						}

						/***
					 * Comienza el proceso de ordenamiento para salida
					 * */

						listTemp.each { actual->
							def ban=0
							if(resultados.size()>0){

								resultados.each {

									if(it.idLocalidad==actual.idLocalidad){
										Resultado res= new Resultado()
										res.anio=actual.anio
										if(!Double.isNaN(actual.resultadoIndicador)){
											res.indicador=actual.resultadoIndicador
										}else{
											res=null;
										}
										it.resultados.add(res)
										ban=1
									}
								}


								if(ban==1){
									Resultado res= new Resultado()
									res.anio=actual.anio
									if(!Double.isNaN(actual.resultadoIndicador)){
										res.indicador=actual.resultadoIndicador
									}else{
										res=null;
									}
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
								if(!Double.isNaN(actual.resultadoIndicador)){
									res.indicador=actual.resultadoIndicador
								}else{
									res=null;
								}
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

		[indicadorInstance:indicadorInstance,resultados:resultados]
	}
	
	def getOrigenDatos(clave,indicador){
		
	}

	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def encuentraVariablesAndCategoria(Variable v, Categoria cat){
		def ban = false
		for(c in v.categorias){
			if(c.id==cat.id)
				ban=true
		}

	return ban;
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def descripciones(){
		def cvar= new ArrayList<ComboVariable>()
		def sql = new Sql(sessionFactory.currentSession.connection())
		def query = "select cvv_clave as clave ,cvv_descripcion as descripcion  from cat_variable group by cvv_clave,cvv_descripcion"

		def result = sql.rows(query.toString())

		cvar=result?.each
		{
			def v= new  Combo();

			v.descripcion=it.clave+"-"+it.descripcion;
			v.clave=it.clave

			cvar.add(v)
		}
		sql.close()
		render cvar as JSON
	}
	
	
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_ADMIN','ROLE_LECTURA'])
	def categoriasByModulo(){
		
		def modulo = Eje.get(params.id)
		def divisiones
		if(modulo){
			
		divisiones=Division.findAllByEje(modulo)
			
		}else{
		divisiones =Division.list()
		}
		render divisiones as JSON
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_ADMIN','ROLE_LECTURA'])
	def temaByEje(){
		if(params.id!='null'){
			def eje = PNDesarrollo.get(params.id)
			def temas
			
			if(eje){
				temas = Tema.findAllByEje(eje)
			}else{
				temas = Tema.list()
			}
			render temas as JSON
		}else{
			def temas = [[id:'null', descripcion: '-No pertenece al PED-']]
			render temas as JSON
		}
		
	}
	
	
    def save() {
        def indicadorInstance = new Indicador(params)
		
		def sentencia= indicadorInstance?.formula?.variables
		def variables= sentencia.split("\\|")
		
		indicadorInstance.fechaActualizacion= new Date()
		def resultado
		
		for(v in variables){

				def numCategorias= params.getAt("numCategorias_"+v)
				
				def poblacion = Poblacion.get(params.getAt("poblacion_"+v))
			
				def dVariable = new  DVariable()
				dVariable.clave=v
				dVariable.descripcion=params.getAt("descripcion_"+v)
				dVariable.claveVar=params.getAt("claveVar_"+v)
				dVariable.intervalo=params.getAt("intervalo_"+v).toInteger()
				dVariable.mostrarCiclo = params.getAt("mostrarCiclo_"+v)
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
		
		indicadorInstance.valorNivelNacional = params.valorNivelNacional!=''?params.valorNivelNacional:null
		indicadorInstance.fuenteNivelNacional = params.fuenteNivelNacional!=''?params.fuenteNivelNacional:null
		indicadorInstance.calcularPorPeriodoNivelNacional = params.calcularPorPeriodoNivelNacional.toBoolean()
		
		if(indicadorInstance.calcularPorPeriodoNivelNacional==true && params.periodo.id)
			indicadorInstance.periodoNivelNacional = Periodo.get(params.periodo.id)
		else if(params.anio.id)
			indicadorInstance.periodoNivelNacional = Periodo.get(params.anio.id)
		else{
			indicadorInstance.periodoNivelNacional = null
			indicadorInstance.calcularPorPeriodoNivelNacional = false
		}
			

		if (!indicadorInstance.save(flush: true)) {
			render(view: "create", model: [indicadorInstance: indicadorInstance])
			return
		}
		
        flash.message = message(code: 'default.created.message', args: [message(code: 'indicador.label', default: 'Indicador'), indicadorInstance.id])
        redirect(action: "show", id: indicadorInstance.id)
    }
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
    def show(Long id) {
        def indicadorInstance = Indicador.get(id)
        if (!indicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
            return
        }

        [indicadorInstance: indicadorInstance]
    }

	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
    def edit(Long id) {
        def indicadorInstance = Indicador.get(id)
        if (!indicadorInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'indicador.label', default: 'Indicador'), id])
            redirect(action: "list")
            return
        }

		def user =springSecurityService.currentUser
		
		def dep = user.dependencia
		def decimalesList = [0,1,2,3,4]
		
        [indicadorInstance: indicadorInstance,dep:dep,decimalesList:decimalesList]
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
		
		def sentencia = indicadorInstance?.formula?.variables
		def variables = sentencia.split("\\|")
		
		indicadorInstance.fechaActualizacion= new Date()

		def variablesAux = indicadorInstance.variables
		
		if(variablesAux.size()!=variables.size()){
			def ids = variablesAux.id
			ids.each{ idVariable ->
				def variable = indicadorInstance.variables.find{it.id == idVariable}
				def frecuencias = FrecuenciaIndicador.findAllByVariable(variable)
				frecuencias?.each{
					it.delete()
				}
				
				indicadorInstance.removeFromVariables(variable)
				variable.delete()
			}
		}
		
		variables.each{ v ->
			
			def variable = indicadorInstance.variables.find{it.clave == v}
			def nuevo = params.getAt("nuevo_"+v)
			def numCategorias= params.getAt("numCategorias_"+v)
			
			if(nuevo.equals('false')){
				variable.clave = v
				variable.descripcion  =params.getAt("descripcion_"+v)
				variable.claveVar = params.getAt("claveVar_"+v)
				variable.intervalo = params.getAt("intervalo_"+v).toInteger()
				variable.poblacion = Poblacion.get(params.getAt("poblacion_"+v))
				variable.mostrarCiclo = params.getAt("mostrarCiclo_"+v)
				variable.categorias.clear()
				
				for(i in 1 .. numCategorias){
					def categoria = Categoria.get(params.getAt("categoria_"+i+"_"+v))
					if(categoria){
						variable.addToCategorias(categoria)
					}
				}
				
			}else{
				def dVariable = new  DVariable()
				dVariable.clave = v
				dVariable.descripcion  =params.getAt("descripcion_"+v)
				dVariable.claveVar = params.getAt("claveVar_"+v)
				dVariable.intervalo = params.getAt("intervalo_"+v).toInteger()
				dVariable.poblacion = Poblacion.get(params.getAt("poblacion_"+v))
				
				for(i in 1 .. numCategorias){
					def categoria = Categoria.get(params.getAt("categoria_"+i+"_"+v))
					if(categoria){
						dVariable.addToCategorias(categoria)
					}
				}
				
				if(variable){
					def frecuencias = FrecuenciaIndicador.findAllByVariable(variable)
					frecuencias?.each{
						it.delete()
					}
					
					indicadorInstance.removeFromVariables(variable)
					variable.delete()
				}
				
				indicadorInstance.addToVariables(dVariable)
			}
		}
		
		indicadorInstance.valorNivelNacional = params.valorNivelNacional!=''?params.valorNivelNacional:null
		indicadorInstance.fuenteNivelNacional = params.fuenteNivelNacional!=''?params.fuenteNivelNacional:null
		indicadorInstance.calcularPorPeriodoNivelNacional = params.calcularPorPeriodoNivelNacional.toBoolean()
		
		if(indicadorInstance.calcularPorPeriodoNivelNacional==true && params.periodo.id)
			indicadorInstance.periodoNivelNacional = Periodo.get(params.periodo.id)
		else if(params.anio.id)
			indicadorInstance.periodoNivelNacional = Periodo.get(params.anio.id)
		else{
			indicadorInstance.periodoNivelNacional = null
			indicadorInstance.calcularPorPeriodoNivelNacional = false
		}
		
        if (!indicadorInstance.save(flush: true)) {
            render(view: "edit", model: [indicadorInstance: indicadorInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'indicador.label', default: 'Indicador'), indicadorInstance.id])
        redirect(action: "show", id: indicadorInstance.id)
    }

	
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def categorias(){
		
		def var =params.id
		def con= params.con
		
		[var:var,con:con]
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
	def buscadorVariable(){
		def formula = Formula.get(params.id);
		def var
		def resultado
		def claveVar 
		if(formula){
			
			var = formula.variables.split("\\|")
			if(params.idIndicador!="undefined"){
				def indicador =  Indicador.get(params.idIndicador);
				if(indicador){
					if(indicador.formula.id==formula.id){
						var = indicador.variables
						[variable:var,sentencia:formula.sentencia,descripcion:formula.descripcion]
					}else{
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
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_LECTURA', 'ROLE_ADMIN'])
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
	
	
	@Secured(['ROLE_DEP','ROLE_LECTURA', 'ROLE_ADMIN', 'ROLE_NUCLEO'])
	def semaforo(){
		def usuario = springSecurityService.currentUser
		def dependencia =  usuario.dependencia
		[dependencia:dependencia]
		
	}
	
	@Secured(['ROLE_DEP','ROLE_LECTURA', 'ROLE_ADMIN', 'ROLE_NUCLEO'])
	def actualizarSemaforo(){
		def dependencia = Dependencia.get(params.id)
		def indicadores = Indicador.findAllByDependencia(dependencia)
		def filas = []
		def status = false
		def esAdmin = esAdmin()
		def g = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()
		
		indicadores.each{ indicador ->
			def variablesCompletas = true
			indicador.variables.each{ v ->
				def variables = Variable.findByClave(v.claveVar)
				if(!variables){
					variablesCompletas=false
				}
			}
			filas.add(variablesCompletas)
		}
		render(template:"indicadorSemaforo", model:[indicadores:indicadores,rol:params.rol, filas:filas])
	}
	
	@Secured(['ROLE_DEP','ROLE_LECTURA', 'ROLE_ADMIN', 'ROLE_NUCLEO'])
	def semaforoVariable(Long id){
		def usuario = springSecurityService.currentUser
		def dependencia =  usuario.dependencia
		def indicadorInstance = Indicador.get(id)
		def filas = []
		
		indicadorInstance?.variables.sort{it.id}.each{ dVariableInstance ->
			def fecuenciaIndicadorInstance = FrecuenciaIndicador.findByVariable(dVariableInstance)
			def fechaLimite
			def status = false
			
			if(fecuenciaIndicadorInstance!=null){
				fechaLimite = fecuenciaIndicadorInstance?.fechaActualizacion
				
				if(fechaLimite<new Date()){
					while(fechaLimite<new Date()){
						fechaLimite = use(TimeCategory) {
							if(fecuenciaIndicadorInstance?.frecuencia?.mes){
								fechaLimite + fecuenciaIndicadorInstance?.frecuencia?.nmeses?.month
							}else{
								fechaLimite + fecuenciaIndicadorInstance?.frecuencia?.nmeses?.days
							}
						}
					}
					fechaLimite = use(TimeCategory) {
						if(fecuenciaIndicadorInstance?.frecuencia?.mes){
							fechaLimite - fecuenciaIndicadorInstance?.frecuencia?.nmeses?.month
						}else{
							fechaLimite - fecuenciaIndicadorInstance?.frecuencia?.nmeses?.days
						}
					}
				}else{
					status=true
				}
				
			}else{
				fechaLimite=null
			}
			def variable = CatOrigenDatos.findByClave(dVariableInstance?.claveVar)?.descripcion
			
			if(fechaLimite!=null && fechaLimite<=dVariableInstance?.fechaActualizacion){
				status = true
			}
			filas.add([dVariableInstance?.claveVar, variable, fecuenciaIndicadorInstance?.frecuencia?.descripcion, dVariableInstance?.fechaActualizacion, status])
		}
		[dependencia:dependencia, indicadorInstance:indicadorInstance, filas:filas]
	}
	
	@Secured(['ROLE_ADMIN'])
	def correo(){
		def asunto = Valor.findByKey('asunto')
		def cuerpo = Valor.findByKey('cuerpo')
		[asunto:asunto?.valor, cuerpo:cuerpo?.valor]
	}
	
	@Secured(['ROLE_ADMIN'])
	def actualizarCorreo(){
		def asunto = Valor.findByKey('asunto')
		def cuerpo = Valor.findByKey('cuerpo')
		
		asunto.valor = params.asunto
		cuerpo.valor = params.cuerpo
		if (!asunto.save(flush: true)) {
			flash.message = 'Error al guardar el asunto'
			render(view: "correo", model: [asunto:params.asunto, cuerpo:params.cuerpo])
			return
		}
		if (!cuerpo.save(flush: true)) {
			flash.message = 'Error al guardar el cuerpo del correo'
			render(view: "correo", model: [asunto:params.asunto, cuerpo:params.cuerpo])
			return
		}
		flash.message = 'Datos guardados correctamente'
		render(view: "correo", model: [asunto:asunto?.valor, cuerpo:cuerpo?.valor])
	}
	
	def verificarFrecuenciaActualizacion(){
		render(text:params.fechaActualizacion)
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
	
	def descargarManual(){
		def valorInstance = Valor.findByKey("rutaManual")
		
		if(valorInstance){
			descargarArchivo(valorInstance.valor)
		}else{
			response.sendError(500)
		}
	}
	
	def descargarCatalogoIndicadores(){
		def valorInstance = Valor.findByKey("rutaCatalogoIndicadores")
		
		if(valorInstance){
			descargarArchivo(valorInstance.valor)
		}else{
			response.sendError(500)
		}
	}
	
	def descargarArchivo(String ruta){
		try {
			def archivo = new File (ruta)
			response.setContentType("application/octet-stream; charset=UTF-8")
			response.setCharacterEncoding("UTF-8");
			response.setHeader("Content-disposition", "attachment;filename*=UTF-8''${URLEncoder.encode(archivo.getName(), 'UTF-8')}")
			response.outputStream << archivo.newInputStream()
		} catch(Exception ex){
			ex.printStackTrace()
			response.sendError(500)
		}
	}
	
	@Secured(['ROLE_ADMIN'])
	def ordenar(){
		
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_ADMIN','ROLE_LECTURA'])
	def getDivisionByEje(Long id){
		def modulo = Eje.get(id)
		def divisiones
		if(modulo){
			divisiones=Division.findAllByEje(modulo)
		}else{
			divisiones =Division.list()
		}
		render template:'divisiones', model:[division:params.division, divisiones: divisiones, idEje: id]
	}
	
	@Secured(['ROLE_DEP','ROLE_NUCLEO','ROLE_ADMIN','ROLE_LECTURA'])
	def getIndicadorByDivision(Long id){
		def	division = Division.get(id)
		render template:'division', model:[division: division]
	}
	
	@Secured(['ROLE_ADMIN'])
	def ordenarIndicadores(){
		
		def	indicadores = params.indicador
		if(indicadores){
			def tamanio = indicadores.size()
			indicadores.eachWithIndex{ indicador, index ->
				try{
					def indicadorInstance = Indicador.get(indicador.toLong())
					indicadorInstance.orden = index
					indicadorInstance.save(flush:true)
					flash.message = "Orden guardado correctamente"
				}catch(Exception e){
					e.printStackTrace()
					flash.message = "Orden guardado correctamente"
				}
			}
		}
		redirect (action:'ordenar', params:[eje:params.eje, division:params.division]) 
	}
	
}
