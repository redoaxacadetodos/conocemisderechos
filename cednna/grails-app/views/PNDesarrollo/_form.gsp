<%@ page import="mx.gob.redoaxaca.cednna.domino.PNDesarrollo" %>



<div class="fieldcontain ${hasErrors(bean: PNDesarrolloInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="PNDesarrollo.descripcion.label" default="Descripción" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${PNDesarrolloInstance?.descripcion}" class="uk-form-width-large" required=""/>
	</div>
</div>

