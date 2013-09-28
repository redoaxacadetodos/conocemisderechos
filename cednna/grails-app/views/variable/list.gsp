
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'variable.label', default: 'Variable')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				<g:javascript src="jquery.dataTables.js"  />
			<g:datatablehelperjs ctrlid="variableTable" context="${request.getContextPath()}" 
				controller="variable" action="dataTablesListadoVariables" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:5}','{mData:6}','{mData:7}','{mData:8}','{mData:muestraBoton}']"   
				/>
		<script type="text/javascript" >


		function muestraBoton(source, type, val) 	
		{
		return "<a href='#'  class='uk-icon-button uk-icon-edit'  onclick='editaRegistro(" + source[0] + "); '\/> "
		}

		




		function editaRegistro(id){
			
			document.location.href=CONTEXT_ROOT+"/variable/edit/"+id;

		}


		function monitorRegistro(id){
			
			document.location.href=CONTEXT_ROOT+"/variable/panel/"+id;

		}
			
			
				

				
</script>
	</head>
	<body>
		<a href="#list-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>


<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li class="uk-active"><g:link class="list" action="list">Origen de datos </g:link></li>

			
				<li><g:link class="create" action="create">Agregar origen de datos</g:link></li>
				<li><g:link class="create" action="archivo">Subir desde archivo</g:link></li>
			<!--	<li><g:link class="create" action="monitor">Monitor de datos</g:link></li>  -->

</nav>

					<br>
					<br>
				  <div class="body">
				  
				  <g:datatablehelper ctrlid="variableTable"  cols="['ID','Clave','Descripci&oacute;n','Regi&oacute;n','Municipio','Localidad','Poblaci&oacute;n Total','Hombres','Mujeres','Opciones']" class="table table-striped table-bordered"></g:datatablehelper>
				  </div>
</body>


</html>
