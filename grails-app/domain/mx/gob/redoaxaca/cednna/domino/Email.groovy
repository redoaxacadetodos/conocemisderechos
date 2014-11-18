package mx.gob.redoaxaca.cednna.domino

class Email {
	String email
	
	static belongsTo =[contacto: Contacto]
	
	static mapping = {
		version(false)
	}

    static constraints = {
    }
}
