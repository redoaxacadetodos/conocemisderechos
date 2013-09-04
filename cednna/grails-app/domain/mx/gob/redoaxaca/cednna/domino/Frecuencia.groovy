package mx.gob.redoaxaca.cednna.domino

class Frecuencia {

		String descripcion
		int    nmeses
	
    static constraints = {
		
    }

  static mapping = {
		table('cat_frecuencia')
		id(column: 'cfr_id')

		descripcion(column: 'cfr_descripcion')
		nmeses(column: 'cfr_nmeses')

		version(false)
	}

}
