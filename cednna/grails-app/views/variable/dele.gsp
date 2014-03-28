<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>


<script type="text/javascript" defer="defer" >




$(function(){




		$(document).ready(function() {
	
			llenaCombo({
				url : CONTEXT_ROOT+'/json/anos.json',
				htmlOptions : {	
					name : "anio",
					id : "anio",
					clase : "chosen-select",
					style: "width:150px"
						
				},
				index : 0,
				chained : false,
				anchor : "#anio",
				combo : true,
				valorDefault:false,
				valorDefaultText:" Seleccione la año ",
				delTag: false,
				load:false
			});  
		    
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
							
							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
								<label class="uk-form-label" for="anio">
									<g:message code="variable.anio.label" default="Año" />
									<span class="required-indicator">*</span>
								</label>
								<div class="uk-form-controls">
								<select id="anio" name="anio" style="width:150px" ></select>
								</div>
							</div>



	

<hr class="uk-article-divider" class="uk-button">
	
	<g:actionSubmit class="uk-button uk-icon-download" action="borrarOrigen" value="Eliminar origen de datos" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" class="uk-button"/>
			


	
	

	
<hr class="uk-article-divider">




</g:form>

	
</body>

</html>

