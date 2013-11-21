package mx.gob.redoaxaca.cednna.domino

import java.util.ArrayList;

import com.redoaxaca.java.ArchivoDescarga;
import com.redoaxaca.java.LeeArchivo
import com.redoaxaca.java.ResultCategorias;
import com.redoaxaca.java.Row
import com.redoaxaca.java.TotalVariable
import grails.converters.JSON
import org.springframework.dao.DataIntegrityViolationException
import mx.gob.redoaxaca.cednna.seguridad.Usuario;

import org.springframework.dao.DataIntegrityViolationException
import org.apache.commons.io.FilenameUtils;
import org.springframework.web.multipart.MultipartHttpServletRequest
import org.springframework.web.multipart.commons.CommonsMultipartFile


import grails.plugins.springsecurity.Secured
import groovy.sql.Sql


@Secured(['ROLE_DEP','ROLE_LECTURA'])
class VariableController {
	def dataTablesService
	def sessionFactory
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def springSecurityService
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
	def dele(){}

	
	
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
		
		CatOrigenDatos cod= CatOrigenDatos.findByClave( params.origenDatos)
		variableInstance.clave=cod.clave
		variableInstance.descripcion=cod.descripcion
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

        flash.message = message(code: 'default.created.message', args: [message(code: 'variable.label', default: 'Variable'), variableInstance.id])
        redirect(action: "show", id: variableInstance.id)
    }

	
	def generaXLS() {
		
		
		CatOrigenDatos cod= CatOrigenDatos.findByClave( params.origenDatos)
		
		if(cod){
		
		
		def clave =cod.clave
		def descripcion=cod.descripcion
		
		
		
		
		
		int anio= params.anio.toInteger()
		int opcion= params.opcionSerie.toInteger()
		def numCategorias= params.numCategorias.toInteger()
		System.out.println("El numero de categoria es : "+numCategorias);
		
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
										//System.out.println("valor :"+ temCategoria.id);
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
			cts.each{
				c ->
							int veces=	tamX/c.categorias.size()
							System.out.println("Numero de veces "+veces );
							int x=0;
						
							if(ban==0){
								for(int xy=0; xy<veces;xy++){
									
										c.categorias.each {
											
											mat[x][y]= it.id
										//	System.out.println("Matriz :"+ it.id);
											x++;
										}
									
								}
								ban=1
							}
							else{
								
								c.categorias.each {
									System.out.println("paso "+veces);
									for(int xy=0; xy<veces;xy++){
									
											
											
											mat[x][y]= it.id
										//	System.out.println("Matriz :"+ it.id);
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
							renglon.anio=anio;
							renglon.categorias = new ArrayList<Long>();
							for(int v=0; v<tamY;v++){
								
								 renglon.categorias.add(Categoria.get(mat[x][v]));
							
							}
							
							renglones.add(renglon)
						}
						
					
						
										
				
						
						
			break;
			
			
			
			case 2:
			
			
			
			
							//System.out.println("SE GENERA EL ARCHIVO POR CATEGORIAS");
			
							
							def regiones=  Region.list( sort: "clave", order: "asc")
			
							regiones.each {
								
			
								
								for(int x=0; x<tamX;x++){
									
									Row renglon = new Row()
									renglon.clave=clave
									renglon.descripcion=descripcion
									renglon.idRegion=it.id
									renglon.region=it.descripcion
									renglon.anio=anio;
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
									
									renglon.anio=anio;
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
									renglon.anio=anio;
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
		
		
	
			
			
		
		ArchivoDescarga archivodown = new ArchivoDescarga(renglones,cts,opcion)
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
	
	
	@Secured(['ROLE_DEP'])
	def archivo(){
		
		def usuario = springSecurityService.currentUser
		[dependencia:usuario.dependencia]
	}
	
	
	def borrarOrigen(){
		
		def sql = new Sql(sessionFactory.currentSession.connection())
		def consulta ="delete from cat_variable where cvv_anio=? and  cvv_clave=? "
		
		sql.execute(consulta, [params.anio.toInteger(),params.origenDatos.toString()])
		redirect(action: "list")
		return
		
	}
	
	
	
	
	def getCategoriaByTipo() {
		def pla = new ArrayList<Categoria>()
		

		
		def tipoCat = Tipo.get(params.id)

		if (tipoCat) {

			pla = Categoria.findAllByTipo(tipoCat)


			render pla as JSON
		} else {
			render pla as JSON
		}
	}
	
	
	
	def getMunicipioByRegion() {
		def pla = new ArrayList<Municipio>()
		
		System.out.println(params.id);
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
		
//		def variable = Variable.get(params.id)
		
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
		def dependencia
		def archivo

		File archivo_
		def contadorBuenos = 0
		def contadorMalos = 0
		def contador = 0
		def mensaje=""
		def estOaxaca=Estado.get(20)
		def  ArrayList<Row>	renglones
		ArrayList<Row> renglonesMalos
		
		try{
			def path = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads
			
			def fBase = request.getFile('fileBase')
			if(!fBase.empty) {
				fBase.transferTo( new File(path + fBase.originalFilename.toString()) )
			}
	
			 archivo_ = new File(path + fBase.originalFilename.toString())
			 archivo = new LeeArchivo(archivo_);
			 renglones = archivo.getDatos();
			 
		 
		     renglonesMalos = new ArrayList<Row>();

			 if(usuario)
			 	dependencia = usuario.dependencia
		
				try{
				
						for(Row row : renglones){
							
												Integer dep =dependencia?.id
												
												Integer municipio=row.getIdMunicipio()
												Integer localidad=row.getIdLocalidad()
												Integer region=row.getIdRegion()
											
												def sql = new Sql(sessionFactory.currentSession.connection())
												def sec
												def secuencia= "select nextval ('hibernate_sequence')"
												def resulSec = sql.rows(secuencia)
												
												
												resulSec?.each {
													
													sec=it.nextval
												}
												
												
												def consulta ="insert into cat_variable "+
												"(cvv_anio, cvv_clave, cvv_dependencia, cvv_descripcion, cvv_estado, cvv_hombres, cvv_localidad, cvv_mujeres, cvv_municipio, cvv_poblacion_total, cvv_region,cvv_id) "+
												"values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?,?)"	
												
												sql.execute(consulta, [row.getAnio(),row.getClave(),dep,row.getDescripcion(),estOaxaca.id,row.getHombres(),localidad,row.getMujeres(),municipio,row.getHombres()+row.getMujeres(),region,sec])
												
												consulta="insert into cat_variable_categoria (cvc_cvv_id, cvc_cct_id) values (?, ?)"

												
												row.categorias.each {
													
													sql.execute(consulta, [sec,it.id])
													
												}
												contadorBuenos++
												
						}
				
				}catch (Exception e) {
					println(e.getMessage())
					e.printStackTrace()
					renglonesMalos.add(row);
					
					contadorMalos++
				}
				
			}catch (Exception e) {
		//	println(e.getMessage())
			//e.printStackTrace()
		
		}
			
			
			[dependencia : dependencia, total :contadorBuenos+contadorMalos, buenos : contadorBuenos, malos : contadorMalos ,rMalos:renglonesMalos,mensaje:mensaje]
			
	
			
		}
	
	
	
	
	def categorias(){
		
		def var =params.id
		def con= params.con
		
		[var:var,con:con]
	}
	
	
    def show(Long id) {
        def variableInstance = Variable.get(id)
        if (!variableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
            return
        }

        [variableInstance: variableInstance]
    }

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
					System.out.println("Dependencia usurio :"+usuario.dependencia.id+" Variable dependencia "+variableInstance.dependencia.id);
				}
			}
		}else{
		
				if(usuario.dependencia){
					ban=0
				}
		
		}
		//System.out.println("Valor de la bandera de variable "+ban);

        [variableInstance: variableInstance,ban:ban]
    }

	@Secured(['ROLE_DEP','ROLE_ADMIN'])
    def update(Long id, Long version) {
        def variableInstance = Variable.get(id)
		
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

        variableInstance.properties = params

        if (!variableInstance.save(flush: true)) {
            render(view: "edit", model: [variableInstance: variableInstance])
            return
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
