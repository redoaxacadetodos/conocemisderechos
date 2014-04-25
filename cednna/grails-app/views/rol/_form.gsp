<%@ page import="mx.gob.redoaxaca.cednna.seguridad.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'authority', 'error')} required">
	<label for="authority">
		<g:message code="mx.gob.redoaxaca.rol.label" default="Rol" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="authority" required="" value="${rolInstance?.authority}"/>
</div>

