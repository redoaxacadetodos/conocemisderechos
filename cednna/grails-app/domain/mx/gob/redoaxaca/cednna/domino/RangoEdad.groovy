package mx.gob.redoaxaca.cednna.domino

class RangoEdad {
	
	String descripcion
	int    minimo
	int    maximo
	

    static constraints = {
    }

      static mapping = {
	table('cat_rango_edad')
	id(column: 'cre_id')


	
	descripcion(column:'cre_descripcion')
	minimo(column:'cre_minimo')
	maximo(column:'cre_maximo')

	version(false)
  }    
}
