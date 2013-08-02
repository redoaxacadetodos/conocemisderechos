package mx.gob.redoaxaca.cednna.domino

class Poblacion {

	
	String descripcion
	String clave
    static constraints = {
    }

      static mapping = {
	table('cat_poblacion')
	id(column: 'cpp_id')


	
	descripcion(column:'cpp_descripcion')
	clave(column:'cpp_clave')

	version(false)
  }    
}
