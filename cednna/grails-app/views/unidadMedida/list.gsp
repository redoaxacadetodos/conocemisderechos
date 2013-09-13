
<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadMedida" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'unidadMedida.label', default: 'UnidadMedida')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-unidadMedida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-unidadMedida" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="abreviatura" title="${message(code: 'unidadMedida.abreviatura.label', default: 'Abreviatura')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'unidadMedida.descripcion.label', default: 'Descripcion')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${unidadMedidaInstanceList}" status="i" var="unidadMedidaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${unidadMedidaInstance.id}">${fieldValue(bean: unidadMedidaInstance, field: "abreviatura")}</g:link></td>
					
						<td>${fieldValue(bean: unidadMedidaInstance, field: "descripcion")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${unidadMedidaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
