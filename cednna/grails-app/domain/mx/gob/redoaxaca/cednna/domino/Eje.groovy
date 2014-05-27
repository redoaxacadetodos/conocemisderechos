package mx.gob.redoaxaca.cednna.domino

class Eje {

	String descripcion
	String titulo
	
    static constraints = {
		titulo(nullable:true, size:1..1024)
    }
}
