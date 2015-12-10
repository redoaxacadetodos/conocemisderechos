package mx.gob.redoaxaca.cednna.domino

class Coordenada{
	
	String longitud
	String latitud
	String altitud
	int tipo
	
    static constraints = {
		tipo nullable:true, blank:true
    }	
	
	static mapping = {
		
		version false
	}	
}
