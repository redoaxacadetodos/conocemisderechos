<br>
<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Tipo de categoria" />

	</label>
	<g:select id="tipo" name="tipo_${var}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id" optionValue="descripcion"  class="many-to-one"/>
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Categoria" />

	</label>
	<g:select id="categoria" name="categoria_${con}_${var}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" optionValue="descripcion"   class="many-to-one"/>

</div>