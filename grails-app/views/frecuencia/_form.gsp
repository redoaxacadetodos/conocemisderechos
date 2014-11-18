<%@ page import="mx.gob.redoaxaca.cednna.domino.Frecuencia" %>

<div class="fieldcontain ${hasErrors(bean: frecuenciaInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="frecuencia.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${frecuenciaInstance?.descripcion}" required="" class="uk-form-width-large"/>
	</div>
</div>