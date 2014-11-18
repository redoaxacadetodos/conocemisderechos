<%@ page import="mx.gob.redoaxaca.cednna.domino.ObjetivoMilenio" %>



<div class="fieldcontain ${hasErrors(bean: objetivoMilenioInstance, field: 'clave', 'error')} uk-form-row">
	<label for="clave" class="uk-form-label">
		<g:message code="objetivoMilenio.clave.label" default="Clave" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="clave" value="${objetivoMilenioInstance?.clave}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: objetivoMilenioInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="objetivoMilenio.descripcion.label" default="Descripción" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="descripcion" value="${objetivoMilenioInstance?.descripcion}" class="uk-form-width-large"/>
	</div>
</div>

