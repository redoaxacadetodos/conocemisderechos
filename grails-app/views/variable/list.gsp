
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'variable.label', default: 'Variable')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<g:javascript src="jquery.dataTables.js" />
<%--<g:datatablehelperjs ctrlid="variableTable"--%>
<%--	context="${request.getContextPath()}" controller="variable"--%>
<%--	action="dataTablesListadoVariables" jqueryui="true"--%>
<%--	lang="${request.getContextPath()}/js/langtabla.json"--%>
<%--	aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:5}','{mData:6}','{mData:7}','{mData:8}','{mData:9}','{mData:10}','{mData:muestraBoton}']" />--%>
<g:hiddenField name="lectura" value="1" />

<sec:ifAnyGranted roles="ROLE_DEP, ROLE_ADMIN">
	<script type="text/javascript">
		$("#lectura").val("0");
	</script>
</sec:ifAnyGranted>
<script type="text/javascript">
		function muestraBoton(source, type, val){
			if($("#lectura").val()==0)
				return "<a href='"+ CONTEXT_ROOT+"/variable/edit/"+ source[0] + "'  class='uk-icon-button uk-icon-edit'/> ";
			else
				return "<a href='"+CONTEXT_ROOT+"/variable/show/"+ source[0] + "'  class='uk-icon-button uk-icon-search'/> ";
		}
</script>

<script type="text/javascript" charset="utf&minus;8">
			$(document).ready(function(){
				var div = "divVariables";
				var tabla = "tablaVariables";
				$('#'+div).html( "<table class='table table-striped table-hover table-bordered' id='"+ tabla + "'></table>" );
				$('#'+tabla).dataTable({	
					"bProcessing": true,
				    "bServerSide": true,
				    "sAjaxSource": "<g:createLink controller='variable' action='getTablaDatosEstadisticos'/>",
				    "oLanguage": {
				    	  "sUrl": "../datatables/language/spanish.txt"
				    	},
					"aoColumns": [
						{ "sTitle": "Id", bVisible: false },
						{ "sTitle": "Clave" },
						{ "sTitle": "Descripción" },
						{ "sTitle": "Región" },
						{ "sTitle": "Municipio" },
						{ "sTitle": "Categoría" },
						{ "sTitle": "Año/Ciclo" },
						{ "sTitle": "Población total" },
						{ "sTitle": "Mujeres" },
						{ "sTitle": "Hombres" },
						{ "sTitle": "Opciones", mData:muestraBoton }
					],
					"aoColumnDefs": [
						{ 'bSortable': false, 'aTargets': [ 10 ] }
					],
					"aaSorting": [[ 0, "asc" ]]
					
				});
			});
		</script>
</head>
<body>
	<a href="#list-variable" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
			<li class="uk-active"><g:link class="list" action="list">Datos estad&iacute;sticos </g:link></li>
			<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_DEP">
				<li><g:link class="create" action="create">Agregar datos</g:link></li>
				<li><g:link class="create" action="archivo">Subir desde archivo</g:link></li>	
			</sec:ifAnyGranted>
		</ul>	
			<!--	<li><g:link class="create" action="monitor">Monitor de datos</g:link></li>  -->
	</nav>

	<br>
	<br>
	<div class="body">
		<div id="divVariables"></div>
<%--		<g:datatablehelper ctrlid="variableTable"--%>
<%--			cols="['ID','Clave','Descripci&oacute;n','Regi&oacute;n','Municipio','Localidad','Categoría','Año','Poblaci&oacute;n Total','Mujeres','Hombres','Opciones']"--%>
<%--			class="table table-striped table-bordered"></g:datatablehelper>--%>
	</div>
</body>
</html>
