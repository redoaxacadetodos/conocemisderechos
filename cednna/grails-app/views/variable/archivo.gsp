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

	<h1  class="uk-article-title">Procesar datos desde archivo xlsx</h1>

	<a href="${request.getContextPath()}/img/plantilla_origen_datos.xlsx" class="uk-button"><i class="uk-icon-download"></i>
	 Descarga el formato para carga de datos </a>
<hr class="uk-article-divider">
	<g:form action="subirArchivo" controller="variable" method="post" enctype="multipart/form-data">
	<fieldset class="uk-form uk-form-horizontal">
		<div class="fieldcontain">
 		   <input type="file" name="fileBase" multiple="multiple" class="uk-form"></input>
		</div><br />
		<input type="submit" name="Procesar"  class="uk-button" value="Procesar archivo">
	</fieldset>
	</g:form>
</body>
</html>