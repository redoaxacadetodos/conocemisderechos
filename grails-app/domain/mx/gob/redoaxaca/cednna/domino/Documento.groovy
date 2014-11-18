package mx.gob.redoaxaca.cednna.domino

class Documento {
	
	String titulo
	String url
	Nivel nivel
	
	static belongsTo = [tipoDocumento:TipoEje]

    static constraints = {
		tipoDocumento nullable:true
    }
	
	static mapping = {
		table 'cat_documento'
		id column:'doc_id'
		titulo column:'doc_titulo'
		url column:'doc_url'
		nivel column:'doc_nivel'
		version false
	}
}
