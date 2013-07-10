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
	
}