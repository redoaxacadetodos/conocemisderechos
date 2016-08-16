package mx.gob.redoaxaca.cednna.domino

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import grails.util.Environment
import groovy.sql.Sql
import groovyx.net.http.*

import org.springframework.dao.DataIntegrityViolationException

import com.jcraft.jsch.Channel
import com.jcraft.jsch.ChannelExec
import com.jcraft.jsch.ChannelSftp
import com.jcraft.jsch.JSch
import com.jcraft.jsch.Session
import com.redoaxaca.java.ArchivoDescarga
import com.redoaxaca.java.LeerExcell
import com.redoaxaca.java.ResultCategorias
import com.redoaxaca.java.Row
import com.redoaxaca.java.TotalVariable


@Secured(['ROLE_DEP','ROLE_LECTURA', 'ROLE_ADMIN'])
class VariableController {
	def dataTablesService
	def tablasService
	def sessionFactory
	def dataSource
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def springSecurityService
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
    def index() {
        redirect(action: "list", params: params)
    }
	
	def dataTablesListadoVariables = {
		
	def query=" from cat_variable	 as v  "+
			  "	left join  cat_region as r on r.crg_id=v.cvv_region   "+
			  " left join  cat_municipio as m on m.mun_id=v.cvv_municipio   "+
			  " left join  cat_localidad as l on l.ctl_id=v.cvv_localidad   "+
			  " left join cat_variable_categoria as cvc on cvc.cvc_cvv_id= v.cvv_id  "+
			  " left join cat_categoria as ct on  ct.cct_id= cvc.cvc_cct_id  "
		
		
		render dataTablesService.datosParaTablaQuery(query,params,
		[
		"cvv_id as id ", 
		"cvv_clave as clave " , 
		"cvv_descripcion as descripcion ", 
		"coalesce(r.crg_descripcion , '') as region " ,
		"coalesce(m.mun_descripcion , '') as municipio " ,
		"coalesce(l.ctl_descripcion , '') as localidad " ,
		"coalesce(ct.cct_descripcion , '') as categoria " ,
		"cvv_anio as anio " ,
		"cvv_poblacion_total as total ",
		"cvv_mujeres as mujeres",
		"cvv_hombres as hombres "
		],
		[
		"cvv_clave " , 
		"cvv_descripcion ", 
		"coalesce(r.crg_descripcion , '') " ,
		"coalesce(m.mun_descripcion , '') " ,
		"coalesce(l.ctl_descripcion , '') " ,
		"coalesce(ct.cct_descripcion , '')  " ,
		"cvv_anio " ,
		"cvv_poblacion_total ",
		"cvv_mujeres ",
		"cvv_hombres "
		],[
		"id", 
		"clave" , 
		"descripcion", 
		"region" ,
		"municipio" ,
		"localidad" ,
		"categoria",
		"anio" ,
		"total",
		"mujeres",
		"hombres"
		],1,"text") as JSON
}

	
	
	
		def dataTablesListadoPanel = {
			
		def query=" from viw_variable ";
				
				render dataTablesService.datosParaTablaQuery(query,params,
			[
			"region_id",
			"municipio_id",
			"localidad_id", 
			"clave", 
			"descripcion", 
			"region" ,
			"municipio" ,
			"localidad" ,
			"mujeres",
			"hombres",
			"total"
			],[
			"region_id",
			"municipio_id",
			"localidad_id", 
			"clave", 
			"descripcion", 
			"region" ,
			"municipio" ,
			"localidad" ,
			"mujeres",
			"hombres",
			"total"
			],[
			"region_id",
			"municipio_id",
			"localidad_id", 
			"clave", 
			"descripcion", 
			"region" ,
			"municipio" ,
			"localidad" ,
			"mujeres",
			"hombres",
			"total"
			],1,"text") as JSON
		}
	
		
	def monitor(){
		
	}
	
	@Secured(['ROLE_ADMIN'])
	def dele(){}
	

	@Secured(['IS_AUTHENTICATED_FULLY'])
	def getTablaDatosEstadisticos(){
		int sEcho = 0
		if(params.sEcho){
			sEcho = params.sEcho.toInteger()
			sEcho++
		}
		
		def datos = tablasService.getTablaDatosEstadisticos(params, false)
		def totalRecords = tablasService.getTablaDatosEstadisticos(params, true)
		
		def result = ['sEcho':sEcho, 'iTotalRecords':totalRecords, 'iTotalDisplayRecords':totalRecords, 'aaData':datos]
		
		render result as JSON
	}
	
	@Secured(['IS_AUTHENTICATED_FULLY'])
    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [variableInstanceList: Variable.list(params), variableInstanceTotal: Variable.count()]
    }

	@Secured(['ROLE_DEP','ROLE_ADMIN'])
    def create() {
		def usuario = springSecurityService.currentUser
        [variableInstance: new Variable(params),dependencia:usuario.dependencia]
    }

	@Secured(['ROLE_DEP','ROLE_ADMIN'])
    def save() {
        def variableInstance = new Variable(params)
		
		if(params.periodo){
			variableInstance.anio = variableInstance.periodo.anioInicial
		}
		
		CatOrigenDatos cod= CatOrigenDatos.findByClave( params.origenDatos)
		variableInstance.clave=cod.clave
		variableInstance.descripcion=cod.descripcion
		variableInstance.estado = Estado.get(20)
		def usuario = springSecurityService.currentUser
		variableInstance.dependencia=usuario.dependencia
		def numCategorias= params.numCategorias.toInteger()

		for(i in 1 .. numCategorias){
			def temCategoria =  Categoria.get(params.getAt("categoria_"+i))
			if(temCategoria){
				variableInstance.addToCategorias(temCategoria)
			}
		}
		
        if (!variableInstance.save(flush: true)) {
            render(view: "create", model: [variableInstance: variableInstance])
            return
        }
		
		def dVariables = DVariable.findAllByClaveVar(variableInstance?.clave)
		
		dVariables.each{ dVariableInstance ->
			dVariableInstance.fechaActualizacion = new Date()
			dVariableInstance.save(flush: true)
		}

        flash.message = message(code: 'default.created.message', args: [message(code: 'variable.label', default: 'Variable'), variableInstance.id])
        redirect(action: "show", id: variableInstance.id)
    }
	
	def tieneDatosOrigen(){
		String respuesta = ""
		def variables
		def tipo = params.opcionSerie.toInteger()
		def clave = params.origenDatos
		String areaGeografica = ""
		String periodo = ""
		
		if(tipo==2)
				areaGeografica = " and cvv_region is not null"
		else if(tipo==3)
				areaGeografica = " and cvv_municipio is not null"
		
		if(params.periodo){
			Periodo p = Periodo.get(params['periodo.id'].toLong())
			periodo = " cvv_ped_id = '${p.id}' "
		}else{
			periodo = " cvv_anio = '${params.anio}' and cvv_ped_id is null "
		}
		
		def sql = new Sql(sessionFactory.currentSession.connection())
		String sqlOrigen = "select count(*) from cat_variable where cvv_clave = '${clave}' and ${periodo} ${areaGeografica}"
		variables = sql.rows(sqlOrigen)
		
		if(variables[0].count!=0){
			respuesta = "<script>verificarDescarga(1);</script>"
		}else{
			respuesta ="<script>verificarDescarga(2);</script>"
		}
		
		render(text: respuesta, contentType: "text/html", encoding: "UTF-8")
	}
	
	def generaXLS() {
		
		CatOrigenDatos cod= CatOrigenDatos.findByClave( params.origenDatos)
		
		if(cod){
			def clave =cod.clave
			def descripcion=cod.descripcion
			Periodo periodo
			int anio
			
			if(params['periodo.id']){
				periodo = Periodo.get(params['periodo.id'].toLong())
				anio = periodo.anioInicial
			}else{
				anio= params.anio.toInteger()
			}
			
			int opcion= params.opcionSerie.toInteger()
			def numCategorias= params.numCategorias.toInteger()
		
			ArrayList<ResultCategorias> cts= new   ArrayList<ResultCategorias>();
			ArrayList<String> cats= new   ArrayList<String>();
			ArrayList<Row> renglones = new ArrayList<Row>();
			
			ArrayList<ArrayList<Long>> arryCat = new ArrayList<ArrayList<Long>>();
		
			def  ArrayList<Long> tipos =   new ArrayList<Long>()
		
			for(i in 1 .. numCategorias){
				def temCategoria =  Categoria.get(params.getAt("categoria_"+i))
				if(temCategoria){
					if(!tipos.contains(temCategoria.tipo.id)){
						tipos.add(temCategoria.tipo.id);
					}
				}
			}
			
			tipos.each {
				ResultCategorias rc= new ResultCategorias()
				rc.tipo=Tipo.get(it)
				cts.add( rc)
			}

			for(i in 1 .. numCategorias){
				def temCategoria =  Categoria.get(params.getAt("categoria_"+i))
				if(temCategoria){
					if(cts.size()>0){
						cts.each{
							if(temCategoria.tipo.id ==it.tipo.id){
								it.categorias.add(temCategoria);
							}
						}
					}
				}
			}

			int tamY =cts.size()
			int tamX =1;
			
			cts.each{
				tamX= tamX *it.categorias.size()
			}
			
			long[][] mat=new long[tamX][tamY];
			int ban=0
			int y=0
			cts.each{ c ->
				int veces=	tamX/c.categorias.size()
				int x=0;

				if(ban==0){
					for(int xy=0; xy<veces;xy++){
						c.categorias.each {
							mat[x][y]= it.id
							x++;
						}
					}
					ban=1
				}
				else{
					c.categorias.each {
						for(int xy=0; xy<veces;xy++){
							mat[x][y]= it.id
							x++;
						}
					}
				}
				y++;
			}

			switch (opcion) {
				case 1:
					for(int x=0; x<tamX;x++){
						Row renglon = new Row()
						renglon.clave=clave
						renglon.descripcion=descripcion
						if(params.periodo){
							renglon.periodo=periodo.descripcion
						}else{
							renglon.anio=anio
						}
						renglon.categorias = new ArrayList<Long>();
						for(int v=0; v<tamY;v++){
							renglon.categorias.add(Categoria.get(mat[x][v]));
						}
						renglones.add(renglon)
					}
					break;

				case 2:
					def regiones=  Region.list( sort: "clave", order: "asc")
					regiones.each {
						for(int x=0; x<tamX;x++){
							Row renglon = new Row()
							renglon.clave=clave
							renglon.descripcion=descripcion
							renglon.idRegion=it.id
							renglon.region=it.descripcion
							if(params.periodo){
								renglon.periodo=periodo.descripcion
							}else{
								renglon.anio=anio
							}
							renglon.categorias = new ArrayList<Long>();
							for(int v=0; v<tamY;v++){
								renglon.categorias.add(Categoria.get(mat[x][v]));
							}
							renglones.add(renglon)
						}
					}
					break;

				case 3:
					def municipios=  Municipio.list(sort: "id", order: "asc")
					municipios.each {
						for(int x=0; x<tamX;x++){
							Row renglon = new Row()
							renglon.clave=clave
							renglon.descripcion=descripcion
							renglon.idRegion=it.region.id
							renglon.region=it.region.descripcion

							renglon.idMunicipio=it.id
							renglon.municipio=it.descripcion

							if(params.periodo){
								renglon.periodo=periodo.descripcion
							}else{
								renglon.anio=anio
							}
							renglon.categorias = new ArrayList<Long>();
							for(int v=0; v<tamY;v++){
								renglon.categorias.add(Categoria.get(mat[x][v]));
							}

							renglones.add(renglon)
						}
					}
					break;

				case 4:
					def localidades=  Localidad.list()
					localidades.each {
						Row renglon = new Row()
						renglon.clave=clave
						renglon.descripcion=descripcion
						if(params.periodo){
							renglon.periodo=periodo.descripcion
						}else{
							renglon.anio=anio
						}
						renglon.categorias = new ArrayList<Integer>();

						renglon.idRegion=it.municipio.region.id
						renglon.region=it.municipio.region.descripcion
						renglon.idMunicipio=it.municipio.id
						renglon.municipio=it.municipio.descripcion

						renglon.idLocalidad=it.id
						renglon.localidad=it.descripcion

						for(i in 1 .. numCategorias){
							def temCategoria =  Categoria.get(params.getAt("categoria_"+i))
							if(temCategoria){
								cats.add(temCategoria.descripcion)
								renglon.categorias.add(new Long(temCategoria.id))
							}
						}
						renglones.add(renglon)
					}
					break;
		}
			
		ArchivoDescarga archivodown
		
		if(params['periodo.id']){
			archivodown = new ArchivoDescarga(renglones,cts,opcion, true)
		}else{
			archivodown = new ArchivoDescarga(renglones,cts,opcion, false)
		}
		
		try {
			def archivo = new File (archivodown.getRuta())
			response.setContentType("application/octet-stream")
			response.setHeader("Content-disposition", "attachment;filename=${archivo.getName()}")
			response.outputStream << archivo.newInputStream()
		} catch(Exception ex){
			response.sendError(500)
		}
			
		}else{
		
		 redirect(action: "archivo")
		 
		 }
		
	}
	
	@Secured(['ROLE_DEP', 'ROLE_ADMIN'])
	def archivo(){
		def usuario = springSecurityService.currentUser
		[dependencia:usuario.dependencia]
	}
	
	def borrarOrigen(){
		def sql = new Sql(sessionFactory.currentSession.connection())
		def anio = "cvv_anio=?"
		int idAnio 
		
		if(params.tipoPeriodo=='true'){
			anio = "cvv_ped_id=?"
			idAnio = params.periodo.toInteger()
		}else{
			idAnio = params.anio.toInteger()
		}
		
		def consulta ="delete  from cat_variable_categoria where cvc_cvv_id in (select cvv_id from cat_variable where ${anio} and  cvv_clave=?)"
		sql.execute(consulta, [idAnio,params.origenDatos.toString()])
			
		consulta ="delete from cat_variable where ${anio} and  cvv_clave=? "
		sql.execute(consulta, [idAnio,params.origenDatos.toString()])
	
		redirect(action: "list")
	}
	
	def getCategoriaByTipo() {
		def sql = new Sql(sessionFactory.currentSession.connection())
		
		def pla = new ArrayList<Categoria>()
		
		def tipoCat = Tipo.get(params.id)
		def consulta = "select cct_id id, cct_descripcion descripcion from cat_categoria where cct_ctt_id = "+tipoCat.id
		

		if (tipoCat) {
			pla = sql.rows(consulta)
			sql.close()
			render pla as JSON
		} else {
			render pla as JSON
		}
	}
	
	
	
	def getMunicipioByRegion() {
		def pla = new ArrayList<Municipio>()
		
		if(params.id!="null"){
		def region = Region.get(params.id)

		
		if (region) {

			pla = Municipio.findAllByRegion(region)


			render pla as JSON
		} else {
			render Municipio.list() as JSON
		}
		}else{
		render Municipio.list() as JSON
		
		}
	}
	
	
	
	def getLocalidadByMunicipio() {
		def pla = new ArrayList<Localidad>()
		

		if(params.id!="null"){
			def municipio = Municipio.get(params.id)
	
			
			if (municipio) {
	
				pla = Localidad.findAllByMunicipio(municipio)
	
	
				render pla as JSON
			} else {
				render Localidad.list() as JSON
			}
		}else{
		render Localidad.list() as JSON
		}
	}
	
	
	def panel(){
		def localidad
		def municipio
		def region
		
		if(params.localidad!='null')
		localidad = Localidad.get(params.localidad)
		
		if(params.municipio!='null')
		municipio =Municipio.get(params.municipio)
		
		if(params.region!='null')
		region = Region.get(params.region)
		
		
		[clave:params.id,desc:params.desc,region:region,localidad:localidad,municipio:municipio,mujeres:params.mujeres,hombres:params.hombres,total:params.total]
	}
	
	
	
	def addCat(){
		def num= params.contador 
			
		[num:num+1]	
	}
	
	
	def resultadoSeleccion(){
		
		def localidad
		def municipio
		def region
		
		if(params.localidad.id!='null'){
			
				localidad = Localidad.get(params.localidad)
		}
		
		if(params.municipio.id!='null'){
			
				municipio =Municipio.get(params.municipio)
		}
		
		if(params.region.id!='null'){
			
				region = Region.get(params.region)
		}
	}
	
	def resultadoPanel() {
		
		def variablesLocalidad = new TotalVariable()
		def variablesMunicipio = new TotalVariable()
		def variablesRegion = new TotalVariable()
	    def variables = new TotalVariable()
					
						if(params.localidad!="null")
						{	
							def vlocalidad
							def localidad = Localidad.get(params.localidad)
							
							vlocalidad= Variable.findByClaveAndLocalidad(params.id,localidad);
							if(vlocalidad)
							{
								variablesLocalidad.variables=vlocalidad
							
								vlocalidad.each {
									variablesMunicipio.total+=it.poblacionTotal
									variablesMunicipio.mujeres+=it.mujeres
									variablesMunicipio.hombres+it.hombres
								}
						
							}
						
						}
						
						
						if(params.municipio!="null")
						{	
							def vmunicipio
							def municipio = Region.get(params.municipio)	
							vmunicipio= Variable.findByClaveAndMunicipio(params.id,municipio);
							if(vmunicipio)
							{
								
								variablesMunicipio.variables=vmunicipio
								
								vmunicipio.each {
									variablesMunicipio.total+=it.poblacionTotal
									variablesMunicipio.mujeres+=it.mujeres
									variablesMunicipio.hombres+it.hombres
								}
								
							}else{
									def result=vLocalidades(municipio)
									variablesMunicipio.variables=result.variables
									variablesMunicipio.total=result.total
									variablesMunicipio.mujeres=result.mujeres
									variablesMunicipio.hombres=result.hombres
							}
						}
						
						
						if(params.region!="null")
						{	
							def vregiones
							def region = Region.get(params.region)	
							vregiones= Variable.findByClaveAndRegion(params.id,region);
							if(vregiones)
							{
								
								variablesRegion.variables=vregiones
								
								vregiones.each {
									variablesRegion.total+=it.poblacionTotal
									variablesRegion.mujeres+=it.mujeres
									variablesRegion.hombres+it.hombres
								}
								
							}else{
									def result=vMunicipios(region)
									variablesRegion.variables=result.variables
									variablesRegion.total=result.total
									variablesRegion.mujeres=result.mujeres
									variablesRegion.hombres=result.hombres
							}
						}
						
						
			
						
		[general:variables,vregion:variablesRegion,vmunicipio:variablesMunicipio,vlocalidad: variablesLocalidad]				
						
	}
	
	
	
	
	
	
	
	
	
	
	
	def busquedaMunicipio(){
		
		
		
		
		
		
					"select  cvv_region as region, sum(cvv_hombres) as hombres, sum(cvv_mujeres) as mujeres, sum(cvv_poblacion_total) as total"+
					"from cat_variable"+
					"where cvv_region is not null"+
					"and"+
					"("+
					"	cvv_id in (select  cvc_cvv_id from cat_variable_categoria where cvc_cct_id = 1) "+
					"	or"+
					"	cvv_id in (	select cvc_cvv_id  from cat_variable_categoria  where cvc_cct_id = 2) "+
					")"+
					"group by  cvv_region"
		
		
		
	}
	
	
	def busquedaRegion(){
		
		
		
		
		
		
					"select  cvv_region as region, sum(cvv_hombres) as hombres, sum(cvv_mujeres) as mujeres, sum(cvv_poblacion_total) as total"+
					"from cat_variable"+
					"where cvv_region is not null"+
					"and"+
					"("+
					"	cvv_id in (select  cvc_cvv_id from cat_variable_categoria where cvc_cct_id = 1) "+
					"	or"+
					"	cvv_id in (	select cvc_cvv_id  from cat_variable_categoria  where cvc_cct_id = 2) "+
					")"+
					"group by  cvv_region"
		
		
		
	}
	
	
	def busquedaLocalidad(){
		
		
		
		
		
		
					"select  cvv_region as region, sum(cvv_hombres) as hombres, sum(cvv_mujeres) as mujeres, sum(cvv_poblacion_total) as total"+
					"from cat_variable"+
					"where cvv_region is not null"+
					"and"+
					"("+
					"	cvv_id in (select  cvc_cvv_id from cat_variable_categoria where cvc_cct_id = 1) "+
					"	or"+
					"	cvv_id in (	select cvc_cvv_id  from cat_variable_categoria  where cvc_cct_id = 2) "+
					")"+
					"group by  cvv_region"
		
		
		
	}
	
	
	
	def addCategoria(val){
		
		return "select cvc_cvv_id from cat_variable_categoria  where cvc_cct_id = "+val
		
	}
	
	
	def orCategoria(val){
		
		return "or cvv_id in ( select cvc_cvv_id from cat_variable_categoria  where cvc_cct_id = "+val+")"
		
	}
	
	
	
	
	
	def vLocalidades(municipio){
		
		def ids = new ArrayList()
		def result = new TotalVariable()
		Integer  total =0
		Integer  hombres=0
		Integer  mujeres=0
		
		
		def loc= Localidad.findAllByMunicipio(municipio)
		loc.each { ids.add(it) }
		
		if(ids.size>0){
		
		String sqlVariables=" from Variable where localidad in (:list)  "
		
		def variables=Variable.executeQuery(sqlVariables,[list:ids])
			
		
		variables.each {
			 mujeres+=it.mujeres 
			 hombres+=it.hombres
			 total+=it.poblacionTotal
		}
		
		result.variables=variables
		}
		result.mujeres=mujeres
		result.hombres=hombres
		result.total=total
		
		
		return result
	}
	
	
	def vMunicipios(region){
		
		def ids = new ArrayList()
		def result = new TotalVariable()
		Integer  total =0
		Integer  hombres=0
		Integer  mujeres=0
		
		
		def mun= Municipio.findAllByRegion(region)
		mun.each { ids.add(it) }
		
		if(ids.size>0){
		
		String sqlVariables=" from Variable where municipio in (:list)  "
		
		def variables=Variable.executeQuery(sqlVariables,[list:ids])
			
		
		variables.each {
			 mujeres+=it.mujeres 
			 hombres+=it.hombres
			 total+=it.poblacionTotal
		}
		
	
		result.variables=variables
		
		}
		
		result.mujeres=mujeres
		result.hombres=hombres
		result.total=total
		
		return result
	}
	
	def subirArchivo(){
		def usuario = springSecurityService.currentUser
		Dependencia dependencia
		def archivo

		File archivo_
		def contadorBuenos = 0
		def contadorMalos = 0
		def contador = 0
		def mensaje=""
		def path
		def  ArrayList<Row>	renglones
		ArrayList<Row> renglonesMalos
		
		try{
			path = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads
			
			def sec
			def sql = new Sql(sessionFactory.currentSession.connection())
			def secuencia= "select max(cvv_id) as ultimo from cat_variable"
			def resulSec = sql.rows(secuencia)
			sql.close()
			
			resulSec?.each{
				sec=it.ultimo
			}
			
			int idTemporal
			def sqlTemporal = new Sql(sessionFactory.currentSession.connection())
			def sqlSecuencia= "select nextval('sec_tmp_carga')"
			def result = sqlTemporal.rows(sqlSecuencia)
			sqlTemporal.close()
			idTemporal = result[0].nextval
			
			Estado estOaxaca=Estado.get(20)
			
			def fBase = request.getFile('fileBase')
			if(!fBase.empty) {
				fBase.transferTo( new File(path + fBase.originalFilename.toString()) )
			}
			
			archivo_ = new File(path + fBase.originalFilename.toString())
			def arc = new LeerExcell(archivo_, idTemporal,estOaxaca, dependencia,path)
			 
			contadorBuenos=arc.total()
			
			Valor servidor = Valor.findByKey("servidor")
			Valor usuarioSSH = Valor.findByKey("usuario")
			Valor pem = Valor.findByKey("pem")
			String tabla = "tmp_carga"
			String columnas = ""
			
			enviarArchivo(servidor?.valor, usuarioSSH?.valor, pem?.valor, path+"csvCV_"+idTemporal+".csv" )
			ejecutarCopy(servidor?.valor, usuarioSSH?.valor, pem?.valor, path+"csvCV_"+idTemporal+".csv", tabla, columnas)
			
			def resultadoCarga = ejecutarCargarDatos(idTemporal)
			
			if(contadorBuenos!=resultadoCarga){
				contadorMalos = contadorBuenos - resultadoCarga
				contadorBuenos = resultadoCarga
			}
			
			def dVariables = DVariable.findAllByClaveVar(arc.getClave())
			
			dVariables.each{ dVariableInstance ->
				dVariableInstance.fechaActualizacion = new Date()
				dVariableInstance.save(flush: true)
			}
			 
			
			}catch (Exception e) {
				e.printStackTrace()
				contadorMalos = contadorBuenos
				contadorBuenos = 0
			}
			render(template:'subirArchivo', model:[total :contadorBuenos+contadorMalos, buenos : contadorBuenos, malos : contadorMalos])
		}
	
	def ejecutarCargarDatos(int id){
		def sqlProcedicimiento = new Sql(dataSource)
		def procedimiento= "select cargardatos(${id})"
		def datosInsertados = sqlProcedicimiento.rows(procedimiento)
		
		sqlProcedicimiento.close()
		return datosInsertados.cargardatos[0]
	}
	
	def ejecutarCopy(String url, String usuario, String rutaPEM, String path, String tabla, String columnas){
		Valor base
		switch (Environment.current) {
		    case Environment.DEVELOPMENT:
				base = Valor.findByKey("desarrollo")
		        break
		    case Environment.PRODUCTION:
				base = Valor.findByKey("produccion")
		        break
		}
		String baseDatos = base?.valor
		String sshCommand = "\\copy ${tabla} ${columnas} from '${path}' csv header   NULL  'null'"
		sshCommand = """psql ${baseDatos} -c "${sshCommand}" """
		java.util.Properties config = new java.util.Properties()
		config.put "StrictHostKeyChecking", "no"
		println 'sshCommand:'+sshCommand
		JSch jsch=new JSch();
		Session session=jsch.getSession(usuario, url, 22);
		jsch.addIdentity(rutaPEM);
		Properties config2 = new Properties();
		config2.put("StrictHostKeyChecking", "no");
		session.setConfig(config);
		session.connect();
	   
		ChannelExec channel=(ChannelExec) session.openChannel("exec");
		channel.setCommand(sshCommand);
		
		channel.setErrStream(System.err);
		InputStream input = channel.getInputStream();
		
		channel.connect();
		
		InputStream is = channel.getInputStream();
		BufferedReader inp = new BufferedReader(new InputStreamReader(is));

		String line;
		while ((line = inp.readLine()) != null || !channel.isClosed()) {
			if (line != null) {
				println line + '\n'
			}
		}
		inp.close();
		is.close();
		
		channel.disconnect();
		session.disconnect();
	}
	
	def enviarArchivo(String url, String usuario, String rutaPEM, String path){
		java.util.Properties config = new java.util.Properties()
		
		config.put "StrictHostKeyChecking", "no"
		JSch ssh = new JSch()
		ssh.addIdentity(rutaPEM);
		Session sess = ssh.getSession usuario, url, 22
		sess.with {
			setConfig config
			connect()
			Channel chan = openChannel "sftp"
			chan.connect()
			 
			ChannelSftp sftp = (ChannelSftp) chan;
			def sessionsFile = new File(path)
			sessionsFile.withInputStream { istream -> sftp.put(istream, path) }
			
			chan.disconnect()
			disconnect()
		}
		
		
	} 
	
	def categorias(){
		
		def var =params.id
		int con= params.con.toInteger()
		def valida = params.valida
		
		[var:var,con:con,valida:valida]
	}
	
	
	def categoriasAll(){
		def var =params.id
		def tipo =Tipo.get(params.tipoId)
		def con= params.con.toInteger()
		
		def categorias = Categoria.findAllByTipo(tipo);
		
		[var:var,con:con,categorias:categorias,tipo:tipo,idn:tipo.id+"_"+con]
	}
	
	@Secured(['ROLE_DEP','ROLE_LECTURA', 'ROLE_ADMIN','ROLE_NUCLEO'])
    def show(Long id) {
        def variableInstance = Variable.get(id)
        if (!variableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
            return
        }

        [variableInstance: variableInstance]
    }

	@Secured(['ROLE_DEP','ROLE_ADMIN'])
    def edit(Long id) {
		def ban=1;
		def usuario = springSecurityService.currentUser
        def variableInstance = Variable.get(id)
		
        if (!variableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
            return
        }
		
		if(variableInstance.dependencia){
			if(usuario.dependencia){
				if(usuario.dependencia.id!=variableInstance.dependencia.id){
					ban=0
				}
			}
		}else{
		
				if(usuario.dependencia){
					ban=0
				}
		
		}

        [variableInstance: variableInstance,ban:ban, dependencia:usuario.dependencia]
    }

	@Secured(['ROLE_DEP','ROLE_ADMIN'])
    def update(Long id, Long version) {
        def variableInstance = Variable.get(id)
		def dVariables = DVariable.findAllByClaveVar(variableInstance?.clave)
		
		CatOrigenDatos cod= CatOrigenDatos.findByClave( params.origenDatos)
		variableInstance.clave=cod.clave
		variableInstance.descripcion=cod.descripcion
		
        if (!variableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (variableInstance.version > version) {
                variableInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'variable.label', default: 'Variable')] as Object[],
                          "Another user has updated this Variable while you were editing")
                render(view: "edit", model: [variableInstance: variableInstance])
                return
            }
        }
		
		dVariables.each{ dVariableInstance ->
			dVariableInstance.fechaActualizacion = new Date()
			dVariableInstance.save(flush: true)
		}

        variableInstance.properties = params
		
		if(!params.periodo?.id){
			variableInstance.periodo = null
		}else{
			variableInstance.anio = variableInstance.periodo.anioInicial
		}

        if (!variableInstance.save(flush: true)) {
            render(view: "edit", model: [variableInstance: variableInstance])
            return
        }
		
		dVariables.each{ dVariableInstance ->
			dVariableInstance.fechaActualizacion = new Date()
			dVariableInstance.save(flush: true)
		}

        flash.message = message(code: 'default.updated.message', args: [message(code: 'variable.label', default: 'Variable'), variableInstance.id])
        redirect(action: "show", id: variableInstance.id)
    }
	
	
	@Secured(['ROLE_ADMIN'])
    def delete(Long id) {
        def variableInstance = Variable.get(id)
        if (!variableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
            return
        }

        try {
            variableInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "show", id: id)
        }
    }
	
	

}
