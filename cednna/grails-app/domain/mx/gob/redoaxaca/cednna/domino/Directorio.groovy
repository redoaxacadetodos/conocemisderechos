package mx.gob.redoaxaca.cednna.domino

class Directorio {

   	String nombre
	String cargo
	String dependencia
	String correo
	String telefono
	String website

	
    static constraints = {
    }

    static mapping = {
		table('cat_directorio')
		id(column: 'dir_id')

		nombre(column: 'dir_nombre')
		cargo(column: 'dir_cargo')
		dependencia(column: 'dir_dependencia')
		correo(column: 'dir_correo')
		telefono(column: 'dir_telefono')
		website(column: 'dir_website')
		
		version(false)
	}
}
