<%@ page import="mx.gob.redoaxaca.cednna.seguridad.Rol" %>



<div class="fieldcontain ${hasErrors(bean: rolInstance, field: 'authority', 'error')} required uk-form-row">
	<label for="authority" class="uk-form-label">
		<g:message code="mx.gob.redoaxaca.rol.label" default="Rol" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:textField name="authority" required="" value="${rolInstance?.authority}"/>
	</div>
</div>

