<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadEjecutora" %>



<div class="fieldcontain ${hasErrors(bean: unidadEjecutoraInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="unidadEjecutora.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${unidadEjecutoraInstance?.descripcion}" required="" class="uk-form-width-large"/>
	</div>
</div>

