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
	

	
    
}
