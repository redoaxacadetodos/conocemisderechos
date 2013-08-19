package mx.gob.redoaxaca.cednna.publico

import mx.gob.redoaxaca.cednna.domino.*
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
}
