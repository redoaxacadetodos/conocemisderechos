<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>

<script>
$(document).ready(function() {
	var config = {
		'.chosen-select' : {},
		'.chosen-select-deselect' : {
			allow_single_deselect : true
		},
		'.chosen-select-no-single' : {
			disable_search_threshold : 10
		},
		'.chosen-select-no-results' : {
			no_results_text : 'Oops, nothing found!'
		},
		'.chosen-select-width' : {
			width : "95%"
		}
	}
	for ( var selector in config) {
		$(selector).chosen(config[selector]);
	}

	
});
</script>

<div class="fieldcontain required uk-form-row">
	<label for="indicador" class="uk-form-label">
		<g:message code="frecuenciaIndicador.indicador.label" default="Indicador" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select id="indicador" name="indicador.id" from="${admin==true?mx.gob.redoaxaca.cednna.domino.Indicador.getAll():mx.gob.redoaxaca.cednna.domino.Indicador.findAllByDependencia(dependencia)}"
			onchange="${remoteFunction(action: 'actualizarVariables',
                       update: 'divVariables',
                       params: '\'idIndicador=\' + this.value')}" 
			optionKey="id" optionValue="nombre" required="" class="many-to-one chosen-select"
			noSelection="[null:'-Seleccione una opción-']"/>
	</div>
</div>
<br>

<div id="divVariables"></div>
