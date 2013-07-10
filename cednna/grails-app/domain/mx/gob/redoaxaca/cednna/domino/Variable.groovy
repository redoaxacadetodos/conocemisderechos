package mx.gob.redoaxaca.cednna.domino

class Variable {

	
	String     clave
	String     descripcion
	Estado     estado
	Municipio  municipio 
	int        poblacionTotal
	int        hombres
	int        mujeres
	Region     region
	Localidad  localidad
	int        anio
	RangoEdad  rango
	
	
		
    static constraints = {
		
		
		        clave(blank:false, nullable:false)
		        descripcion(blank:false, nullable:false, size:1..500)
		        estado(blank:true, nullable:true)
		        municipio(blank:true, nullable:true)
		        poblacionTotal(blank:false, nullable:false)
		        hombres(blank:false, nullable:false)
		        mujeres(blank:false, nullable:false)
		        region(blank:true, nullable:true)
		        anio(blank:false, nullable:false)
		        rango(blank:true, nullable:true)
		
    }
}
