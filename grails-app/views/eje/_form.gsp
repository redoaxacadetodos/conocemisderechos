<%@ page import="mx.gob.redoaxaca.cednna.domino.Eje" %>



<div class="fieldcontain ${hasErrors(bean: ejeInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="eje.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${ejeInstance?.descripcion}" required="" class="uk-form-width-large"/>
	</div>
</div>



