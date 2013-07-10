import mx.gob.redoaxaca.cednna.seguridad.*

class BootStrap {

    def springSecurityService
	def grailsApplication
	def init = { servletContext ->
		development {

//			def userRole = Rol.findByAuthority('ROLE_USER') ?: new Rol(authority: 'ROLE_USER').save(failOnError: true)
//			def adminRole = Rol.findByAuthority('ROLE_ADMIN') ?: new Rol(authority: 'ROLE_ADMIN').save(failOnError: true)
//			
//			def usuario = Usuario.findByUsername('admin') ?: new Usuario(
//					username: 'admin',
//					password:'admin',
//					enabled: true).save(failOnError: true)
//
//		
//			if (!usuario.authorities.contains(userRole)) {
//				UsuarioRol.create usuario, userRole
//			}
//			
//			if (!usuario.authorities.contains(adminRole)) {
//				UsuarioRol.create usuario, adminRole
//			}
//
//			
		

		}
	}

}
