package mx.gob.redoaxaca.cednna.domino

class FrecuenciaIndicador {
	
	DVariable variable
	Frecuencia frecuencia
	int ano
	Date fechaActualizacion

    static constraints = {
    }
	
	static mapping = {
		table('idn_frecuenciaIndicador')
		variable(column:'fre_variable')
		frecuencia(column:'fre_fecuencia')
		ano(column:'fre_ano')
		fechaActualizacion(column:'fre_fechaActualizacion')
		
		version(false)
	}
}
