<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadEjecutora" %>



<div class="fieldcontain ${hasErrors(bean: unidadEjecutoraInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="unidadEjecutora.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<g:textField name="descripcion" value="${unidadEjecutoraInstance?.descripcion}"/>
</div>

