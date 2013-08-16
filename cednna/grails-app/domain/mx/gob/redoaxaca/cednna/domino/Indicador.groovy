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
	
	
//	Region         region
//	Localidad      localidad
//	Estado         estado
//	Municipio      municipio
	
	Division       division
	
	boolean        publico
	
	static hasMany = [variables:DVariable]


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
				division(blank:true, nullable:true)
				
//				region(blank:false, nullable:false) 
//			    localidad(blank:false, nullable:false) 
//				
//				estado(blank:false, nullable:false) 
//				municipio(blank:false, nullable:false) 
    }

  static mapping = {
	table('idn_indicador')
	id(column: 'idn_id')


	publico(column:'idn_publico')
	fecha(column:'idn_fecha')
    fechaActualizacion(column:'idn_fecha_actualizacion')
	dependencia(column:'idn_cdp_id')


	frecuencia(column:'idn_frecuencia')

	formula(column:'idn_ctf_id')
	
	sentido(column:'idn_sentido')
	nombre(column:'idn_nombre')
	objetivo(column:'idn_objetivo')
	mediosVerificacion(column:'idn_medios_verificacion')
	comentarios(column:'idn_comentarios')
	nombreResponsable(column:'idn_nombre_responsable')
	mailResponsable(column:'idn_mail_responsable')
	areaResponsable(column:'idn_area_responsable')
	ejecutora(column:'idn_cue_id')
		

	version(false)
  }    
}
