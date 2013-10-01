package mx.gob.redoaxaca.cednna.domino

class CatOrigenDatos {

	String clave
	String descripcion
	
	
	static mapping = {
		table('cat_origen_datos')
		id(column: 'cod_id')
		clave(column: 'cod_clave')
		descripcion(column: 'cod_descripcion')

	
		
		version(false)
	}
	
	
	
    static constraints = {
    }
}
