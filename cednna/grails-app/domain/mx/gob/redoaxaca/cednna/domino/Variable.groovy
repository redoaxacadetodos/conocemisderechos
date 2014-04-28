package mx.gob.redoaxaca.cednna.domino

class Variable {

	
	String      clave
	String      descripcion
	Estado      estado
	Region      region
	Municipio   municipio
	Localidad   localidad
	int         poblacionTotal
	int         hombres
	int         mujeres
	int         anio
	Periodo 	periodo
	Dependencia dependencia
	
	static hasMany = [categorias:Categoria]
		
    static constraints = {
		
		periodo nullable:true
		clave(blank:false, nullable:false)
		descripcion(blank:false, nullable:false, size:1..500)
		estado(blank:true, nullable:true)
		localidad(blank:true, nullable:true)
		municipio(blank:true, nullable:true)
		poblacionTotal(blank:false, nullable:false)
		hombres(blank:false, nullable:false)
		mujeres(blank:false, nullable:false)
		region(blank:true, nullable:true)
		anio(blank:false, nullable:false)
		dependencia(blank:true, nullable:true)
		
    }

    static mapping = {
	table('cat_variable')
	id(column: 'cvv_id')
	dependencia(column:'cvv_dependencia')
	
	clave(column:'cvv_clave')
	descripcion(column:'cvv_descripcion')
	estado(column:'cvv_estado')
	region(column:'cvv_region')
	municipio(column:'cvv_municipio')
	localidad(column:'cvv_localidad')
	poblacionTotal(column:'cvv_poblacion_total')
	hombres(column:'cvv_hombres')
	mujeres(column:'cvv_mujeres')
	anio(column:'cvv_anio')
	
	periodo(column:'cvv_ped_id')

 	categorias (column: 'cvc_cvv_id', joinTable: 'cat_variable_categoria')

	version(false)
  }    
}
