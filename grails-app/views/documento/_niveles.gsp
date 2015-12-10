<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'nivel', 'error')} required uk-form-row">
	<label for="nivel" class="uk-form-label">
		<g:message code="documento.nivel.label" default="Nivel" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select name="nivel.id" from="${niveles }" 
			optionKey="id" optionValue="nivel" value="${nivelSeleccionado }"/>
	</div>
</div>
