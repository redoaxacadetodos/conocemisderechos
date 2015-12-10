
<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadMedida" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'unidadMedida.label', default: 'UnidadMedida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<g:javascript src="jquery.dataTables.js" />
		<g:datatablehelperjs ctrlid="table"
			context="${request.getContextPath()}" controller="unidadMedida"
			action="dataTablesList" jqueryui="true"
			lang="${resource(dir:'js', file:'langtabla.json')}"
			aoColumns="['{bVisible: false}','{mData:mostrar }','{mData:2 }']"/>
		<script type="text/javascript">
			function mostrar(source, type, val){
				return "<a href='"+ CONTEXT_ROOT+"/unidadMedida/show/"+ source[0] + "'> "+ source[1] +"</a> ";
			}
		</script>
	</head>
	<body>
		<a href="#list-unidadMedida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Nueva unidad de medida</g:link></li>
			</ul>
		</div>
		<div id="list-unidadMedida" class="content scaffold-list" role="main">
			<h1>Listado de unidades de medida</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:datatablehelper ctrlid="table"
				cols="['Id', 'Abreviatura', 'Descripci&oacute;n']" class="table table-striped table-bordered nuevocolortabla"></g:datatablehelper>
		</div>
		<script type="text/javascript">
			$( window ).resize(function() {
				$( "#table" ).css('width', '100%');
			});
		</script>
	</body>
</html>
