package mx.gob.redoaxaca.cednna

import mx.gob.redoaxaca.cednna.domino.CatOrigenDatos

class TablasService {

    def serviceMethod() {

    }

    def getTablaVariables(){
    	def list = []
    	def variables = CatOrigenDatos.list()
    	variables.each{
    		list<<[
    			'0':"<a href='/cednna/catOrigenDatos/show/"+it.id+"'>"+it.clave+"</a>",
    			'1':it.descripcion
    		]	
    	}

    	return list
    }
}
