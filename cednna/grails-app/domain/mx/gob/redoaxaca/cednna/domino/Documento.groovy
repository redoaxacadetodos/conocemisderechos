package mx.gob.redoaxaca.cednna.domino

class Documento {
	
	String titulo
	String url
	int tipo
	Nivel nivel

    static constraints = {
		
    }
	
	static mapping = {
		table 'cat_documento'
		id column:'doc_id'
		titulo column:'doc_titulo'
		url column:'doc_url'
		tipo column:'doc_tipo'
		nivel column:'doc_nivel'
		version false
	}
}
