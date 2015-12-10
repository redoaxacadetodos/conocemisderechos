package mx.gob.redoaxaca.cednna.domino

class Dimension {
	
	String descripcion

    static constraints = {
    }
	
	static mapping = {
		table 'cat_dimension'
		id column:'dim_id'
		descripcion column:'dim_descripcion'
		
		version false
	}
}
