<br>
<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Tipo de categoria" />

	</label>
	<g:select id="tipo_${con}" name="tipo_${con}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id" optionValue="descripcion" class="chosen-select" />
</div>
<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Categoria" />

	</label>
	<div id="divTipo_${con}" class="fieldcontain">
			<g:select id="categoria_${con}" name="categoria_${con}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" optionValue="descripcion" class="chosen-select"  />
	
	</div>
	

</div>