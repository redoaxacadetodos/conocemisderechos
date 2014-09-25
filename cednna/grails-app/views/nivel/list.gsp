
<%@ page import="mx.gob.redoaxaca.cednna.domino.Nivel" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'nivel.label', default: 'Nivel')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-nivel" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-nivel" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nivel" title="${message(code: 'nivel.nivel.label', default: 'Nivel')}" />
					
						<g:sortableColumn property="tipoNivel" title="${message(code: 'nivel.tipoNivel.label', default: 'tipoNivel')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${nivelInstanceList}" status="i" var="nivelInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${nivelInstance.id}">${fieldValue(bean: nivelInstance, field: "nivel")}</g:link></td>
					
						<td>${nivelInstance?.tipoNivel?.tipo}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${nivelInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
