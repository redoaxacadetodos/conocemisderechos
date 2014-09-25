package mx.gob.redoaxaca.cednna.domino

class Nivel {
	
	String nivel
//	int tipo
	
	static belongsTo = [tipoNivel:TipoEje]

    static constraints = {
		tipoNivel(nullable:true)
    }
	
	static mapping = {
		table 'cat_nivel'
		nivel column:'niv_nivel'
		version false
	}
}
