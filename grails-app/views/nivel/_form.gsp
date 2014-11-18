<%@ page import="mx.gob.redoaxaca.cednna.domino.Nivel" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.TipoEje" %>



<div class="fieldcontain ${hasErrors(bean: nivelInstance, field: 'nivel', 'error')} uk-form-row">
	<label for="nivel" class="uk-form-label">
		<g:message code="nivel.nivel.label" default="Nivel" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="nivel" value="${nivelInstance?.nivel}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: nivelInstance, field: 'tipo', 'error')} required uk-form-row">
	<label for="tipo" class="uk-form-label">
		<g:message code="nivel.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select name="tipoNivel.id" value="${nivelInstance.tipoNivel}" from="${TipoEje.list() }"
			optionKey="id" optionValue="tipo"/>
	</div>
</div>

