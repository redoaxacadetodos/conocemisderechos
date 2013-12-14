<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>
</head>
<body>
<a href="#create-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>


<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li><g:link class="list" action="list">Origen de datos </g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Nuevo origen de datos</g:link></li>
			

<br>
	<br>
	<h2>Resultados del Archivo Procesado</h2>
	<br>
	<br>
	
	<h4>Total de registros procesados:</h4> <div class="total_registros">${ total }</div>
	<br>
	<h4>Registros cargados exitosamente: </h4> <div class="total_registros">${ buenos }</div>

	<br>
	<br>
	
	<h4>Registros que no fueron cargados debido a un problema: </h4><div class="total_registros">${ malos }</div>
	
	
	<br>
	<br>
	

	
	
			
	
	
</body>
</html>