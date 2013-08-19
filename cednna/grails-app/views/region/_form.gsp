<%@ page import="mx.gob.redoaxaca.cednna.domino.Region" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: regionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="region.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${regionInstance?.descripcion}"/>
</div>

