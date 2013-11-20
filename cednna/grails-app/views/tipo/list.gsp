
<%@ page import="mx.gob.redoaxaca.cednna.domino.Tipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipo.label', default: 'Tipo')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
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
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="Descripci&oacute;n" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${tipoInstanceList}" status="i" var="tipoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${tipoInstance.id}">${fieldValue(bean: tipoInstance, field: "descripcion")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${tipoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
