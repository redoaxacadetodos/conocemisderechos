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

			<li><g:link class="list" action="list">Listado variables</g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Nueva variable</g:link></li>
			
	
</nav>

	<h2>Procesar datos desde archivo xlsx</h2>
	
	<br>
	
	<a href="${request.getContextPath()}/img/plantilla_origen_datos.xlsx"><label>Descarga el formato para carga de datos</label>  <img border='0' src='${request.getContextPath()}/img/excell.png'   style='cursor:pointer;' /></a>
	<br>
	<br>
	<g:form action="subirArchivo" controller="variable" method="post" enctype="multipart/form-data">
		<div class="fieldcontain">
 		   <input type="file" name="fileBase" multiple="multiple"></input>
		</div>
		<input type="submit" name="Procesar"  class="btn btn-primary" value="Procesar archivo" >
	</g:form>
</body>
</html>