package mx.gob.redoaxaca.cednna.domino

class Eje {

	String descripcion
	String titulo
	Integer status
	Integer orden
	String color
//	Integer tipo
	
	static belongsTo = [tipoEje:TipoEje]
	
    static constraints = {
		titulo(nullable:true, size:1..1024)
		color(blank:true, nullable:true)
		tipoEje(nullable:true)
    }
}
