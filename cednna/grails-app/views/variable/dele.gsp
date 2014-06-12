<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>

<link rel="stylesheet" href="${resource(dir: 'css', file: 'selectize.css')}" type="text/css">
<link rel="stylesheet" href="${resource(dir: 'css', file: 'selectize.default.css')}" type="text/css">
<g:javascript src="selectize.js" />

<script type="text/javascript" defer="defer" >




$(function(){

		$(document).ready(function() {


			 var config = {
			      '.chosen-select'           : {},
			      '.chosen-select-deselect'  : {allow_single_deselect:true},
			      '.chosen-select-no-single' : {disable_search_threshold:10},
			      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			      '.chosen-select-width'     : {width:"95%"}
		    }
			for (var selector in config) {
				$(selector).chosen(config[selector]);
			}
			

			$("#mostrarPeriodo").click(function(){
				$('#periodo').removeAttr('disabled');
				$('#divPeriodo').show();
				$('#divAnio').hide();
				$('#tipoPeriodo').val('true');
			});

			$("#ocultarPeriodo").click(function(){
				$('#divPeriodo').hide();
				$('#divAnio').show();
				$('#tipoPeriodo').val('false');
			});
			
			$('#divPeriodo').hide();

		});

		
});




</script>
</head>
<body>
<a href="#create-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<g:form controller="variable" method="post" >

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li><g:link class="list" action="list">Datos estadísticos</g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Agregar datos</g:link></li>
			
	
</nav>

	<h1  class="uk-article-title">Eliminar datos estadísticos</h1>
	
	
	<h3  class="uk-article-content">Datos para ubicar variable</h3>



							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
								<label class="uk-form-label" for="region">
									<g:message code="variable.region.label" default="Descripción" />
									
								</label>
								<div class="uk-form-controls">
								<g:select id="origenDatos" name="origenDatos" from="${mx.gob.redoaxaca.cednna.domino.CatOrigenDatos.list()}" optionKey="clave" optionValue="detalleCombo"  class="chosen-select" style="width:800px;"  value="${variableInstance?.clave}"  noSelection="['null': '- Ninguna descripcion-']"/>
								</div>
							</div>

		<div id="divAnio" class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
			<label class="uk-form-label" for="anio"> <g:message
					code="variable.anio.label" default="Año" /> <span
				class="required-indicator">*</span>
			</label>
			<div class="uk-form-controls">
				<g:select name="anio" from="${2004..2022}" class="chosen-select" />
				<input type="button" id="mostrarPeriodo" value="Mostrar periodos" class="uk-button">
			</div>
		</div>

<g:hiddenField name="tipoPeriodo" value="false"/>

<div id="divPeriodo" class="fieldcontain uk-form-row ">
<br>
	<label class="uk-form-label"for="periodo">
		<g:message code="indicador.periodo.label" default="Ciclo escolar " />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="periodo" name="periodo" from="${mx.gob.redoaxaca.cednna.domino.Periodo.list().sort{it.descripcion}}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.periodo?.id}" class="many-to-one chosen-select" style="width:350px;" />
	<input type="button" id="ocultarPeriodo" value="Ocultar periodos" class="uk-button">
</div>

	

<hr class="uk-article-divider" class="uk-button">
	
	<g:actionSubmit class="uk-button uk-icon-download" action="borrarOrigen" value="Eliminar origen de datos" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" class="uk-button"/>
			


	
	

	
<hr class="uk-article-divider">




</g:form>

	
</body>

</html>

