package mx.gob.redoaxaca.cednna.domino

class TipoPoblacion {

	String descripcion
	
    static constraints = {
    }

    static mapping = {
	table('cat_tipo_poblacion')
	id(column: 'ctp_id')


	
	descripcion(column:'ctp_descripcion')
		

	version(false)
  }    
}
