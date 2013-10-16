<%@ page import="mx.gob.redoaxaca.cednna.domino.Eje" %>



<div class="fieldcontain ${hasErrors(bean: ejeInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="eje.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${ejeInstance?.descripcion}"/>
</div>



