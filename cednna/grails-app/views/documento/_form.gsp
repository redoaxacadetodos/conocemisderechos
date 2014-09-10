<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'clave', 'error')} required uk-form-row">
	<label for="tipo" class="uk-form-label">
		<g:message code="documento.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:field name="tipo" type="number" value="${documentoInstance.tipo}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'titulo', 'error')} required uk-form-row">
	<label for="titulo" class="uk-form-label">
		<g:message code="documento.titulo.label" default="Titulo" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:textField name="titulo" value="${documentoInstance?.titulo}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'url', 'error')} required uk-form-row">
	<label for="url" class="uk-form-label">
		<g:message code="documento.url.label" default="Documento" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:field name="url" type="file" value="${documentoInstance?.url}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'nivel', 'error')} required uk-form-row">
	<label for="nivel" class="uk-form-label">
		<g:message code="documento.nivel.label" default="Nivel" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select name="nivel" from="${[[k:1, v:'Internacional'], [k:2, v:'Federal'], [k:3, v:'Estatal']] }" 
			optionKey="k" optionValue="v"/>
	</div>
</div>


