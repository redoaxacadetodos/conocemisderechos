package mx.gob.redoaxaca.cednna

import org.codehaus.groovy.grails.web.json.JSONArray
import grails.converters.JSON
import groovy.json.JsonSlurper

class PublicoService {

    def serviceMethod() {

    }



    def getTablaIndicador(resultadosIndicador, tipo){
    	def list = []


    	
    	resultadosIndicador.each{ lista ->
    		def listRow = [:]
    		int index = 0
    		
    		println 'it1:'+lista
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
    	
    	return list
    }
}
