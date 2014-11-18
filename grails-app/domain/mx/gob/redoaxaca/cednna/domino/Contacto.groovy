package mx.gob.redoaxaca.cednna.domino

class Contacto {
	
	String titulo
	String grado
	String nombre
	String paterno
	String materno
	String descripcion
	List email
	String telefono
	String domicilio
	
	static hasMany = [email:Email]
	
	static mapping = {
		version(false)
	}
	
    static constraints = {
		domicilio(nullable:true)
		telefono(nullable:true)
    }
}
