package mx.gob.redoaxaca.cednna.domino

class TipoEje {
	
	String tipo

    static constraints = {
    }
	
	static mapping = {
		table 'cat_tipo_eje'
		id column:'eje_id'
		tipo column:'eje_tipo'
		version false
	}
}
