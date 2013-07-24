
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-indicador" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombreResponsable" title="${message(code: 'indicador.nombreResponsable.label', default: 'Nombre Responsable')}" />
					
						<g:sortableColumn property="fechaActualizacion" title="${message(code: 'indicador.fechaActualizacion.label', default: 'Fecha Actualizacion')}" />
					
						<g:sortableColumn property="areaResponsable" title="${message(code: 'indicador.areaResponsable.label', default: 'Area Responsable')}" />
					
						<g:sortableColumn property="mailResponsable" title="${message(code: 'indicador.mailResponsable.label', default: 'Mail Responsable')}" />
					
						<g:sortableColumn property="anio" title="${message(code: 'indicador.anio.label', default: 'Anio')}" />
					
						<th><g:message code="indicador.sentido.label" default="Sentido" /></th>
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${indicadorInstanceList}" status="i" var="indicadorInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${indicadorInstance.id}">${fieldValue(bean: indicadorInstance, field: "nombreResponsable")}</g:link></td>
					
						<td><g:formatDate date="${indicadorInstance.fechaActualizacion}" /></td>
					
						<td>${fieldValue(bean: indicadorInstance, field: "areaResponsable")}</td>
					
						<td>${fieldValue(bean: indicadorInstance, field: "mailResponsable")}</td>
					
						<td>${fieldValue(bean: indicadorInstance, field: "anio")}</td>
					
						<td>${fieldValue(bean: indicadorInstance, field: "sentido")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${indicadorInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
