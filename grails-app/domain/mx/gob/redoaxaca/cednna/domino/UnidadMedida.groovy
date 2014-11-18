package mx.gob.redoaxaca.cednna.domino

class UnidadMedida {

	String descripcion
	String abreviatura
	
    static constraints = {
    }
	
	static mapping = {
		table('cat_unidad_medida')
		id(column: 'cum_id')
	
	
		
		descripcion(column:'cum_descripcion')
		abreviatura(column:'cum_abreviatura')
			
	
		version(false)
	  }
	
	
}
