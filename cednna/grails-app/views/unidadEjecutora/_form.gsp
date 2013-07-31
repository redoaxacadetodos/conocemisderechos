<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadEjecutora" %>



<div class="fieldcontain ${hasErrors(bean: unidadEjecutoraInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="unidadEjecutora.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${unidadEjecutoraInstance?.descripcion}"/>
</div>

