



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'municipio', 'error')} required">
	<label for="municipio">
		<g:message code="indicador.municipio.label" default="Variable" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="variables" name="varaibles" from="${var}" optionKey="id" optionValue="descripcion" required=""    class="many-to-one"/>
</div>
