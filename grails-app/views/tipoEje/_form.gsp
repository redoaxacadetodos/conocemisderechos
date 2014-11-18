<%@ page import="mx.gob.redoaxaca.cednna.domino.TipoEje" %>

<div class="fieldcontain ${hasErrors(bean: tipoEjeInstance, field: 'tipo', 'error')} uk-form-row">
	<label for="tipo" class="uk-form-label">
		<g:message code="tipoEje.tipo.label" default="Tipo" />
	</label>
	<div class="uk-form-controls">
		<g:textField name="tipo" value="${tipoEjeInstance?.tipo}"/>
	</div>
</div>

