<%@ page import="mx.gob.redoaxaca.cednna.domino.Region" %>



<div class="fieldcontain ${hasErrors(bean: regionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="region.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${regionInstance?.descripcion}"/>
</div>

