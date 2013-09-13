<%@ page import="mx.gob.redoaxaca.cednna.domino.Division" %>



<div class="fieldcontain ${hasErrors(bean: divisionInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="division.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${divisionInstance?.descripcion}"/>
</div>

