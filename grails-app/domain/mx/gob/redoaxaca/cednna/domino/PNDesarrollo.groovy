package mx.gob.redoaxaca.cednna.domino

class PNDesarrollo {

	String descripcion
	
   
	static mapping = {
		table('cat_pn_desarrollo')
		id(column: 'cnpd_id')
	
	
		
		descripcion(column:'cnpd_descripcion')
			
	
		version(false)
	  }
}
