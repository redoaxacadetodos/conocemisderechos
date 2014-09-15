package mx.gob.redoaxaca.cednna.domino

class Nivel {
	
	String nivel
	int tipo

    static constraints = {
    }
	
	static mapping = {
		table 'cat_nivel'
		nivel column:'niv_nivel'
		tipo column:'niv_tipo'
		version false
	}
}
