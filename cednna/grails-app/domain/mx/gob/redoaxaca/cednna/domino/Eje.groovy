package mx.gob.redoaxaca.cednna.domino

class Eje {

	List division
	String descripcion
	
	static hasMany = [division:Division]
	
	
    static constraints = {
    }
}
