
<%@ page import="mx.gob.redoaxaca.cednna.domino.Dependencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'dependencia.label', default: 'Dependencia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-dependencia" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Nueva dependencia</g:link></li>
			</ul>
		</div>
		<div id="list-dependencia" class="content scaffold-list" role="main">
			<h1>Listado de dependencias</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'dependencia.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="Descripci&oacute;n" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${dependenciaInstanceList}" status="i" var="dependenciaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${dependenciaInstance.id}">${fieldValue(bean: dependenciaInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: dependenciaInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${dependenciaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
