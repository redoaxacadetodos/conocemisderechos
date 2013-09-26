package mx.gob.redoaxaca.cednna.domino

class Division {

	
		
		String descripcion
		
		static belongsTo = [eje:Eje]

	
	
    static constraints = {
		
		
		eje(blank:true,nullable:true)
    }
}
