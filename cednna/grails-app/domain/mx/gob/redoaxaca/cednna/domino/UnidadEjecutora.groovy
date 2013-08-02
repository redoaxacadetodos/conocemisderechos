package mx.gob.redoaxaca.cednna.domino

class UnidadEjecutora {

			String descripcion
	
    static constraints = {
    }

 static mapping = {
	table('cat_unidad_ejecutora')
	id(column: 'cue_id')


	
	descripcion(column:'cue_descripcion')
		

	version(false)
  }    

}
