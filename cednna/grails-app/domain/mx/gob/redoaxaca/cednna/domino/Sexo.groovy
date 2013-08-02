package mx.gob.redoaxaca.cednna.domino

class Sexo {
	
	String descripcion

    static constraints = {
    }

    static mapping = {
	table('cat_sexo')
	id(column: 'csx_id')


	
	descripcion(column:'csx_descripcion')
		

	version(false)
  }    
}
