package mx.gob.redoaxaca.cednna.domino

class Resultado {

	
	int    anio
	double resultado

		static belongsTo = [indicador:Indicador]

	
	
    static constraints = {
    }

      static mapping = {
	table('cat_resultado')
	id(column: 'crr_id')


	
	anio(column:'crr_anio')
	resultado(column:'crr_resultado')
	indicador(column:'crr_idn_id')

	version(false)
  }    
}
