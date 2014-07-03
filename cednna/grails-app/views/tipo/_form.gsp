<%@ page import="mx.gob.redoaxaca.cednna.domino.Tipo" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: tipoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="tipo.descripcion.label" default="Descripción" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${tipoInstance?.descripcion}" class="uk-form-width-large"/>
	</div>
</div>

