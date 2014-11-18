
<%@ page import="mx.gob.redoaxaca.cednna.domino.Municipio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'municipio.label', default: 'Municipio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-municipio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</nav>
		<div id="list-municipio" class="content scaffold-list" role="main">
			<h1 class="uk-article-title"><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'municipio.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'municipio.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="municipio.distrito.label" default="Distrito" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${municipioInstanceList}" status="i" var="municipioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${municipioInstance.id}">${fieldValue(bean: municipioInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: municipioInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: municipioInstance, field: "distrito")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${municipioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
