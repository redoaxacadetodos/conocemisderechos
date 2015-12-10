package mx.gob.redoaxaca.cednna.publico

import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.sql.Sql

import java.text.Normalizer

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

import mx.gob.redoaxaca.cednna.domino.*

import org.apache.commons.io.FilenameUtils
import org.apache.commons.lang.StringUtils
import org.hibernate.criterion.CriteriaSpecification
import org.jggug.kobo.commons.lang.CollectionUtils

import com.redoaxaca.java.DetalleIndicador
import com.redoaxaca.java.RVariable
import com.redoaxaca.java.Resultado
import com.redoaxaca.java.ResultadoIndicador
import com.redoaxaca.java.ResultadoTemporal


@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
class PublicoController {
	
	def sessionFactory
	def dataSource
	def dataTablesService
	def publicoService
	def tablasService
	def exportService
	def archivoService
	def grailsApplication
	def g = new org.codehaus.groovy.grails.plugins.web.taglib.ApplicationTagLib()

    def index() { 
		redirect(action:'indicadores')
	}
	
	def directorio = {
	}

	def galeria = {
		[infografiaList: Infografia.getAll().sort{it.orden}]
	}

	def datosdirectorio = {
		def query="  from cat_directorio "
		
		render dataTablesService.datosParaTablaQuery('from cat_directorio',params,
	    [
		'dir_id',
		'dir_nombre',
		'dir_cargo',
		'dir_dependencia',
		'dir_correo',
		'dir_telefono',
		'dir_website',
		],  
		[
		'dir_id',
		'dir_nombre',
		'dir_cargo',
		'dir_dependencia',
		'dir_correo',
		'dir_telefono',
		'dir_website',
		],1,"text") as JSON
	}
	
	def documentoList = {
		def query = "from cat_documento"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"doc_id as id",
			"doc_titulo as titulo",
			"doc_nivel as nivel",
			"tipo_documento_id as tipo",
			"doc_url as url"
			],
			[
			"tipo_documento_id",
			"doc_titulo",
			"doc_nivel"
			],
			[
			"id",
			"titulo",
			"nivel",
			"tipo",
			"url"
			],1,"text") as JSON
	}
	
	def indicadores = {
		def valorInstance = Valor?.findByKey('urlvideo')
		def urlvideo = valorInstance?.valor!=null?valorInstance?.valor:" "
		def listaEjes = Eje.findAllByStatus(1, [sort: "orden", order: "asc"])
		[urlvideo: urlvideo, listaEjes: listaEjes]
	}
		
	def contacto = {
		def contactos = Contacto.getAll().sort{it.id}
		[contactos: contactos]
	}
	
	def obtenerCoordenadasPorTipo(Long id, int tipo, def resultadosIndicador){
		def indicador = Indicador.get(id)
		def decimales = indicador?.decimales
		def color = indicador.division.eje.color
		def sqlUbicacion = ""
		def ubicaciones = []
		def coordenadas = []
		def aux = [:]
		def coordenadasList = []
		def sql = ""
		def sqlNombre=""
		def sqlNombreId = ""
		def sqlQuery = ""
		def idUbicacion = 20
		
		def db = new Sql(dataSource)
		
		switch(tipo){
			case 1:
				sql = "select estado_coordenadas_id as id, latitud, longitud from cat_entidad_coordenada ORDER BY coordenadas_idx;"
				sqlUbicacion = "select estado_coordenadas_id idUbicacion from cat_entidad_coordenada group by estado_coordenadas_id"
				break
			case 2:
				sql = "select region_coordenadas_id as id, latitud, longitud from cat_region_coordenada ORDER BY region_coordenadas_id,coordenadas_idx  ;"
				sqlUbicacion = "select region_coordenadas_id idUbicacion from cat_region_coordenada group by region_coordenadas_id ORDER BY region_coordenadas_id"
				break
			case 3:
				sql = "select municipio_coordenadas_id as id, latitud, longitud from cat_municipio_coordenada ORDER BY municipio_coordenadas_id,coordenadas_idx;"
				sqlUbicacion = "select municipio_coordenadas_id idUbicacion from cat_municipio_coordenada group by municipio_coordenadas_id ORDER BY municipio_coordenadas_id"
				break
		}
		
		db.eachRow(sqlUbicacion) {
			ubicaciones.add([idUbicacion:it.idUbicacion])
		}
			
		def idAux
		def cooorAux = []
		boolean primero = true
		
		db.eachRow(sql) { row ->
			if(primero){
				idAux = row?.id
				primero = false
			}
			if(row?.id != idAux){
				coordenadasList.add(cooorAux)
				cooorAux = new ArrayList()
				idAux = row?.id
			}
			cooorAux.add([row?.latitud, row?.longitud])
		}
		
		if(!primero){
			coordenadasList.add(cooorAux)
		}
		
		aux.put("lugar",["ubicaciones":ubicaciones])
		def jsodata = aux as JSON
		db.close()
		return [coordenadasList:coordenadasList, aux:jsodata, resultadosIndicador:resultadosIndicador, color:color]
	}	
	
	def actualizarMapa(Long id) {
		int tipo = params.idTipo.toInteger()
		params.paginado = false
		def anios = getAnosPorIndicador(id, tipo)
		def coordenadas = obtenerCoordenadasPorTipo(id, tipo, [])
		coordenadas.put("idTipo",tipo)
		coordenadas.put("idIndicador",id)
		if(anios.size()==0){
			render(template:"mapa", model:[resultadosIndicador:anios])
		}else{
			render(template:"mapa", model:coordenadas)
		}
	}
	
	def obtenerInformacionIndicador(Long id){
		def indicadorInstance = Indicador.get(id)
		def decimales = indicadorInstance?.decimales
		def sql = new Sql(dataSource)
		int tipo = params.idTipo.toInteger()
		params.paginado = false
		
		if(tipo==2){
			def sqlIndice = "select sistema from cat_region_inegi where crg_id = '${params.idUbicacion}'"
			def region = sql.firstRow(sqlIndice)
			params.idUbicacion = region["sistema"]
		}
		
		DetalleIndicador detalleIndicador = visorIndicadorPaginadoV2(id,tipo, params)
		def resultadosIndicador = detalleIndicador.resultados
		def mensaje = ""
		
		resultadosIndicador.each { resultado ->
			def ubicacionNombre = "Oaxaca"
			
			if(tipo==1){
				mensaje += '<b>Oaxaca</b><br><br>'
			}else if(tipo==2){
				def sqlIndice = "select crg_descripcion descripcion from cat_region_inegi where sistema = '${resultado.idRegion}'"
				def region = sql.firstRow(sqlIndice)
				mensaje += '<b>'+region["descripcion"]+'</b><br><br>'
			}
			else if(tipo==3){
				def sqlIndice = "select mun_descripcion descripcion from cat_municipio_inegi where mun_id = '${resultado.idMunicipio}'"
				def municipio  = sql.firstRow(sqlIndice)
				mensaje += '<b>'+municipio["descripcion"]+'</b><br><br>'
			}
			mensaje += '<table><thead><th>Año</th><th>Indicador</th></thead><tbody>'
			resultado.resultados.each { r ->
				mensaje +="<tr><td>"+r?.anio+"</td><td>"+(r?.indicador!=null?r.indicador.round(decimales):'-')+"</td></tr>"  
			}
			mensaje +="</tbody></table>"
		}
		
		sql.close()
		render text: mensaje
	}
	
	def String remplazarAcentos(String s){
		String texto = Normalizer.normalize(s, Normalizer.Form.NFD)
		texto = texto.replaceAll("\\p{InCombiningDiacriticalMarks}+", "")
		return texto;
	}
	
	def infoIndicador (Long id) {
		def eje = Eje.get(id)
		if(eje){
			def divisiones=Division.findAllByEje(eje)
			render(template:"division", model: [divisiones: divisiones])
		}else{
		redirect(action:"indicadores")
		}		
	}
	
	def detalleIndicador(Long id){		
		//Mostrar vista de indicadores		
		def eje = Eje.get(id)
		if(eje){								
			def divisiones=Division.findAllByEje(eje)	
			def niveles = []
			
			if( eje?.tipoEje?.id!=null && eje?.tipoEje?.id!=1){
				niveles = Nivel.findAllByTipoNivel(eje?.tipoEje).sort{it.id}
			}
			[divisiones: divisiones, ejeInstance: eje, tipo:eje?.tipoEje?.id, niveles:niveles]
		}else{
			redirect(action:"indicadores")
		}
	}
	
	def mostrarIndicador(Long id){
		def indicador = Indicador.get(id)
		
		if(indicador){
			def tipo = 1
			int maximaLongitud = 55
			params.paginado = false
			DetalleIndicador detalleIndicador = visorIndicadorPaginadoV2(id,tipo,params)
			
			//Cambiar f�rmula
			String formula = crearFormula(indicador)
						
			def tamVariables = indicador.variables.size()
			def eje = Eje.get(params.ejeInstance.toLong())
			def nombreIndicador = StringUtils.abbreviate(indicador?.nombre,maximaLongitud)
			def coordenadas = obtenerCoordenadasPorTipo(id, tipo, detalleIndicador.resultados)
			
			[resultados:detalleIndicador?.resultados?.resultados[0],
				nombreIndicador: nombreIndicador, 
				ejeInstance:eje, 
				indicadorInstance: indicador, 
				tipo:'1', 
				tamVariables:tamVariables, 
				formula:formula,
				coordenadasList:coordenadas.coordenadasList,
				aux:coordenadas.aux,
				resultadosIndicador:coordenadas.resultadosIndicador,
				color:coordenadas.color ]
		}
		else{
			redirect(action:"indicadores")
		}
	}
	
	String crearFormula(Indicador indicador){
		//Cambiar valores de la formula por la descripci�n
		def formula = ""
		def formulaOriginal = indicador?.formula?.sentencia
		def var = [:]
		def fin = false
		indicador?.variables.each {
			var.put(it.clave , CatOrigenDatos.findByClave(it.claveVar)?.descripcion)
		}
		
		for (int i=0; i<formulaOriginal.length(); i++) {
			for(int j=0; j<var.size(); j++){
				if(!fin && var.get(formulaOriginal.charAt(i).toString())!=null){
					formula += var.get(formulaOriginal.charAt(i).toString())
					fin = true
				}
			}
			if(!fin){
				formula += formulaOriginal.charAt(i).toString()
			}
			fin = false
		}
		return formula
	}
	
	def getTitulosDatosCalculo(Long id){
		def indicadorInstance = Indicador.get(id)
		int tipo = params.idTipo.toInteger()
		def titulos = []
		def anios = []
		def titulosAnios = []
		
		titulos.add( "Variable")
		if(tipo==1){
			titulos.add("Estado")
		}else if(tipo==2){
			titulos.add("Regiones")
		}else{
			titulos.add( "Municipios")
		}
		
		getAnosPorIndicador(id,tipo).each{
			if(indicadorInstance?.etiquetaPeriodo){
				def periodo = Periodo.get(it.periodo.toLong())
				anios.add( 'Total')
				anios.add( 'Mujeres')
				anios.add( 'Hombres')
				titulosAnios.add( periodo.descripcion)
			}else{
				anios.add( "Periodo")
				anios.add('Total')
				anios.add( 'Mujeres')
				anios.add( 'Hombres')
				titulosAnios.add(it.anio.toString())
			}
		}
		anios.add("Detalle")
		render template:'tablaDatosCalculo', model:[titulos:titulos, titulosAnios:titulosAnios, anios:anios, tipo:tipo, id:id, etiquetaPeriodo:indicadorInstance?.etiquetaPeriodo, idIndicador:indicadorInstance?.id]
	}
	
	def getTablaDatosCalculo(Long id){
		int tipo = params.idTipo.toInteger()
		def indicadorInstance = Indicador.get(id)
		def List<RVariable> datosCalculo = new ArrayList<RVariable>()
		params.paginado = false
		def anios = getAnosPorIndicador(id,tipo)
		
		anios.each{
			def rVariables = obtenerDatosCalculo(indicadorInstance, it.anio, tipo)
			if(indicadorInstance.variables.size()==rVariables.size()){
				rVariables.each{
					datosCalculo.add(it)
				}
			}
		}
		
		def tamVariables = indicadorInstance.variables.size()
		if(tipo==2){
			datosCalculo.each {
				it.valores = it.valores.sort{it.region}
			}
		}else if(tipo==3){
			datosCalculo.each {
				it.valores = it.valores.sort{it.municipio}
			}
		}
		
		def datos = [] 
		if(anios.size()>0)
			datos = publicoService.getTablaDatosCalculo(datosCalculo, tamVariables, tipo, indicadorInstance.variables, anios, indicadorInstance?.etiquetaPeriodo)
		def totalRecords = datos.size()
		
		def result = [ 
			"bDestroy": true, 
			"bRetrieve": true,
			'sEcho':1, 
			'iTotalRecords':totalRecords, 
			"aaSorting" : [], 
			'iTotalDisplayRecords':totalRecords, 
			'aaData':datos, 
			'oLanguage':["sUrl": "../../datatables/language/spanish.txt"]]
		render result as JSON
	}
	
	def descargarDatosCalculo(Long id){
		def indicadorInstance = Indicador.get(id)
		int tipo = params.idTipo.toInteger()
		def titulos = []
		def anios = []
		def titulosAnios = []
		params.paginado = false
		
		titulos.add( "Variable")
		if(tipo==1){
			titulos.add("Estado")
		}else if(tipo==2){
			titulos.add("Regiones")
		}else{
			titulos.add( "Municipios")
		}
		
		def aniosIndicador = getAnosPorIndicador(id,tipo)
		
		aniosIndicador.each{
			if(indicadorInstance?.etiquetaPeriodo){
				def periodo = Periodo.get(it.periodo.toLong())
				titulosAnios.add( periodo.descripcion)
				titulos.add( periodo.descripcion + ': Total')
				titulos.add( periodo.descripcion + ': Mujeres')
				titulos.add( periodo.descripcion + ': Hombres')
			}else{
				titulosAnios.add(it.anio.toString())
				titulos.add( it.anio.toString() + ': Total')
				titulos.add( it.anio.toString() + ': Mujeres')
				titulos.add( it.anio.toString() + ': Hombres')
			}
		}
		
		def List<RVariable> datosCalculo = new ArrayList<RVariable>()
		
		aniosIndicador.each{
			def rVariables = obtenerDatosCalculo(indicadorInstance, it.anio, tipo)
			if(indicadorInstance.variables.size()==rVariables.size()){
				rVariables.each{
					datosCalculo.add(it)
				}
			}
		}
		
		def tamVariables = indicadorInstance.variables.size()
		
		if(tipo==2){
			datosCalculo.each {
				it.valores = it.valores.sort{it.region}
			}
		}else if(tipo==3){
			datosCalculo.each {
				it.valores = it.valores.sort{it.municipio}
			}
		}
		
		def datos = []
		if(aniosIndicador.size()>0)
			datos = publicoService.descargarDatosCalculo(datosCalculo, tamVariables, tipo, indicadorInstance.variables, aniosIndicador)
		
		if(params?.format && params.format != "html"){
			response.contentType = grailsApplication.config.grails.mime.types[params.format]
			response.setHeader("Content-disposition", "attachment; filename=indicadores.${params.extension}")
			
			List fields = []
			Map labels = [:]
			Map parameters = [title: indicadorInstance?.nombre]
			
			for(int i=0; i<titulos.size();i++){
				fields.add(i.toString())
				labels.put(i.toString(), titulos[i])
			}
			exportService.export(params.format, response.outputStream, datos, fields, labels, [:], parameters)
		}
	}
	
	def descargarDetalleDatosCalculo(Long id){
		def dVariableInstance = DVariable.get(params.variable.toLong())
		def indicadorInstance = Indicador.get(id)
		int tipo = params.areaGeografica.toInteger()
		def titulos = []
		params.paginado = false
		
		titulos.add("Descripción")
		if(tipo==1){
			titulos.add("Entidad Federativa")
		}else if(tipo==2){
			titulos.add("Región")
		}
		
		titulos.add("Municipio")
		titulos.add("Categoría")
		titulos.add("Año")
		titulos.add("Total")
		titulos.add("Hombres")
		titulos.add("Mujeres")
		
		def datos = publicoService.getTablaDetalleDatosEstadisticos(dVariableInstance?.categorias, params, false)
		
		if(params?.format && params.format != "html"){
			response.contentType = grailsApplication.config.grails.mime.types[params.format]
			response.setHeader("Content-disposition", "attachment; filename=indicador.${params.extension}")
			
			List fields = []
			Map labels = [:]
			Map parameters = [title: indicadorInstance?.nombre]
			
			for(int i=0; i<titulos.size();i++){
				fields.add(i.toString())
				labels.put(i.toString(), titulos[i])
			}
			exportService.export(params.format, response.outputStream, datos, fields, labels, [:], parameters)
		}
	}
	
	def getTablaIndicador(Long id){
		int sEcho = 0
		def indicadorInstance = Indicador.get(id)
		if(params.sEcho){
			sEcho = params.sEcho.toInteger()
			sEcho++
		}
		
		int tipo = params.idTipo.toInteger()
		def titulos = []
		def columnasSinOrdenar = []
		def indexColumnas = 0
		def indices = []
		
		switch (tipo){
			case 1:
				titulos.add([sTitle : "Estado"])
				indexColumnas++
				break
			case 2:
				titulos.add([sTitle : "Regi&oacute;n"])
				indexColumnas++
				break
			case 3:
				titulos.add([sTitle : "Regi&oacute;n"])
				titulos.add([sTitle : "Municipio"])
				indexColumnas+=2
				break
			case 4:
				titulos.add([sTitle : "Regi&oacute;n"])
				titulos.add([sTitle : "Municipio"])
				titulos.add([sTitle : "Localidad"])
				indexColumnas+=3
				break
		}
		
		getAnosPorIndicador(id,tipo).each{
			if(indicadorInstance?.etiquetaPeriodo){
				def periodo = Periodo.get(it.periodo.toLong())
				titulos.add([sTitle : periodo.descripcion])
			}else{
				titulos.add([sTitle : it.anio.toString()])
			}
			indices.add(indexColumnas)
			indexColumnas++
		}
		
		columnasSinOrdenar.add(bSortable: false, aTargets: indices)
		
		def metodo = createLink(controller:'publico', action:'getTablaIndicadorJson', id:id, params:[idTipo:tipo])
		def metodoDescargarCSV = createLink(controller:'publico', action:'descargarSerieHistorica', id:id, params:[idTipo:tipo, tipoDocumento:'CSV'])
		
		def result = ["bServerSide": true,
			"bProcessing": true, 
			"sAjaxSource":metodo,
			"bDestroy": true, 
			"bRetrieve": true, 
			'aoColumns':titulos, 
			'aoColumnDefs': columnasSinOrdenar,
			'oLanguage':["sUrl": "../../datatables/language/spanish.txt"]
			]
		render (template:'tablaIndicador', model:[json:result as JSON, id:id, tipo:tipo, tabla:params.tabla])
	}
	
	def getTablaIndicadorJson(Long id){
		int sEcho = 0
		if(params.paginado==null)
			params.paginado = true
		if(params.sEcho){
			sEcho = params.sEcho.toInteger()
			sEcho++
		}
		
		int tipo = params.idTipo.toInteger()
		def indicador = Indicador.get(id)
		
		DetalleIndicador detalleIndicador = visorIndicadorPaginadoV2(id,tipo,params)
		def resultadosIndicador = detalleIndicador.resultados
		
		def datos = publicoService.getTablaIndicador(resultadosIndicador, tipo)
		def totalRecords = datos.size()
		if(tipo==3){
			totalRecords = 570
		}
		
		def result = ['sEcho':sEcho, 'iTotalRecords':totalRecords, 'iTotalDisplayRecords':totalRecords, 'aaData':datos]
		
		render result as JSON
	}
	
	def descargarSerieHistorica(Long id){
		def indicadorInstance = Indicador.get(id)
		int tipo = params.idTipo.toInteger()
		def indicador = Indicador.get(id)
		
		def titulos = []
		
		switch (tipo){
			case 1:
				titulos.add( "Estado")
				break
			case 2:
				titulos.add("Región")
				break
			case 3:
				titulos.add( "Región")
				titulos.add("Municipio")
				break
			case 4:
				titulos.add( "Región")
				titulos.add( "Municipio")
				titulos.add("Localidad")
				break
		}
		
		getAnosPorIndicador(id,tipo).each{
			if(indicadorInstance?.etiquetaPeriodo){
				def periodo = Periodo.get(it.periodo.toLong())
				titulos.add( periodo.descripcion)
			}else{
				titulos.add(it.anio.toString())
			}
		}
		
		DetalleIndicador detalleIndicador = visorIndicadorPaginadoV2(id,tipo,params)
		def resultadosIndicador = detalleIndicador.resultados
		def datos = publicoService.descargarSerieHistorica(resultadosIndicador, tipo)
		
		if(params?.format && params.format != "html"){
			response.contentType = grailsApplication.config.grails.mime.types[params.format]
			response.setHeader("Content-disposition", "attachment; filename=indicadores.${params.extension}")
			
			List fields = []
			Map labels = [:]
			Map parameters = [title: indicadorInstance?.nombre]
			
			for(int i=0; i<titulos.size();i++){
				fields.add(i.toString())
				labels.put(i.toString(), titulos[i])
			}

			exportService.export(params.format, response.outputStream, datos, fields, labels, [:], parameters)
		}
	}
	
	def actualizarAreaGrafica(Long id){
		def tipo = params.idTipo.toInteger()
		def indicadorInstance = Indicador.get(id)
		def listaArea = []
		if(tipo==1){
			listaArea = Estado.getAll()
		}else if(tipo==2){
			listaArea = Region.createCriteria().list() {order("descripcion", "asc")}
		}else if(tipo==3){
			listaArea = Municipio.createCriteria().list() {order("descripcion", "asc")}
		}
		
		render(template:'selectGrafica', model:[tipo: tipo, opcionesAreaGrafica:listaArea, indicadorInstance:indicadorInstance, idSelect:params.idSelect])
	}
	
	def crearGrafica(Long id, int tipo){
		
		def jsondata = null
		
		def indicador = Indicador.get(id)
		def decimales= indicador?.decimales
		params.paginado = false
		DetalleIndicador detalleIndicador = visorIndicadorPaginadoV2(id,tipo,params)
		DetalleIndicador detalleIndicadorEstatal = null
	
		if(detalleIndicador.resultados.size()>0){
			if(tipo==2){
				detalleIndicadorEstatal = visorIndicadorPaginadoV2(id,1,params)
				detalleIndicadorEstatal.resultados.sort{it.region}
				detalleIndicador.resultados.sort{it.region}
			}else if(tipo==3){
				CollectionUtils.extendMetaClass()
				detalleIndicadorEstatal = visorIndicadorPaginadoV2(id,1,params)
				detalleIndicadorEstatal.resultados.sort{remplazarAcentos(it.region)}{remplazarAcentos(it.municipio)}
				detalleIndicador.resultados.sort{remplazarAcentos(it.region)}{remplazarAcentos(it.municipio)}
			}
			
			def resultadosIndicador = detalleIndicador.resultados.get(0)
			def resultados = []
			def titulo = "Oaxaca"
			
			for(int i=0;i<detalleIndicador.resultados.size();i++){
				def resultado = detalleIndicador.resultados.get(i)
				if(tipo==2){
					if(resultado.idRegion.toString().equals(params.idArea)){
						resultadosIndicador = resultado
					}
				}else if(tipo==3){
					if(resultado.idMunicipio.toString().equals(params.idArea)){
						resultadosIndicador = resultado
					}
				}
			}
	
			resultados = resultadosIndicador?.resultados
			switch(tipo){
				case 2:
					titulo = resultadosIndicador?.region
					break
				case 3:
					titulo = resultadosIndicador?.municipio
					break
			}
			
			//Creaci�n de arreglo para Highcharts
			def series = []
			def categorias = []
			def datos = []
			def a = [:]
			a.put("subtitle", [text: 'Indicador: ' + indicador?.nombre])
			a.put("plotOptions", [line: [dataLabels: [enabled: true, allowOverlap: true]]])
			a.put("yAxis", [title: [text: '%']])
			a.put("tooltip", [valueSuffix: '%'])
			a.put("colors", ['#6FA6E5', '#AE0606'])
			a.put("legend", [layout: "vertical", align: "right", verticalAlign: "middle", borderWidth: 0])
			
			resultados.each { result ->
				if(result?.indicador!=null){
					categorias.add(result?.anio)
					datos.add(result?.indicador.round(decimales))
				}
			}
			
			if(detalleIndicadorEstatal && detalleIndicadorEstatal.resultados.size()>0){
				def datosEstatal = []
				def categoriasEstatal = []
				def resultadosIndicadorEstatal = detalleIndicadorEstatal.resultados.get(0)
				def resultadosEstatal = resultadosIndicadorEstatal?.resultados
				resultadosEstatal.each { result ->
					if(result?.indicador!=null){
						categoriasEstatal.add(result?.anio)
						datosEstatal.add(result?.indicador.round(decimales))
					}
				}
				series.add([name:"Indicador estatal", data:datosEstatal])
				for(int i=0; i<categoriasEstatal.size();i++){
					if(categoriasEstatal[i]!=categorias[i]){
						categorias.addAll(i,categoriasEstatal[i])
						datos.addAll(i,"null")
					}
				}
			}
			
			for(int i=0; i<datos.size();i++){
				if(datos[i].equals("null")){
					datos[i] = null
				}
			}
			
			a.put("xAxis", [categories: categorias] )
			series.add([name: "Indicador", data: datos])
			a.put("series", series)
			
			//Convertir el arreglo a JSON
			jsondata = a as JSON
		}
		return jsondata
	}
	
	def actualizarGrafica(Long id){
		def tipo = params.idTipo.toInteger()
		def jsondata = crearGrafica(id, tipo)
		String nivel = "Oaxaca"
		switch(tipo){
			case 2:
				nivel = Region.get(params.idArea.toLong())
				break
			case 3:
				nivel = Municipio.get(params.idArea.toLong())
				break
		}
		render(template:"graficaIndicador", model:[tablaJSON:jsondata, nivel: nivel])
	}
	
	def actualizarGraficaDatosCalculo(Long id){
		def tipo = params.idTipo.toInteger()
		def jsondata = crearGraficaDatosCalculo(id, tipo, params.idArea.toLong())
		String nivel = "Oaxaca"
		switch(tipo){
			case 2:
				nivel = Region.get(params.idArea.toLong())
				break
			case 3:
				nivel = Municipio.get(params.idArea.toLong())
				break
		}
		render(template:"graficaDatosCalculo", model:[tablaJSON:jsondata, nivel: nivel])
	}
	
	def crearGraficaDatosCalculo(Long id, int tipo, Long idArea){
		
		params.idUbicacion = idArea
		def indicadorInstance = Indicador.get(id)
		def List<RVariable> datosCalculo = new ArrayList<RVariable>()
		params.paginado = false
		def anios = getAnosPorIndicador(id,tipo)
		
		anios.each{
			def rVariables = obtenerDatosCalculo(indicadorInstance, it.anio, tipo)
			if(indicadorInstance.variables.size()==rVariables.size()){
				rVariables.each{
					datosCalculo.add(it)
				}
			}
		}
		
		def tamVariables = indicadorInstance.variables.size()
		if(tipo==2){
			datosCalculo.each {
				it.valores = it.valores.sort{it.region}
			}
		}else if(tipo==3){
			datosCalculo.each {
				it.valores = it.valores.sort{it.municipio}
			}
		}
		
		def datos = [:]
		if(anios.size()>0)
			datos = publicoService.getGraficaDatosCalculo(datosCalculo, tamVariables, tipo, indicadorInstance.variables, anios, indicadorInstance?.etiquetaPeriodo)
		
		def jsondata = null
		def decimales= indicadorInstance?.decimales
		DetalleIndicador detalleIndicadorEstatal = null
	
		if(datos.size()>0){
			if(tipo==2){
				detalleIndicadorEstatal = visorIndicadorPaginadoV2(id,1,params)
				detalleIndicadorEstatal.resultados.sort{it.region}
			}else if(tipo==3){
				CollectionUtils.extendMetaClass()
				detalleIndicadorEstatal = visorIndicadorPaginadoV2(id,1,params)
				detalleIndicadorEstatal.resultados.sort{remplazarAcentos(it.region)}{remplazarAcentos(it.municipio)}
			}
			
			//Creaci�n de arreglo para Highcharts
			def series = []
			def categorias = []
//			def datos = []
			def a = [:]
			a.put("subtitle", [text: 'Indicador: ' + indicadorInstance?.nombre])
			a.put("plotOptions", [line: [dataLabels: [enabled: true, allowOverlap: true]]])
			a.put("yAxis", [title: [text: '']])
			a.put("tooltip", [valueSuffix: ''])
			a.put("colors", ['#6FA6E5', '#AE0606'])
			a.put("legend", [layout: "horizontal", align: "center", verticalAlign: "bottom", borderWidth: 0])
			
			anios.each{
				categorias.add(it.anio)
			}
			
			a.put("xAxis", [categories: categorias] )
			
			datos.titulos.eachWithIndex{ v, i ->
				series.add([name: v, data: datos.datos[i]])
			}
			
			a.put("series", series)
			
			//Convertir el arreglo a JSON
			jsondata = a as JSON
		}
		return jsondata
	}
	
	def getTablaBuscador(){
		int sEcho = 0
		if(params.sEcho){
			sEcho = params.sEcho.toInteger()
			sEcho++
		}
		
		def datos = tablasService.getTablaBuscador(params, false)
		def totalRecords = tablasService.getTablaBuscador(params, true)
		
		def result = ['sEcho':sEcho, 'iTotalRecords':totalRecords, 'iTotalDisplayRecords':totalRecords, 'aaData':datos]
		render result as JSON
	}
	
	def getAnosPorIndicador(Long id, int idTipo){
		def sqlAnio = new Sql(sessionFactory.currentSession.connection())
		def indicadorInstance = Indicador.get(id)
		def titulos = []
		String claves = ""
		String periodo = ""
		String tipo = ""
		def variables = indicadorInstance.variables
		int tamVariables = variables.size()-1
		
		variables.eachWithIndex { v, cont ->
			if(v?.categorias){
				int tamCategorias = v.categorias.size()-1
				claves +=" (("
				v.categorias.eachWithIndex{ categoria, i ->
					claves+= " cvc_cct_id = "+categoria.id
					if(i!=tamCategorias){
						claves += " or "
					}
				}
				claves += ") and cvv_clave = '" + v.claveVar + "')"
			}else{
				claves += " cvv_clave = '" + v.claveVar + "'"
			}
			
			if(cont!=tamVariables){
				claves += " or "
			}
			
		}
		
		if(idTipo == 2)
			tipo = "and cvv_region is not null"
		else if(idTipo == 3)
			tipo = "and cvv_municipio is not null"
		
		if(indicadorInstance?.etiquetaPeriodo)
			periodo = " and cvv_ped_id is not null "
		else
			periodo = " and cvv_ped_id is null "
		
		String sqlAnios = """
			select count(anio) v, anio, cvv_ped_id periodo from (
				select DISTINCT (cvv_anio) as anio, cvv_clave, cvv_ped_id   
				from cat_variable left join cat_variable_categoria on (cvv_id = cvc_cvv_id)
				where (${claves}) ${periodo} ${tipo}
			) as p 
			group by anio, cvv_ped_id
			having count(anio) = (select count(*) from cat_dvariable where cdv_ind_id = ${id})
			order by 2
		"""
		
		def aniosCondicionales = " and ("
		def anios = sqlAnio.rows(sqlAnios)
		
		if(anios.size()!=0){
			anios.eachWithIndex(){ anio, i ->
				aniosCondicionales += "cvv_anio = ("+ anio.anio +"+cdv_intervalo)"
				if(i!=anios.size()-1)
					aniosCondicionales +=" or "
			}
			
			String sqlPeriodo = """
				select count(anio) v, anio, cvv_ped_id periodo from (
					select DISTINCT (cvv_anio-cdv_intervalo) as anio, cdv_intervalo, cvv_clave, cvv_ped_id  
					from cat_dvariable join cat_variable on (cdv_clavevar = cvv_clave) left join cat_variable_categoria on (cvv_id = cvc_cvv_id)
					where (${claves}) ${periodo} ${tipo}  and cdv_ind_id = ${id}
					${aniosCondicionales})
				) as p 
				group by anio, cvv_ped_id
				having count(anio) = (select count(*) from cat_dvariable where cdv_ind_id = ${id})
				order by 2
			"""
			
			titulos = sqlAnio.rows(sqlPeriodo)
		}
		sqlAnio.close()
		
		return titulos
	}
	
	def descargarDocumento(){
		def path = grailsApplication.config.mx.indesti.cednna.valores.directoriouploads + params.tipo + "/" + params.nivel + "/" + params.documento
		descargarArchivo(path)
	}
	
	def descargarManual(){
		def valorInstance = Valor.findByKey("rutaManualPublico")
		
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
	
	def actualizarTablaDocumento(Integer id){
		render template:'tablaDocumento', model:[nivel: params.nivel, tipo:id]
	}
	
	def enviarMensaje(){
		def correo = Valor.findByKey('correoContacto')
		String mensaje = params.mensaje
		String correro = params.correo
		String nombre = params.nombre!=''? ("Atentamente: " + params.nombre + "."):''
		sendMail {
			to correo?.valor
		   subject params.asunto
		   html  """
		   			${mensaje} <br><br>
					Correo: ${correro}<br>
		   			${nombre}
		   		"""
		}
		
		String nombreRespuesta = params.nombre!=''?(', ' +params.nombre) + '.':''
		
		sendMail {
			to params?.correo
		   subject 'Respuesta CEDNNA'
		   html  """
		   			<b>Gracias ${nombreRespuesta}<b> <br><br>
		   			Hemos recibido tu mensaje, en breve te responderemos.<br>
		   			No responda a este correo.
		   		"""
		}
		redirect action:'contacto' 
	}
	
	DetalleIndicador visorIndicadorPaginadoV2(Long id,idTipo,params){
		def indicadorInstance = Indicador.get(id);
		def opcion = idTipo;
		def formula =  indicadorInstance?.formula?.sentencia
		def sentencia = indicadorInstance?.formula?.variables
		def variables = sentencia.split("\\|")
		
		List<ResultadoIndicador> resultados= new ArrayList<ResultadoIndicador>()
		List<RVariable> resutaldoVariables = new ArrayList<RVariable>()
		List<RVariable> rVariables = new ArrayList<RVariable>()
		List<ResultadoTemporal> listTemp = new ArrayList<ResultadoTemporal>()
		RVariable temVar

		def num=0
		def letra
		def valorBase
		
		def aniosPorBuscar = getAnosPorIndicador(id,idTipo)
		
		aniosPorBuscar.each{
			def anio = it.anio
			boolean b = true
			
			switch (opcion) {
				case 1:
				/***
				 * PROCESO DE SALIDA POR ESTADO
				 *
				 * */

				/***
				 * Comienza la busqueda en el origen de datos en base a las variable
				 * */
					
					rVariables = obtenerDatosCalculo(indicadorInstance, anio, opcion)
					rVariables.each{
						resutaldoVariables.add(it)
					}
					
				/***
				 * Comienza el calculo del indicador en base a las variables
				 * */
					
					if(indicadorInstance.variables.size()!=rVariables.size()){
						rVariables.each{
							resutaldoVariables.remove(it)
						}
						rVariables = []
					}
					
					if(rVariables.size()>0){
						rVariables.each { var->
							if(var?.valores?.get(0)?.indicador!=null){
								formula=formula.replaceAll(var.letra, String.valueOf(var?.valores?.get(0)?.indicador))
							}
						}

						ResultadoTemporal rTemp = new ResultadoTemporal()

						ScriptEngineManager script = new ScriptEngineManager();
						ScriptEngine js = script.getEngineByName("JavaScript");
						
						try {
							rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
							if(rTemp.resultadoIndicador.toString().equals('Infinity')){
								rTemp.resultadoIndicador = null
							}
							rTemp.anio=anio
							listTemp.add(rTemp)
							
							if(Double.isNaN(rTemp.resultadoIndicador)){
								rTemp.resultadoIndicador = null
							}
						} catch (Exception e) {
							rTemp.resultadoIndicador = null
							rTemp.anio=anio
							listTemp.add(rTemp)
						}
						formula= indicadorInstance?.formula?.sentencia
					}

				/***
				 * Comienza el proceso de ordenamiento para salida
				 * */
					listTemp.each { actual->
						Resultado res= new Resultado()
						res.anio=actual.anio
						res.indicador=actual.resultadoIndicador
						
						if(resultados.size()>0){
							resultados.get(0).resultados.add(res)
						}else{
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
					rVariables = obtenerDatosCalculo(indicadorInstance, anio, opcion)
					rVariables.each{
						resutaldoVariables.add(it)
					}

				/***
				 * Comienza el calculo del indicador en base a las variables
				 * */
					if(indicadorInstance.variables.size()!=rVariables.size()){
						rVariables.each{
							resutaldoVariables.remove(it)
						}
						rVariables = []
					}
					
					if(rVariables.size()>0){
						num=rVariables[0].valores.size()
						letra=rVariables.get(0).letra
						valorBase=rVariables[0].valores
						
						valorBase.each { base->
							rVariables.each { var->
								formula=formula.replaceAll(var.letra, String.valueOf(var.valores.find{it.idRegion == base.idRegion}?.indicador))
							}

							ResultadoTemporal rTemp = new ResultadoTemporal()
							ScriptEngineManager script = new ScriptEngineManager();
							ScriptEngine js = script.getEngineByName("JavaScript");
							
							try {
								rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
								if(rTemp.resultadoIndicador.toString().equals('Infinity')){
									rTemp.resultadoIndicador = null
								}
								rTemp.region= base.region
								rTemp.idRegion= base.idRegion
								rTemp.anio=base.anio
								listTemp.add(rTemp)
								
								if(Double.isNaN(rTemp.resultadoIndicador)){
									rTemp.resultadoIndicador = null
								}

							} catch (Exception e) {
								rTemp.resultadoIndicador = null
								rTemp.region= base.region
								rTemp.idRegion= base.idRegion
								rTemp.anio=base.anio
								listTemp.add(rTemp)
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
					rVariables = obtenerDatosCalculo(indicadorInstance, anio, opcion)
					rVariables.each{
						resutaldoVariables.add(it)
					}

				/***
				 * Comienza el calculo del indicador en base a las variables
				 * */
					if(indicadorInstance.variables.size()!=rVariables.size()){
						rVariables.each{
							resutaldoVariables.remove(it)
						}
						rVariables = []
					}

					if(rVariables.size()>0){
						num=rVariables[0].valores.size()
						letra=rVariables.get(0).letra
						valorBase=rVariables[0].valores
						
						valorBase.each { base->
							rVariables.each { var->
								formula=formula.replaceAll(var.letra, String.valueOf(var.valores.find{it.idMunicipio == base.idMunicipio}?.indicador))
							}
							
							ResultadoTemporal rTemp = new ResultadoTemporal()
							ScriptEngineManager script = new ScriptEngineManager();
							ScriptEngine js = script.getEngineByName("JavaScript");
							
							try {
								rTemp.resultadoIndicador =js.eval("eval('"+formula+"')")
								if(rTemp.resultadoIndicador.toString().equals('Infinity')){
									rTemp.resultadoIndicador = null
								}
								rTemp.region= base.region
								rTemp.idRegion= base.idRegion
								rTemp.municipio= base.municipio
								rTemp.idMunicipio= base.idMunicipio
								rTemp.anio=base.anio
								listTemp.add(rTemp)
								
								if(Double.isNaN(rTemp.resultadoIndicador)){
									rTemp.resultadoIndicador = null
								}

							} catch (Exception e) {
								rTemp.resultadoIndicador = null
								rTemp.region= base.region
								rTemp.idRegion= base.idRegion
								rTemp.municipio= base.municipio
								rTemp.idMunicipio= base.idMunicipio
								rTemp.anio=base.anio
								listTemp.add(rTemp)
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
			}
		}
		
		DetalleIndicador detalleIndicador = new DetalleIndicador()
		detalleIndicador.resultados = resultados
		detalleIndicador.rVariables = resutaldoVariables

		return detalleIndicador
	}
	
	def obtenerDatosCalculo(def indicadorInstance, int anio, int opcion){
		List<RVariable> resutaldoVariables = new ArrayList<RVariable>()
		List<RVariable> rVariables = new ArrayList<RVariable>()
		RVariable temVar
		
		for(vari in indicadorInstance.variables.sort{it.clave}){
			int intervalo = vari?.intervalo
			def periodo = anio+intervalo
			
			List variableInstanceList = obtenerListaVariablesPorTipo(opcion, vari, periodo, params)
			def catOrigenDatos = CatOrigenDatos.findByClave(vari.claveVar)

			if(variableInstanceList.size()>0){
				temVar= new RVariable()
				temVar.letra=vari.clave
				temVar.mostrarCiclo=vari.mostrarCiclo 
				temVar.idDVariable = vari.id
				def clave = vari.poblacion.clave
				
				variableInstanceList?.each{
					ResultadoTemporal valorTem = new ResultadoTemporal()
					switch (clave) {
						case "H":
							valorTem.indicador=it.hombres
							break;
						case "M":
							valorTem.indicador=it.mujeres
							break;
						case "T":
							valorTem.indicador=it.total
							break;
						default:
							break;
					}
					
					valorTem.hombres = it.hombres
					valorTem.mujeres = it.mujeres
					
					if(opcion==2 || opcion==3){
						valorTem.region=it.region
						valorTem.idRegion =it.region_id
					}
					if(opcion==3){
						valorTem.municipio=it.municipio
						valorTem.idMunicipio = it.municipio_id
					}
					
					valorTem.poblacion = vari.poblacion.id
					valorTem.anio=anio
					temVar.valores.add(valorTem)
					temVar.descripcion=catOrigenDatos?.descripcion
				}
				
				rVariables.add(temVar)
				resutaldoVariables.add(temVar)
			}
		}
		return resutaldoVariables
	}
	
	def obtenerListaVariablesPorTipo(int tipo, def dVariable, int periodo, params){
		def paginado = [:]
		if(params.iDisplayLength)
			paginado = [max:params.iDisplayLength, offset:params.iDisplayStart]
		
		def variableInstanceList = Variable.createCriteria().list(*:paginado) {
			resultTransformer(CriteriaSpecification.ALIAS_TO_ENTITY_MAP)
			projections{
				if(tipo==2 || tipo==3){
					region{
						groupProperty "id", "region_id"
						groupProperty "descripcion", "region"
						if(tipo==2 && params.idUbicacion){
							eq("id", params.idUbicacion.toLong())
						}
					}
				}
				if(tipo==3){
					municipio{
						groupProperty "descripcion", "municipio"
						groupProperty "id", "municipio_id"
						if(params.idUbicacion){
							eq("id", params.idUbicacion.toLong())
						}
					}
				}
				
				groupProperty "clave", "clave"
				groupProperty "descripcion", "descripcion"
				sum "mujeres", "mujeres"
				sum "hombres", "hombres"
				sum "poblacionTotal", "total"
			}
			eq("clave",dVariable.claveVar )
			eq("anio",periodo )
			
			if(tipo==2){
				isNotNull("region")
				if(params?.sSearch!=null && params?.sSearch!=''){
					region{
						sqlRestriction("UPPER(crg_descripcion) like UPPER ('%${params?.sSearch}%')")
					}
				}
			}else if(tipo==3){
				isNotNull("region")
				isNotNull("municipio")
				if(params?.sSearch!=null && params?.sSearch!=''){
					or{
						region{
							sqlRestriction("UPPER(crg_descripcion) like UPPER ('%${params?.sSearch}%')")
						}
						municipio{
							sqlRestriction("UPPER(mun_descripcion) like UPPER ('%${params?.sSearch}%')")
						}
					}
				}
			}
			
			if(dVariable?.categorias){
				categorias{
					inList("id", dVariable.categorias.id )
				}
			}
			
			if(params.paginado && tipo==2){
				if(params.iSortCol_0){
					switch(params.iSortCol_0){
						case '0':
							order("region", params.sSortDir_0)
							break
					}
				}
			}else if(params.paginado && tipo==3){
				if(params.iSortCol_0){
					switch(params.iSortCol_0){
						case '0':
							order("region", params.sSortDir_0)
							break
						case '1':
							order("municipio", params.sSortDir_0)
							break
					}
				}
			}
			
			if(params.paginado && (tipo==2 || tipo==3)){
				order("region", params.sSortDir_0)
			}
		}
		return variableInstanceList
	}
	
	def mostrarDetalleDatosCalculo(Long id){
		def dVariableInstance = DVariable.get(params.variable.toLong())
		render(template:'detalleDatosCalculo', model:[dVariableInstance:dVariableInstance, variable:params.variable, areaGeografica:params.areaGeografica, idIndicador:id, idCategoria:params.idCategoria])
	}
	
	def getDetalleDatosCalculo(Long id){
		def dVariableInstance = DVariable.get(params.variable.toLong())
		int sEcho = 0
		params.paginado = true
		if(params.sEcho){
			sEcho = params.sEcho.toInteger()
			sEcho++
		}
		
		def variableList = publicoService.getTablaDetalleDatosEstadisticos(dVariableInstance?.categorias, params, false)
		def totalRecords = publicoService.getTablaDetalleDatosEstadisticos(dVariableInstance?.categorias, params, true)
		
		def result = ['sEcho':sEcho, 'iTotalRecords':totalRecords, 'iTotalDisplayRecords':totalRecords, 'aaData':variableList]
		
		render result as JSON
	}
	
	def renderImage(Long id){
		def image
		try {
			image = archivoService.cargarImagen(params.ruta)
			if (image!=null){
				String ext = FilenameUtils.getExtension(params.ruta)
				render(archivoService.mostrarImagen(image, ext, response.getOutputStream()))
				return
			}else{
				throw new Exception()
			}
		}
		catch(Exception ex){
			ex.printStackTrace()
		}
	}
	
	
}	
