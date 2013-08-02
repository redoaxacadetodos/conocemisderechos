package mx.gob.redoaxaca.cednna.domino

class Municipio {

	String clave
	String descripcion
	Distrito distrito

    static constraints = {
    }

	String toString() {
		"${descripcion}"
	}

	boolean equals(Municipio m) {
		if(this.descripcion==m.descripcion)
			return true
		return false
	}
	
  static mapping = {
	table('cat_municipio')
	id(column: 'mun_id')


	
	descripcion(column:'mun_descripcion')
	clave(column:'mun_clave')
	distrito(column:'mun_cdt_id')

	version(false)
  }    
	
    
}
