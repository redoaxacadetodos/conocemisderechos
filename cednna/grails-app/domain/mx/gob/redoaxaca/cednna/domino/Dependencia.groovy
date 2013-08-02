package mx.gob.redoaxaca.cednna.domino

class Dependencia {

	String descripcion
	String clave
	
	
    static constraints = {
    }

    static mapping = {
		table('cat_dependencia')
		id(column: 'cdp_id')

		descripcion(column: 'cdp_descripcion')
		clave(column: 'cdp_clave')
		
		version(false)
	}
}
