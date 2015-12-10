
<%@ page import="mx.gob.redoaxaca.cednna.domino.Tipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipo.label', default: 'Tipo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<g:javascript src="jquery.dataTables.js" />
		<g:datatablehelperjs ctrlid="table"
			context="${request.getContextPath()}" controller="tipo"
			action="dataTablesList" jqueryui="true"
			lang="${resource(dir:'js', file:'langtabla.json')}"
			aoColumns="['{bVisible: false}','{mData:mostrar } ']"/>
		<script type="text/javascript">
			function mostrar(source, type, val){
				return "<a href='"+ CONTEXT_ROOT+"/tipo/show/"+ source[0] + "'> "+ source[1] +"</a> ";
			}
		</script>
	</head>
	<body>
		<a href="#list-tipo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
		<li class="uk-active"><g:link class="list" action="list"><g:message code="tipo.list"  /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="tipo.new" /></g:link></li>
			</ul>
</nav>
		<div id="list-tipo" class="content scaffold-list" role="main">
			<h1><g:message code="tipo.list"/></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:datatablehelper ctrlid="table"
				cols="['Id', 'Descripci&oacute;n']" class="table table-striped table-bordered nuevocolortabla"></g:datatablehelper>
		</div>
		<script type="text/javascript">
			$( window ).resize(function() {
				$( "#table" ).css('width', '100%');
			});
		</script>
	</body>
</html>
