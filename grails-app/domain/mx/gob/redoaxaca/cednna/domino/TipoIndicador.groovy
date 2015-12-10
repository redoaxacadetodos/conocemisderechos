package mx.gob.redoaxaca.cednna.domino

class TipoIndicador {
	
	String descripcion

    static constraints = {
    }
	
	static mapping = {
		table 'cat_tipoIndicador'
		id column:'tid_id'
		descripcion column:'tid_descripcion'
		
		version false
	}
}
