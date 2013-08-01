package mx.gob.redoaxaca.cednna.domino

class DVariable {

	


	Estado     estado
	Municipio  municipio
	Region     region
	Localidad  localidad
	
	static hasMany = [categorias:Categoria]

	
	
    static constraints = {
		
	
		
		estado(blank:true, nullable:true)
		municipio(blank:true, nullable:true)
		localidad(blank:true, nullable:true)
		region(blank:true, nullable:true)
		
		
    }
}
