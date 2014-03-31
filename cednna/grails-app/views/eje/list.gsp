
<%@ page import="mx.gob.redoaxaca.cednna.domino.Eje" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'eje.label', default: 'Eje')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-eje" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Nuevo m&oacute;dulo</g:link></li>
			</ul>
		</div>
		<div id="list-eje" class="content scaffold-list" role="main">
			<h1>Listado de módulos</h1>
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
				<g:each in="${ejeInstanceList}" status="i" var="ejeInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${ejeInstance.id}">${fieldValue(bean: ejeInstance, field: "descripcion")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${ejeInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
