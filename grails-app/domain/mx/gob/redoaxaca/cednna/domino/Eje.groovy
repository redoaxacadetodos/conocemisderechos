package mx.gob.redoaxaca.cednna.domino

class Eje {

	String descripcion
	String titulo
	Integer status
	Integer orden
//	Integer tipo
	
	static belongsTo = [tipoEje:TipoEje]
	
    static constraints = {
		titulo(nullable:true, size:1..1024)
		tipoEje(nullable:true)
    }
}
