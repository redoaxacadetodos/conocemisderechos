package mx.gob.redoaxaca.cednna.domino

class Logotipo {
	
	String ruta
	String titulo
	Integer orden

    static constraints = {
    }
	
	static mapping = {
		table 'cat_logotipo'
		id column:'log_id', generator: 'sequence', params: [sequence: 'seq_log_id']
		ruta column:'log_ruta'
		titulo column:'log_titulo'
		orden column:'log_orden'
		version false
	}
}
