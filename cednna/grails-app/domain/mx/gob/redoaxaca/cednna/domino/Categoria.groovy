package mx.gob.redoaxaca.cednna.domino

class Categoria {

	Tipo    tipo
	String  descripcion
	

	static belongsTo = [dvariable:DVariable, variable:Variable]

    static constraints = {
    }

    static mapping = {
		table('cat_categoria')
		id(column: 'cct_id')

		descripcion(column: 'cct_descripcion')
		dvariable(column:'cct_cdv_id')
		variable(column:'cct_cvv_id')
		tipo(column: 'cct_ctt_id')
		
		version(false)
	}
}
