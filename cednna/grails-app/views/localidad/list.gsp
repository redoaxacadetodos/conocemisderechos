
<%@ page import="mx.gob.redoaxaca.cednna.domino.Localidad" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'localidad.label', default: 'Localidad')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-localidad" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-localidad" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="clave" title="${message(code: 'localidad.clave.label', default: 'Clave')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'localidad.descripcion.label', default: 'Descripcion')}" />
					
						<th><g:message code="localidad.municipio.label" default="Municipio" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${localidadInstanceList}" status="i" var="localidadInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${localidadInstance.id}">${fieldValue(bean: localidadInstance, field: "clave")}</g:link></td>
					
						<td>${fieldValue(bean: localidadInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: localidadInstance, field: "municipio")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${localidadInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
