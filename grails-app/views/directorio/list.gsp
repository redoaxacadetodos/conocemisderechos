
<%@ page import="mx.gob.redoaxaca.cednna.domino.Directorio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'directorio.label', default: 'Directorio')}" />
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				
			<g:datatablehelperjs ctrlid="direcotrioid" context="${request.getContextPath()}" 
				controller="directorio" action="datosdirectorio" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:mostrar } ','{mData:2}','{mData:3}','{mData:4}','{mData:5}','{mData:6}']"   
				/>
		<g:javascript src="jquery.dataTables.js"  />
		<script type="text/javascript">
			function mostrar(source, type, val){
				return "<a href='"+ CONTEXT_ROOT+"/directorio/show/"+ source[0] + "'> "+ source[1] +"</a> ";
			}
		</script>
	</head>
	<body>
		<a href="#list-directorio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-directorio" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:datatablehelper ctrlid="direcotrioid"  cols="['ID','Nombre','Cargo','Dependencia','Correo','Tel&eacute;fono de Oficina','P&aacute;gina WEB']" class="uk-table uk-table-hover uk-table-striped nuevocolortabla">
					  </g:datatablehelper>
		</div>
		
		<script src="${resource(dir: 'js', file: 'jquery.dataTables.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jsDatatables.bootstrap.js')}"></script>
	</body>
</html>
