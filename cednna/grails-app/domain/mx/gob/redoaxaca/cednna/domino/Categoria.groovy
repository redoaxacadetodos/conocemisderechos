package mx.gob.redoaxaca.cednna.domino

class Categoria {

	Tipo    tipo
	String   clave
	String  descripcion
	

	static belongsTo = Variable//, DVariable
	static hasMany = [variables:Variable]//, dvariables: DVariable]

    static constraints = {
		clave(nullable:true,blank:true)
    }

    static mapping = {
		table('cat_categoria')
		id(column: 'cct_id')
		clave(column: 'cct_clave')
		descripcion(column: 'cct_descripcion')

 	    variables (column: 'cvc_cct_id', joinTable: 'cat_variable_categoria')
 	  //  dvariables (column: 'cdc_cct_id', joinTable: 'cat_dvariable_categoria')

		tipo(column: 'cct_ctt_id')
		
		version(false)
	}
}
