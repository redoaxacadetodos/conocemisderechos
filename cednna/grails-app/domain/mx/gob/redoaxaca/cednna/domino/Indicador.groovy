package mx.gob.redoaxaca.cednna.domino

class Indicador {
	
	
	
	String          nombre
	String          objetivo	
	Dependencia     dependencia
	UnidadEjecutora ejecutora
	Frecuencia      frecuencia
	Formula 	    formula
	String          mediosVerificacion
	String          comentarios
	
	Sentido         sentido
	Date 		    fecha= new Date()
	
	String          nombreResponsable
	String          mailResponsable
	String          areaResponsable
	Date		    fechaActualizacion
	
	
	Region         region
	Localidad      localidad
	Estado         estado
	Municipio      municipio
	

	
	boolean        publico
	
	static hasMany = [resultados:Resultado,variables:DVariable]


    static constraints = {
		
		
				nombreResponsable(blank:false, nullable:false, size:1..500)
				fechaActualizacion(blank:false, nullable:false)
				areaResponsable(blank:false, nullable:false, size:1..500)
				mailResponsable(blank:false, nullable:false, size:1..1024,email:true)
		
		
		      
				sentido(blank:false, nullable:false)
			    nombre(blank:false, nullable:false)
				objetivo(blank:false, nullable:false)
				dependencia(blank:false, nullable:false)
				ejecutora(blank:false, nullable:false)
				frecuencia(blank:false, nullable:false)
				formula(blank:false, nullable:false)
				
				region(blank:false, nullable:false) 
			    localidad(blank:false, nullable:false) 
				
				estado(blank:false, nullable:false) 
				municipio(blank:false, nullable:false) 
    }
}
