package mx.gob.redoaxaca.cednna.domino

class Infografia {
	
	String ruta
	String titulo
	Integer orden

    static constraints = {
		
    }
	
	static mapping = {
		table 'cat_infografia'
		id column:'inf_id', generator: 'sequence', params: [sequence: 'sec_inf_id']
		ruta column:'inf_ruta'
		titulo column:'inf_titulo'
		orden column:'inf_orden'
		version false
	}
}
