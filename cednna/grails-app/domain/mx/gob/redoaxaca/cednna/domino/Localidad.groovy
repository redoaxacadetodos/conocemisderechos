package mx.gob.redoaxaca.cednna.domino

class Localidad {

	String clave
	String descripcion
	Municipio municipio

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
}
