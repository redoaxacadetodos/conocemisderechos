package mx.gob.redoaxaca.cednna.domino

class DVariable {

	

	String     descripcion
	Estado     estado
	Municipio  municipio
	Region     region
	Localidad  localidad
    int        edadInicial
	int        edadFinal
	
	
    static constraints = {
		
	
		descripcion(blank:false, nullable:false, size:1..500)
		estado(blank:true, nullable:true)
		municipio(blank:true, nullable:true)
		localidad(blank:true, nullable:true)
		region(blank:true, nullable:true)
		edadInicial(blank:true, nullable:true)
		edadFinal(blank:true, nullable:true)
		
    }
}
