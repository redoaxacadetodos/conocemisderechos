
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Logotipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="public">
		<title>Directorio</title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				
			<g:datatablehelperjs ctrlid="direcotrioid" context="${request.getContextPath()}" 
				controller="publico" action="datosdirectorio" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:5}','{mData:6}']"   
				/>
		<g:javascript src="jquery.dataTables.js"  />
	</head>
	<body>
		<br><br>
	  <div class="uk-overflow-container">
		  <g:datatablehelper ctrlid="direcotrioid"  cols="['ID','Nombre','Cargo','Dependencia','Correo','Tel&eacute;fono de Oficina','P&aacute;gina WEB']" class="uk-table uk-table-hover uk-table-striped">
		  </g:datatablehelper>
	  </div>
	  <div class="uk-grid body">
	  	  <div class="uk-width-1-1">
		  	<div class="uk-panel ">
		  	</div>
		  </div>
		 <g:each var="logotipoInstance" in="${Logotipo.getAll().sort{it.orden} }">
			<div class="uk-width-small-1-3 uk-width-medium-1-5">
				<div class="uk-panel uk-panel-box colorpanelbox">
			  		<img src="${createLink(controller:'publico', action:'renderImage', params:['ruta':logotipoInstance?.ruta])}" alt="${logotipoInstance.titulo}" title="${logotipoInstance.titulo}">
			  	</div>
		  	</div>
		  </g:each>
		</div>
	<script src="${resource(dir: 'js', file: 'jquery.dataTables.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jsDatatables.bootstrap.js')}"></script>
</body>


</html>
