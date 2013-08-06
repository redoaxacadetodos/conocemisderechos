
<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formula.label', default: 'Formula')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-formula" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

		
				<li class="uk-active"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

		</ul>
</nav>
				

		<div id="list-formula" class="content scaffold-list" role="main">
		<h1 class="uk-article-title"><g:message code="default.list.label" args="[entityName]" /></h1>

			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="nombre" title="${message(code: 'formula.nombre.label', default: 'Nombre')}" />
					
						<g:sortableColumn property="descripcion" title="${message(code: 'formula.descripcion.label', default: 'Descripcion')}" />
					
						<g:sortableColumn property="sentencia" title="${message(code: 'formula.sentencia.label', default: 'Sentencia')}" />
					
						<g:sortableColumn property="numVariables" title="${message(code: 'formula.numVariables.label', default: 'Num Variables')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${formulaInstanceList}" status="i" var="formulaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link action="show" id="${formulaInstance.id}">${fieldValue(bean: formulaInstance, field: "nombre")}</g:link></td>
					
						<td>${fieldValue(bean: formulaInstance, field: "descripcion")}</td>
					
						<td>${fieldValue(bean: formulaInstance, field: "sentencia")}</td>
					
						<td>${fieldValue(bean: formulaInstance, field: "numVariables")}</td>
					
					</tr>
				</g:each>
				</tbody>
			</table>
			<div class="pagination">
				<g:paginate total="${formulaInstanceTotal}" />
			</div>
		</div>
	</body>
</html>
