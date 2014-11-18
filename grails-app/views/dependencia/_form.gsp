<%@ page import="mx.gob.redoaxaca.cednna.domino.Dependencia" %>

<div class="fieldcontain ${hasErrors(bean: dependenciaInstance, field: 'clave', 'error')} uk-form-row">
	<label for="clave" class="uk-form-label">
		<g:message code="dependencia.clave.label" default="Clave" />
	</label>
	<div class="uk-form-controls">
	<g:textField name="clave" value="${dependenciaInstance?.clave}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: dependenciaInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="dependencia.descripcion.label" default="Descripci&oacute;n" />
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${dependenciaInstance?.descripcion}"/>
	</div>
</div>