
<%@ page import="mx.gob.redoaxaca.cednna.domino.ObjetivoMilenio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'objetivoMilenio.label', default: 'ObjetivoMilenio')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-objetivoMilenio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create">Nuevo ODM</g:link></li>
			</ul>
		</div>
		<div id="list-objetivoMilenio" class="content scaffold-list" role="main">
			<h1>Listado de Objetivos de Desarrollo del Milenio</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'objetivoMilenio.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'objetivoMilenio.descripcion.label', default: 'Descripción')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${objetivoMilenioInstanceList}" status="i" var="objetivoMilenioInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${objetivoMilenioInstance.id}">${fieldValue(bean: objetivoMilenioInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: objetivoMilenioInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${objetivoMilenioInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
