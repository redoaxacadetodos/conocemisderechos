package mx.gob.redoaxaca.cednna.domino

class Estado {

	 String clave
    String descripcion
    String prefijo

	
    static constraints = {
    }

    String toString() {
        "${descripcion}"
    }

	boolean equals(Estado e) {
		if(this.clave==e.clave)
			return true
		return false
	 }

	  static mapping = {
		table('cat_entidad')
		id(column: 'ent_id')

		descripcion(column: 'ent_descripcion')
		clave(column: 'ent_clave')
		prefijo(column: 'ent_prefijo')
	
		version(false)
	}
	
    
}
