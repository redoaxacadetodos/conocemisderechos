package mx.gob.redoaxaca.cednna.domino

class Coordenada {
	
	String longitud
	String latitud
	String altitud
	
	static belongsTo = [municipio:Municipio, estado:Estado, region:Region, localidad:Localidad]
	
    static constraints = {
    }		
}
