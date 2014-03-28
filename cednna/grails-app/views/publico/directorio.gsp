
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="public">
		<title>Directorio</title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				<g:javascript src="jquery.dataTables.js"  />
			<g:datatablehelperjs ctrlid="direcotrioid" context="${request.getContextPath()}" 
				controller="publico" action="datosdirectorio" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:5}','{mData:6}']"   
				/>
	
	</head>
	<body>
		<a href="#list-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
				  <div class="body">
				  
				  <g:datatablehelper ctrlid="direcotrioid"  cols="['ID','Nombre','Cargo','Dependencia','Correo','Tel&eacute;fono de Oficina','P&aacute;gina WEB']" class="table table-striped table-bordered"></g:datatablehelper>
				  </div>
</body>


</html>
