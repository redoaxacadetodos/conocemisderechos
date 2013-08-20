package mx.gob.redoaxaca.cednna.publico

import javax.script.ScriptEngine
import javax.script.ScriptEngineManager
import mx.gob.redoaxaca.cednna.domino.*
import com.redoaxaca.java.Resultado
import grails.plugins.springsecurity.Secured

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
		render(template: "detalleIndicador", model: [indicador: indicador])
	}
	
	
	
	def visor(){
		
		
		def indicadorInstance = Indicador.get(params.id);
		
		
	
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
		[indicadorInstance:indicadorInstance,resultados:resultados]
	}
}
