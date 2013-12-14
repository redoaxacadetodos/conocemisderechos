<%@ page import="mx.gob.redoaxaca.cednna.seguridad.Usuario" %>



<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'username', 'error')} required">
	<label for="username">
		<g:message code="usuario.username.label" default="Username" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="username" required="" value="${usuarioInstance?.username}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} required">
	<label for="password">
		<g:message code="usuario.password.label" default="Password" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="password" required="" value="${usuarioInstance?.password}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'dependencia', 'error')} ">
	<label for="dependencia">
		<g:message code="usuario.dependencia.label" default="Dependencia" />
		
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" value="${usuarioInstance?.dependencia?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'accountExpired', 'error')} ">
	<label for="accountExpired">
		<g:message code="usuario.accountExpired.label" default="Account Expired" />
		
	</label>
	<g:checkBox name="accountExpired" value="${usuarioInstance?.accountExpired}" />
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'accountLocked', 'error')} ">
	<label for="accountLocked">
		<g:message code="usuario.accountLocked.label" default="Account Locked" />
		
	</label>
	<g:checkBox name="accountLocked" value="${usuarioInstance?.accountLocked}" />
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'enabled', 'error')} ">
	<label for="enabled">
		<g:message code="usuario.enabled.label" default="Enabled" />
		
	</label>
	<g:checkBox name="enabled" value="${usuarioInstance?.enabled}" />
</div>

<div class="fieldcontain ${hasErrors(bean: usuarioInstance, field: 'passwordExpired', 'error')} ">
	<label for="passwordExpired">
		<g:message code="usuario.passwordExpired.label" default="Password Expired" />
		
	</label>
	<g:checkBox name="passwordExpired" value="${usuarioInstance?.passwordExpired}" />
</div>

<div class="fieldset fieldset--demo js__fieldset" ><div class="fieldset__wrapper">
	<label for="concesionario">
		<h3><g:message code="estacion.concesionario.label" default="Roles" /></h3>
	
	</label>
	
		<g:each in="${com.indesti.ace.security.Rol.list()}" status="i" var="rolInstance">
						
										<g:set var="bandera" value="${0}" ></g:set>
										
											<g:if test="${usuarioInstance.id}">
											<g:each in="${com.indesti.ace.security.UsuarioRol.findAllByUsuario(usuarioInstance)}" var="role">
											
												<g:if test="${rolInstance.id==role.rol.id}">
													<g:set var="bandera" value="${1}" ></g:set>
												</g:if>
												
											</g:each>
											
											</g:if>
									
											<div class="fieldcontain ${hasErrors(bean: estacionInstance, field: 'concesionario', 'error')} required">
												 	
													 	<g:if test="${bandera!=1}">
													 		<g:if test="${rolInstance.authority=='ROLE_USUARIO'}">
														 	<input type="checkbox"  name="rol_${rolInstance?.id}" checked /> 
														 	</g:if><g:else>
														 	<input type="checkbox" name="rol_${rolInstance?.id}"  /> 
														 	</g:else>
														 </g:if>	
														<g:else>
															
															<input type="checkbox" name="rol_${rolInstance?.id}"  checked/> 
															
														</g:else>	
												 		
												 
													<span>${rolInstance.authority}</span>
					
												</div>		
									
									
								</g:each>
</div>
</div>	