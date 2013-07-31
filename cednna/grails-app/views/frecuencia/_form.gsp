<%@ page import="mx.gob.redoaxaca.cednna.domino.Frecuencia" %>



<div class="fieldcontain ${hasErrors(bean: frecuenciaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="frecuencia.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${frecuenciaInstance?.descripcion}"/>
</div>

