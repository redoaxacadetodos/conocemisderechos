<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
  
  <h1>Variable  ${clave}</h1>
  <h2>Parametros busqueda</h2>
  
  <div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
	<label class="uk-form-label" for="region">
		<g:message code="variable.region.label" default="Región" />
		
	</label>
	<div class="uk-form-controls">
	<g:select id="region" name="region.id" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id" optionValue="descripcion"  class="chosen-select" style="width:350px;"  value="${variableInstance?.region?.id}"  noSelection="['null': '-Selecciona una región-']"/>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label class="uk-form-label" for="municipio">
		<g:message code="variable.municipio.label" default="Municipio" />
		
	</label>
	<div class="uk-form-controls">
	<div id="divMun">
	<g:select id="municipio" name="municipio.id" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id"  class="chosen-select" style="width:350px;"  optionValue="descripcion"  value="${variableInstance?.municipio?.id}"   noSelection="['null':'-Selecciona un municipio-']"/>
	</div>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label class="uk-form-label" for="localidad">
		<g:message code="variable.localidad.label" default="Localidad" />

	</label>
	<div class="uk-form-controls">
	<div id="divLoc">
	<g:select id="localidad" name="localidad.id" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id"  class="chosen-select" style="width:350px;"  optionValue="descripcion" value="${variableInstance?.localidad?.id}" noSelection="['null': '-Selecciona un localidad-']"/>
	</div>
	</div>
</div>	
  			
  			
		  	<div id="divResultado">
		  			
		  			
		  					
		  	</div>		
  </div>
</body>
</html>