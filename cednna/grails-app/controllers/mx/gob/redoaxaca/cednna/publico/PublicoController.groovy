package mx.gob.redoaxaca.cednna.publico

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import mx.gob.redoaxaca.cednna.domino.*

import com.redoaxaca.java.Resultado
import grails.converters.JSON
import grails.plugins.springsecurity.Secured
import groovy.json.JsonBuilder

@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
class PublicoController {

    def index() { 
		
	}
	
	def calendario = {
		
	}
	
	def directorio = {
	}
	
	def indicadores = {
		
	}
	
	def contacto = {
		
	}
	
	def ayuda = {
		
	}
	
	def infoIndicador (Long id) {
		def eje = Eje.get(id)
		def divisiones = eje.division
	
		render(template:"division", model: [divisiones: divisiones])
	}
	
	def detalleIndicador (Long id){
		def indicador = Indicador.get(id)
		def resultados = visor(id)
		
		//Prueba
		/*
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
		*/
		
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
		System.out.println(categorias)
		def serie = [name: "Indicador", data: datos]
		series << serie
		a.put("series", series)
		
		def jsodata = a as JSON		
		
		System.out.println(jsodata)
		[indicadorInstance: indicador, resultados:resultados, tablaJSON: jsodata]
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
