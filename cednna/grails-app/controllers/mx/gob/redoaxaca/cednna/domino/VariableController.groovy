package mx.gob.redoaxaca.cednna.domino

import com.redoaxaca.java.LeeArchivo
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


@Secured(["hasRole('ROLE_ADMIN')"])
class VariableController {
	def dataTablesService
    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]
	def springSecurityService
    def index() {
        redirect(action: "list", params: params)
    }
	
	
	def dataTablesListadoVariables = {
		
	def query=" from cat_variable	 as v  "+
			  "	left join  cat_region as r on r.crg_id=v.cvv_region   "+
			  " left join  cat_municipio as m on m.mun_id=v.cvv_municipio   "+
			  " left join  cat_localidad as l on l.ctl_id=v.cvv_localidad   "
		
		
		
		render dataTablesService.datosParaTablaQuery(query,params,
		[
		"cvv_id as id ", 
		"cvv_clave as clave " , 
		"cvv_descripcion as descripcion ", 
		"coalesce(r.crg_descripcion , '') as region " ,
		"coalesce(m.mun_descripcion , '') as municipio " ,
		"coalesce(l.ctl_descripcion , '') as localidad " ,
		"cvv_poblacion_total as total ",
		"cvv_hombres as hombres ",
		"cvv_mujeres as mujeres"
		],
		[
		"cvv_clave " , 
		"cvv_descripcion ", 
		"coalesce(r.crg_descripcion , '') " ,
		"coalesce(m.mun_descripcion , '') " ,
		"coalesce(l.ctl_descripcion , '') " ,
		"cvv_poblacion_total ",
		"cvv_hombres ",
		"cvv_mujeres "
		],
	
		[
		"id", 
		"clave" , 
		"descripcion", 
		"region" ,
		"municipio" ,
		"localidad" ,
		"total",
		"hombres",
		"mujeres"
		],1,"text") as JSON
}

	
	
	
	def dataTablesListadoPanel = {
		
	def query="   from cat_variable group by cvv_clave,cvv_descripcion   "
		
		
		
		render dataTablesService.datosParaTablaQuery(query,params,
		[
		"cvv_clave as clave",
		"cvv_descripcion as descripcion",
		"sum(cvv_mujeres) as mujeres",
		"sum(cvv_hombres) as hombres",
		"sum(cvv_poblacion_total)  as total"
		
		],
		[
		"cvv_clave",
		"cvv_descripcion",
		"sum(cvv_mujeres)",
		"sum(cvv_hombres)",
		"sum(cvv_poblacion_total)"
		],
	
		[
		"clave",
		"descripcion",
		"mujeres",
		"hombres",
		"total"
		],1,"text") as JSON
}

	
	def monitor(){
		
	}
	

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [variableInstanceList: Variable.list(params), variableInstanceTotal: Variable.count()]
    }

    def create() {
        [variableInstance: new Variable(params)]
    }

    def save() {
        def variableInstance = new Variable(params)
		
		
	
		
		def numCategorias= params.numCategorias

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

	def archivo(){
		
		
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
		
		
		[clave:params.id,desc:params.desc]
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
	
		def  ArrayList<Row>	renglones
		ArrayList<Row> renglonesMalos
		
		try{
			def path = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads
			System.out.println("Ruta del excell"+path);
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
					
						println("Van: " + (++contador) + " renglones")
						
						def variableInstance= new Variable();
						def temRegion=null
						def temLocalidad=null
						def temMunicipio=null
						
						if(row.getIdRegion()){
							temRegion=Region.get(row.getIdRegion())
							
						}
						if(row.getIdRegion()){
							temLocalidad=Localidad.get(row.getIdLocalidad())
							
						}
						if(row.getIdRegion()){
							temMunicipio=Municipio.get(row.getIdMunicipio())
							
						}
						
						variableInstance.region=temRegion
						variableInstance.municipio=temMunicipio
						variableInstance.localidad=temLocalidad
						
						variableInstance.hombres=row.getHombres();
						variableInstance.mujeres=row.getMujeres();
						variableInstance.poblacionTotal=row.getHombres()+row.getMujeres()
						variableInstance.descripcion=row.getDescripcion();
						variableInstance.dependencia= dependencia
						
						variableInstance.anio=row.getAnio();
						System.out.println(row);
						//System.out.println(variableInstance);
						
						if(variableInstance.save(flush : true, failOnError : true)){
							contadorBuenos++
						}
			
			
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
			
			
			[dependencia : dependencia, total :0, buenos : contadorBuenos, malos : contadorMalos ,rMalos:renglonesMalos,mensaje:mensaje]
			
	
			
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
        def variableInstance = Variable.get(id)
        if (!variableInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'variable.label', default: 'Variable'), id])
            redirect(action: "list")
            return
        }

        [variableInstance: variableInstance]
    }

    def update(Long id, Long version) {
        def variableInstance = Variable.get(id)
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
