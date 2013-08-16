
<%@ page import="mx.gob.redoaxaca.cednna.domino.Estado" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'estado.label', default: 'Estado')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-estado" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
		<li class="uk-active"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

			</ul>
		</nav>
		<div id="list-estado" class="content scaffold-list" role="main">
			<h1 class="uk-article-title"><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'estado.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'estado.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="prefijo" title="${message(code: 'estado.prefijo.label', default: 'Prefijo')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${estadoInstanceList}" status="i" var="estadoInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${estadoInstance.id}">${fieldValue(bean: estadoInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: estadoInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: estadoInstance, field: "prefijo")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${estadoInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
