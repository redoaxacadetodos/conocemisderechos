package mx.gob.redoaxaca.cednna.domino

class Tipo {
	
	String descripcion
	
	
    static constraints = {
    }

      static mapping = {
		table('cat_tipo')
		id(column: 'ctt_id')

		descripcion(column: 'ctt_descripcion')
		
		version(false)
	}
}
