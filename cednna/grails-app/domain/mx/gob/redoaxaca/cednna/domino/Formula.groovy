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

      static mapping = {
		table('cat_formula')
		id(column: 'ctf_id')

		nombre(column: 'ctf_nombre')
		descripcion(column: 'ctf_descripcion')
		sentencia(column: 'ctf_sentencia')
		numVariables(column: 'ctf_num_variables')
		variables(column: 'ctf_variables')	

		version(false)
	}
}
