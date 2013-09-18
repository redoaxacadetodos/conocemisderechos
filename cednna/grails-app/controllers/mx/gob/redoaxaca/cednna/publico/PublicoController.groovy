package mx.gob.redoaxaca.cednna.publico

import com.redoaxaca.java.Resultado
import com.redoaxaca.java.ResultadoIndicador
import com.redoaxaca.kml.LeerKml
import com.redoaxaca.kml.ObtenerCoordenadas
import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager

import mx.gob.redoaxaca.cednna.domino.*


@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
class PublicoController {

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
		ObtenerCoordenadas kml = new ObtenerCoordenadas()		
		/*
		def municipios = Municipio.list()
		municipios.each { muni ->
			def coordenadas = kml.obtenerCoordenadas(grailsAttributes.getApplicationContext().getResource("kml/doc.kml").getFile(), muni.id)
			coordenadas.each { coo->				
				coo.save(flush: true)
			}		
		}
		*/
		def coordenadas = kml.obtenerCoordenadas(grailsAttributes.getApplicationContext().getResource("kml/doc.kml").getFile(), 1)
		/*
		def municipio = Municipio.createCriteria().list{  
			order("id", "asc")
		}
		municipio.each { muni ->			
			if(kml.quitarCaracteres(muni.descripcion.toUpperCase()).equals("ABEJONES")){
				System.out.println("entra "+muni.descripcion)
			}
		}*/
		//kml.quitarCaracteres("Ábéjónes")
		//System.out.println(kml.quitarCaracteres("SANTA MARÍA GUELACÉ"))
		
	}
	
	def contacto = {
		
	}
	
	def ayuda = {
		
	}
	
	def actualizarMapa(Long id) {
		def tipo = params.idTipo
		ResultadoIndicador resultadoIndicador = new ResultadoIndicador()		
		resultadoIndicador.region = "Valles Centrales"
		resultadoIndicador.idMunicipio = 506
		List <ResultadoIndicador> resultadosIndicador = []
		resultadosIndicador.add(resultadoIndicador)
		
		
		def ubicaciones = []
		def coordenadas = []
		def ubicacioneString = []
		ubicaciones.add(resultadoIndicador.region)		
		resultadosIndicador.each { resultado ->
			switch(tipo){
				case '1':
					
					break
				case '2':
					def region = Region.get(resultado.idRegion)					
					if(region!=null){
						def municipios = Municipio.findAllByRegion(region)
						municipios.each { muni ->
							coordenadas.add(Coordenada.findAllByMunicipio(muni))
						}
					}					
					break
				case '3':
					def municipio = Municipio.get(resultado.idMunicipio)
					def coor = Coordenada.findAllByMunicipio(municipio)
					coordenadas.add(coor)
					break
				case '4':
					break
			}			
		}
		
		ubicaciones.each { ubi ->
			ubicacioneString.add("'"+ubi+"'")
		}

		//Crear poligonos
		def coo = ""
		def pintarUbicaciones = ""
		coordenadas.each { coorde ->
			coorde.each { ubicacion ->
				coo += "new google.maps.LatLng(" + ubicacion?.latitud + ","+ubicacion?.longitud+"), "
			}
			pintarUbicaciones += " var coordenadas = [ "+coo+"];"+
				"ubicacion = new google.maps.Polygon({"+
				"paths: coordenadas,"+
				"strokeColor: '#FF0000',"+
				"strokeOpacity: 0.8,"+
				"strokeWeight: 2,"+
				"fillColor: '#FF0000',"+
				"fillOpacity: 0.35});"+
				"ubicacion.setMap(map); "
			coo = ""
		}
		render(template:"mapa", model:[pintarUbicaciones:pintarUbicaciones, ubicaciones:ubicacioneString])
	}
	
	def actualizarTablaIndicador(Long id){
		def tipo = params.idTipo
		def indicador = Indicador.get(id)
		
		def resultados = []
				
		switch(tipo){
			case '1':
				resultados = visor(id)
				break
			case '2':
				break
			case '3':
				break
			case '4':
				break
		}	
		//Prueba
		Resultado resultado = new Resultado()
		Resultado resultado2 = new Resultado()
		Resultado resultado3 = new Resultado()
		Resultado resultado4 = new Resultado()
		resultado.setAnio(2013)
		resultado.setIndicador(15)
		resultado2.setAnio(2014)
		resultado2.setIndicador(17)
		resultado3.setAnio(2015)
		resultado3.setIndicador(100)
		resultado4.setAnio(2016)
		resultado4.setIndicador(17)
		resultados.add(resultado)
		resultados.add(resultado2)
		resultados.add(resultado3)
		resultados.add(resultado4)
		
				
		ResultadoIndicador resultadoIndicador = new ResultadoIndicador() 
		resultadoIndicador.resultados = resultados
		resultadoIndicador.region = "Valles Centrales"
		List <ResultadoIndicador> resultadosIndicador = [] 
		resultadosIndicador.add(resultadoIndicador)
		resultadosIndicador.add(resultadoIndicador)
		resultadosIndicador.add(resultadoIndicador)
		
		render (template:"tablaIndicador", model:[tipo:tipo, resultados:resultados, resultadosIndicador:resultadosIndicador])	
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
			def divisiones = eje.division			
			render(template:"division", model: [divisiones: divisiones])
		}else{
		redirect(action:"indicadores")
		}		
	}
	
	def detalleIndicador (Long id){		
		if(params.infoIndicador=='true'){			
			def eje = Eje.get(id)
			if(eje){				
				def divisiones = eje.division				
				[divisiones: divisiones]
			}else{
				redirect(action:"indicadores")
			}
			
		}else{
			def indicador = Indicador.get(id)
			if(indicador){
				def resultados = visor(id)
				//Prueba
			
			Resultado resultado = new Resultado()
			Resultado resultado2 = new Resultado()
			Resultado resultado3 = new Resultado()
			Resultado resultado4 = new Resultado()
			resultado.setAnio(2013)	
			resultado.setIndicador(15)	
			resultado2.setAnio(2014)
			resultado2.setIndicador(17)
			resultado3.setAnio(2015)
			resultado3.setIndicador(100)
			resultado4.setAnio(2016)
			resultado4.setIndicador(17)
			resultados.add(resultado)
			resultados.add(resultado2)		
			resultados.add(resultado3)
			resultados.add(resultado4)
			
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
			def ubicacioneString = []
			def coordenadas = []
			
			indicador?.variables?.each { variable ->
				if(variable?.region?.descripcion){
					ubicaciones.add(variable?.region?.descripcion)								
				}else if(variable?.municipio?.descripcion){
					ubicaciones.add(variable?.municipio?.descripcion)
					def coor = Coordenada.findAllByMunicipio(variable?.municipio)
					//System.out.println("coordenadas: "+coor)
					coordenadas.add(coor)
				}else if(variable?.localidad?.descripcion){
					ubicaciones.add(variable?.localidad?.descripcion)
				}							
			}
			ubicaciones.each { ubi ->				
				ubicacioneString.add("'"+ubi+"'")
			}
			List listarResultados = []
			listarResultados.add(resultados)
			//listarResultados.add(resultados)
			
			//Crear poligonos		
			def coo = ""
			def pintarUbicaciones = ""
			coordenadas.each { coorde ->			
				coorde.each { ubicacion ->
					coo += "new google.maps.LatLng(" + ubicacion?.latitud + ","+ubicacion?.longitud+"), "
				}
				pintarUbicaciones += " var coordenadas = [ "+coo+"];"+
					"ubicacion = new google.maps.Polygon({"+
					"paths: coordenadas,"+
					"strokeColor: '#FF0000',"+
					"strokeOpacity: 0.8,"+
					"strokeWeight: 2,"+
					"fillColor: '#FF0000',"+
					"fillOpacity: 0.35});"+
					"ubicacion.setMap(map); "
				coo = ""
			}
			
			ResultadoIndicador resultadoIndicador = new ResultadoIndicador()
			resultadoIndicador.resultados = resultados
			resultadoIndicador.region = "Valles Centrales"
			List <ResultadoIndicador> resultadosIndicador = []
			resultadosIndicador.add(resultadoIndicador)
					
			[indicadorInstance: indicador, resultados:resultados, listarResultados:listarResultados, tablaJSON: jsodata, ubicaciones: ubicacioneString, pintarUbicaciones:pintarUbicaciones, resultadosIndicador:resultadosIndicador, tipo:'1']
			}
			else{
				redirect(action:"indicadores")
			}
		}
	}
	
	
	
	def List visor(Long id){
		
		
		def indicadorInstance = Indicador.get(id);
		
		
	
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
			
//			if(b){
//				System.out.println(formula);
//				def resultado = new Resultado()
//				ScriptEngineManager script = new ScriptEngineManager();
//				ScriptEngine js = script.getEngineByName("JavaScript");
//				try {
//					
//					resultado.indicador =js.eval("eval('"+formula+"')")
//					System.out.println(resultado.indicador);
//					resultado.anio=anio
//					resultados.add(resultado)
//					
//				} catch (ScriptException e) {
//					// TODO Auto-generated catch block
//					e.printStackTrace();
//				}
//			}
			
			
		}
		return resultados
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
		
}
