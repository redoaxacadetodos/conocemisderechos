package mx.gob.redoaxaca.cednna.domino

import java.util.List;

class Region {
	
	String descripcion
	String clave
	List coordenadas
	
	static hasMany = [coordenadas: Coordenada]
	
		String toString() {
			"${descripcion}"
		}
	
		boolean equals(Region r) {
			if(this.descripcion==r.descripcion)
				return true
			return false
		}
		
	

	
	
    static constraints = {
    }

      static mapping = {
	table('cat_region')
	id(column: 'crg_id')
	clave(column: 'crg_clave')
	descripcion(column:'crg_descripcion')

	version(false)
  }    
}
