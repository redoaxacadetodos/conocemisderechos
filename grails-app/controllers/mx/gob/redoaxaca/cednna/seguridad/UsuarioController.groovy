package mx.gob.redoaxaca.cednna.seguridad

import grails.converters.JSON
import grails.plugins.springsecurity.Secured

import org.springframework.dao.DataIntegrityViolationException

@Secured(['ROLE_ADMIN'])
class UsuarioController {
	def dataTablesService

    static allowedMethods = [save: "POST", update: "POST", delete: "POST"]

    def index() {
        redirect(action: "list", params: params)
    }

    def list(Integer max) {
        params.max = Math.min(max ?: 10, 100)
        [usuarioInstanceList: Usuario.list(params), usuarioInstanceTotal: Usuario.count()]
    }
	
	def dataTablesList = {
		def query = "from sys_usuario u left join cat_dependencia d on (u.dependencia_id = d.cdp_id)"
		render dataTablesService.datosParaTablaQuery(query,params,
			[
			"u.id as id",
			"u.username as usuario",
			"coalesce(d.cdp_descripcion,'') as dependencia",
			"u.account_expired as accountExpired",
			"u.account_locked as accountLocked",
			"u.enabled as enabled"
			],
			[
			"u.username",
			"coalesce(d.cdp_descripcion,'')",
			"u.account_expired",
			"u.account_locked",
			"u.enabled"
			],
			[
			"id",
			"usuario",
			"dependencia",
			"accountExpired",
			"accountLocked",
			"enabled"
			],1, "text") as JSON
	}
	

    def create() {
        [usuarioInstance: new Usuario(params)]
    }

    def save() {
        def usuarioInstance = new Usuario(params)
		def error = false
		
		usuarioInstance.save(flush: true)
		
		params.rol.each{
			def rol = Rol.get(it.toLong())
			try{
				UsuarioRol.create usuarioInstance, rol, true
			}catch(Exception e){
				error = true
			}
			
		}
		
		if(error){
			render(view: "create", model: [usuarioInstance: usuarioInstance])
			return
		}else{
			flash.message = message(code: 'default.created.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
			redirect(action: "show", id: usuarioInstance.id)
		}
    }

    def show(Long id) {
        def usuarioInstance = Usuario.get(id)
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance]
    }

    def edit(Long id) {
        def usuarioInstance = Usuario.get(id)
		
		def usuarioRol = UsuarioRol.where {
         usuario == Usuario.load(usuarioInstance.id)
		}
		
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        [usuarioInstance: usuarioInstance, usuarioRolInstance:usuarioRol]
    }

    def update(Long id, Long version) {
        def usuarioInstance = Usuario.get(id)
		
		UsuarioRol.removeAll(usuarioInstance)	
		
		params.rol.each{
			def rol = Rol.get(it.toLong())
			try{
				UsuarioRol.create usuarioInstance, rol, true
			}catch(Exception e){
				error = true
			}
		}
		
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        if (version != null) {
            if (usuarioInstance.version > version) {
                usuarioInstance.errors.rejectValue("version", "default.optimistic.locking.failure",
                          [message(code: 'usuario.label', default: 'Usuario')] as Object[],
                          "Another user has updated this Usuario while you were editing")
                render(view: "edit", model: [usuarioInstance: usuarioInstance])
                return
            }
        }

        usuarioInstance.properties = params

        if (!usuarioInstance.save(flush: true)) {
            render(view: "edit", model: [usuarioInstance: usuarioInstance])
            return
        }

        flash.message = message(code: 'default.updated.message', args: [message(code: 'usuario.label', default: 'Usuario'), usuarioInstance.id])
        redirect(action: "show", id: usuarioInstance.id)
    }

    def delete(Long id) {
        def usuarioInstance = Usuario.get(id)
		
        if (!usuarioInstance) {
            flash.message = message(code: 'default.not.found.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
            return
        }

        try {
			UsuarioRol.removeAll usuarioInstance
			usuarioInstance.delete(flush: true)
            flash.message = message(code: 'default.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "list")
        }
        catch (DataIntegrityViolationException e) {
            flash.message = message(code: 'default.not.deleted.message', args: [message(code: 'usuario.label', default: 'Usuario'), id])
            redirect(action: "show", id: id)
        }
    }
}
