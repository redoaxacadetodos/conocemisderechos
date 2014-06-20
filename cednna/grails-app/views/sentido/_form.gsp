<%@ page import="mx.gob.redoaxaca.cednna.domino.Sentido" %>



<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'clave', 'error')} required uk-form-row">
	<label for="clave" class="uk-form-label">
		<g:message code="sentido.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:field name="clave" type="number" value="${sentidoInstance.clave}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="sentido.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${sentidoInstance?.descripcion}" class="uk-form-width-medium"/>
	</div>
</div>

