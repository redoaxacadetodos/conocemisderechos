package mx.gob.redoaxaca.cednna.domino

import java.util.List;

class Localidad {

	String clave
	String descripcion
	Municipio municipio
	List coordenadas
	
	static hasMany = [coordenadas: Coordenada]

	String toString() {
		"${descripcion}"
	}

	boolean equals(Localidad l) {
		if(this.descripcion==l.descripcion)
			return true
		return false
	}
	
    static constraints = {
    }

      static mapping = {
	table('cat_localidad')
	id(column: 'ctl_id')


	
	descripcion(column:'ctl_descripcion')
	clave(column:'ctl_clave')
	municipio(column:'ctl_mun_id')

	version(false)
  }    
}
