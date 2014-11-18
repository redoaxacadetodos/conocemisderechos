<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadMedida" %>



<div class="fieldcontain ${hasErrors(bean: unidadMedidaInstance, field: 'abreviatura', 'error')} uk-form-row">
	<label for="abreviatura" class="uk-form-label">
		<g:message code="unidadMedida.abreviatura.label" default="Abreviatura" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="abreviatura" value="${unidadMedidaInstance?.abreviatura}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: unidadMedidaInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="unidadMedida.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${unidadMedidaInstance?.descripcion}"/>
	</div>
</div>

