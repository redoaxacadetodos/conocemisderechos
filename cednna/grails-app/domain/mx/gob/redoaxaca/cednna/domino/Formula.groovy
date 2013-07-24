package mx.gob.redoaxaca.cednna.domino

class Formula {
	
	String nombre
	String descripcion
	String sentencia
	int    numVariables
	String variables
	
	

    static constraints = {
		
		
	 nombre(blank:false, nullable:false)
	 descripcion(blank:false, nullable:false)
	 sentencia(blank:false, nullable:false)
	 variables(blank:true, nullable:true)
		
    }
}
