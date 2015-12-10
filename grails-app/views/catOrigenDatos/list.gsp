
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<g:javascript src="jquery.dataTables.js"></g:javascript>
		<script type="text/javascript">
<%--			//Crear tabla de variables--%>
<%--			var div = "divVariables";--%>
<%--			var tabla = "tablaVariables";--%>
<%--			--%>
<%--			$.ajax( {--%>
<%--			    "url": "<g:createLink controller='catOrigenDatos' action='getTablaVariables' />",--%>
<%--			    "success": function ( json ) {--%>
<%--			    	$('#'+div).html( "<table class='table table-striped table-hover table-bordered' id='"+ tabla + "'></table>" );--%>
<%--			        $('#'+tabla).dataTable( json );--%>
<%--			        $('#'+tabla+'_filter input').addClass('form-control medium mayus');--%>
<%--			    },--%>
<%--			    "dataType": "json"--%>
<%--			} );--%>

			$(document).ready(function(){
				//Crear tabla de variables
				var div = "divVariables";
				var tabla = "tablaVariables";
				
				$('#'+div).html( "<table class='table table-striped table-hover table-bordered nuevocolortabla' id='"+ tabla + "'></table>" );
				$('#'+tabla).dataTable({	
					"bProcessing": true,
				    "bServerSide": true,
				    "sAjaxSource": "<g:createLink controller='catOrigenDatos' action='getTablaVariables' />",
				    "oLanguage": {
				    	  "sUrl": "../datatables/language/spanish.txt"
				    	},
					"aoColumns": [
						{ "sTitle": "Clave" },
						{ "sTitle": "Descripción" }
					],
					"aaSorting": [[ 0, "asc" ]]
				});
			});
		</script>
	</head>
	<body>
		<a href="#list-catOrigenDatos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Nueva variable</g:link></li>
			</ul>
		</div>
		<div id="list-catOrigenDatos" class="content scaffold-list" role="main">
			<h1>Listado de variables</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<div id="divVariables"></div>
			
		</div>
	</body>
</html>
