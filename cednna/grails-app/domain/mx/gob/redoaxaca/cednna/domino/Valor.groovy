package mx.gob.redoaxaca.cednna.domino

class Valor {
	
	String key
	String valor

    static constraints = {
		key unique : true
    }
	
	static mapping = {
		table 'sys_valores'
		id column: 'val_id'
		key column: 'val_key'
		valor column: 'val_valor'
		version false
	}
}
