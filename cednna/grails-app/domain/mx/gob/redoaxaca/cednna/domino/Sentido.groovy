package mx.gob.redoaxaca.cednna.domino

class Sentido {
	
	String descripcion
	int    clave

    static constraints = {
		clave unique : true
		descripcion unique : true
    }

    static mapping = {
	table('cat_sentido')
	id(column: 'csn_id')


	
	descripcion(column:'csn_descripcion')
	clave(column:'csn_clave')

	version(false)
  }    
}
