package mx.gob.redoaxaca.cednna

import org.codehaus.groovy.grails.web.json.JSONArray
import grails.converters.JSON
import groovy.json.JsonSlurper
import grails.plugins.springsecurity.Secured

@Secured( ['IS_AUTHENTICATED_ANONYMOUSLY'])
class PublicoService {

    def serviceMethod() {

    }



    def getTablaIndicador(resultadosIndicador, tipo){
    	def list = []

    	if(resultadosIndicador!=null){
	    	resultadosIndicador.each{ lista ->
	    		def listRow = [:]
	    		int index = 0
	    		
	    		if(tipo==1){
	    			listRow.put(index,'Oaxaca')  
	    			index++  
	    		}

	    		lista.each{ resultadoIndicador ->	    			
	    			if(tipo==2 || tipo==3 || tipo==4){
	    				listRow.put(index,resultadoIndicador?.region) 
	    				index++
	    			}
	    			if(tipo==3 || tipo==4){    				
	    				listRow.put(index,resultadoIndicador?.municipio)
	    				index++
	    			}
	    			
	    			resultadoIndicador?.resultados.each{ result ->
	    				listRow.put(index,result?.indicador==null ? '-':result?.indicador.round(2)) 	
	    				index++    				
	    			}
	    		}

	    		list.addAll(0,listRow)	    	
	    	}
    	}
    	return list
    }

    def getTablaDatosCalculo(datosCalculo, tamVariables, tipo){
    	def list = []

    	if(datosCalculo!=null){
	    	datosCalculo.eachWithIndex(){ datos, i ->
	    		datos?.valores.each{ valor ->
		    		def listRow = [:]
		    		int index = 0

		    		if(i<tamVariables){
		    			listRow.put(index, datos.descripcion)
		    			index++
		    			if(tipo==1){
		    				listRow.put(index, 'Oaxaca')
		    				index++
		    			}else if(tipo==2){
		    				listRow.put(index, valor.region)
		    				index++
		    			}else{
		    				listRow.put(index, valor.municipio)
		    				index++
		    			}

		    			datosCalculo.eachWithIndex(){ datosAux, cont ->
			    			if(((cont+i) % tamVariables) == 0){
			    				datosAux?.valores.each{ valores ->	

			    					if(tipo==1){
					    				listRow.put(index, valores.indicador)
					    				index++
					    			}else if(tipo==2 && valor.region==valores.region){
					    				listRow.put(index, valores.indicador)
					    				index++		
					    			}else if(tipo==3 && valor.municipio.equals(valores.municipio)){
					    				listRow.put(index, valores.indicador)
					    				index++
					    			}
			    				}
			    			}
			    		}
		    		}

		    		if(listRow.size()!=0){
		    			list.addAll(0,listRow)	
		    		}
		    		
	    		}
	    	}	
		}
		
    	return list
    }
}
