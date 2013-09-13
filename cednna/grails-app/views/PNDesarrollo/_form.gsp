<%@ page import="mx.gob.redoaxaca.cednna.domino.PNDesarrollo" %>



<div class="fieldcontain ${hasErrors(bean: PNDesarrolloInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="PNDesarrollo.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${PNDesarrolloInstance?.descripcion}"/>
</div>

