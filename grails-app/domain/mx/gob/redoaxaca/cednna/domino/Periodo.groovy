package mx.gob.redoaxaca.cednna.domino

class Periodo {

	String descripcion
	int anioInicial
	int anioFinal
	Integer categoria
	
    static constraints = {
		categoria (nullabe:true)
    }
}
