<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadMedida" %>



<div class="fieldcontain ${hasErrors(bean: unidadMedidaInstance, field: 'abreviatura', 'error')} ">
	<label for="abreviatura">
		<g:message code="unidadMedida.abreviatura.label" default="Abreviatura" />
		
	</label>
	<g:textField name="abreviatura" value="${unidadMedidaInstance?.abreviatura}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: unidadMedidaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="unidadMedida.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${unidadMedidaInstance?.descripcion}"/>
</div>

