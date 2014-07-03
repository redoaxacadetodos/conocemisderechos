<%@ page import="mx.gob.redoaxaca.cednna.seguridad.Usuario" %>

<script>
	$(function() {
		$('#rol').selectize({
			maxItems: 15
		});
	});
</script>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'username', 'error')} required">
	<label class="uk-form-label" for="username">
		<g:message code="mx.gob.redoaxaca.usuario.label" default="Usuario" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:textField name="username" required="" value="${usuarioInstance?.username}"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'password', 'error')} required">
	<label class="uk-form-label" for="password">
		<g:message code="mx.gob.redoaxaca.usuario.contrasena.label" default="Contraseña" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<input type="password" name="password" required="required" value="${usuarioInstance?.password}"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'dependencia', 'error')} ">
	<label class="uk-form-label" for="dependencia">
		<g:message code="usuario.dependencia.label" default="Dependencia" />
		
	</label>
	<div class="uk-form-controls">
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionValue="descripcion" optionKey="id" value="${usuarioInstance?.dependencia?.id}" class="many-to-one" noSelection="['null': 'Seleccione una dependencia']" required=""/>
</div>
</div>

<div class="fieldcontain uk-form-row required">
	<label class="uk-form-label" for="rol">
		<g:message code="usuarioRol.rol.label" default="Rol" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select id="rol" name="rol" from="${mx.gob.redoaxaca.cednna.seguridad.Rol.list()}" optionValue="authority" optionKey="id" required="" value="${usuarioRolInstance*.rol?.id}" class="many-to-one"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'accountExpired', 'error')} ">
	<label class="uk-form-label" for="accountExpired">
		<g:message code="mx.gob.redoaxaca.usuario.caducada.label" default="Cuenta caducada" />
		
	</label>
	<div class="uk-form-controls">
	<g:checkBox name="accountExpired" value="${usuarioInstance?.accountExpired}" />
</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'accountLocked', 'error')} ">
	<label class="uk-form-label" for="accountLocked">
		<g:message code="mx.gob.redoaxaca.usuario.bloqueada.label" default="Cuenta bloqueada" />
		
	</label>
	<div class="uk-form-controls">
	<g:checkBox name="accountLocked" value="${usuarioInstance?.accountLocked}" />
</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'enabled', 'error')} ">
	<label class="uk-form-label" for="enabled">
		<g:message code="mx.gob.redoaxaca.usuario.habilitado.label" default="Enabled" />
		
	</label>
	<div class="uk-form-controls">
	<g:checkBox name="enabled" value="${usuarioInstance?.enabled}" />
</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: usuarioInstance, field: 'passwordExpired', 'error')} ">
	<label class="uk-form-label" for="passwordExpired">
		<g:message code="mx.gob.redoaxaca.usuario.contrasenaCaducada.label" default="Contraseña caducada" />
		
	</label>
	<div class="uk-form-controls">
	<g:checkBox name="passwordExpired" value="${usuarioInstance?.passwordExpired}" />
</div>
</div>

