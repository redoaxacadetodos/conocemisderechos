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
	
    
}
