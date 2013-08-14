<br>
<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
<div class="uk-grid">
<div class="uk-width-1-2">
	<label for="localidad" class="uk-form-label">
		<g:message code="indicador.localidad.label" default="Tipo de categoria" />

	</label>
	<g:select id="tipo_${con}_${var}" name="tipo_${con}_${var}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id" optionValue="descripcion" class="chosen-select" />
</div>
<div class="uk-width-1-2">
	<label for="localidad" class="uk-form-label">
		<g:message code="indicador.localidad.label" default="Categoria" />

	</label>
	<div id="divTipo_${con}_${var}">
			<g:select id="categoria_${con}_${var}" name="categoria_${con}_${var}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" optionValue="descripcion" class="chosen-select"  />
	
	</div>
</div>
	</div>	

</div>