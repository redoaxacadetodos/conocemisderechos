package mx.gob.redoaxaca.cednna.domino

import sun.tools.tree.ThisExpression;

class CatOrigenDatos {

	String      clave
	String      descripcion
	Dependencia dependencia
	
	static transients = ['datalleCombo']
	
	static mapping = {
		table('cat_origen_datos')
		id(column: 'cod_id')
		clave(column: 'cod_clave')
		descripcion(column: 'cod_descripcion')
		dependencia(column: 'cod_dependencia_id')

	
		
		version(false)
	}
	
	
	
	String getDetalleCombo(){
	 
		return "${clave}"+"-"+"${descripcion}"	
		 }
	
    static constraints = {
		
		dependencia(nullable:true)
		
    }
}
