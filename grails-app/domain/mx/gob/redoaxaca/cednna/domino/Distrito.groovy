package mx.gob.redoaxaca.cednna.domino

class Distrito {

	String clave
	String descripcion
	Estado entidad
	
	boolean equals(Distrito d) {
		if(this.descripcion==d.descripcion)
			return true
		return false
	}
	
	static constraints = {
	}

	   static mapping = {
		table('cat_distrito')
		id(column: 'cdt_id')

		descripcion(column: 'cdt_descripcion')
		clave(column: 'cdt_clave')

		entidad(column: 'cdt_ent_id')
		
		version(false)
	}
	
}