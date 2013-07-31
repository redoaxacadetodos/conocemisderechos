
<%@ page import="mx.gob.redoaxaca.cednna.domino.Distrito" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'distrito.label', default: 'Distrito')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-distrito" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-distrito" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'distrito.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'distrito.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="distrito.entidad.label" default="Entidad" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${distritoInstanceList}" status="i" var="distritoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${distritoInstance.id}">${fieldValue(bean: distritoInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: distritoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: distritoInstance, field: "entidad")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${distritoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
