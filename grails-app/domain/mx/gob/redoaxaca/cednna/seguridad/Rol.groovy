package mx.gob.redoaxaca.cednna.seguridad

class Rol {

	String authority

		static mapping = {
        table 'sys_rol'
		cache true
	}

	static constraints = {
		authority blank: false, unique: true
	}
}
