package mx.gob.redoaxaca.cednna.domino

class Tema {

	PNDesarrollo eje
	String descripcion
	
    static constraints = {
		eje nullable:true
    }
	
	static mapping = {
		eje column: "ped_id"
	}
}
