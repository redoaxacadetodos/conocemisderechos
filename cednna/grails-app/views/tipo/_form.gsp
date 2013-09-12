<%@ page import="mx.gob.redoaxaca.cednna.domino.Tipo" %>



<div class="fieldcontain ${hasErrors(bean: tipoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="tipo.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${tipoInstance?.descripcion}"/>
</div>

