package mx.gob.redoaxaca.cednna.domino

class Region {
	
	String descripcion
	
    static constraints = {
    }

      static mapping = {
	table('cat_region')
	id(column: 'crg_id')


	
	descripcion(column:'crg_descripcion')

	version(false)
  }    
}
