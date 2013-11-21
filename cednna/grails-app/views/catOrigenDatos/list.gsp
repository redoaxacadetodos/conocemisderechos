
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-catOrigenDatos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Nueva variable</g:link></li>
			</ul>
		</div>
		<div id="list-catOrigenDatos" class="content scaffold-list" role="main">
			<h1>Listado de variables</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						
					
						<g:sortableColumn property="clave" title="${message(code: 'catOrigenDatos.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="Descripci&oacute;n" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${catOrigenDatosInstanceList}" status="i" var="catOrigenDatosInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						
					
						<td><g:link action="show" id="${catOrigenDatosInstance.id}">${fieldValue(bean: catOrigenDatosInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: catOrigenDatosInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${catOrigenDatosInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
