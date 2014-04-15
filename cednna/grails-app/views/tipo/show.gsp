
<%@ page import="mx.gob.redoaxaca.cednna.domino.Tipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tipo.label', default: 'Tipo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tipo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list"><g:message code="tipo.list" /></g:link></li>
				<li><g:link class="create" action="create" ><g:message code="tipo.new"  /></g:link></li>
			</ul>
</nav>
		<div id="show-tipo" class="content scaffold-show" role="main">
			<h1><g:message code="tipo.show"  /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tipo">
			
				<g:if test="${tipoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tipo.descripcion.label" default="Descripción" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${tipoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
					<g:link class="create" controller="categoria" action="create" params="[idtipo:tipoInstance?.id]" ><g:message code="tipo.categoria.create" default="Nueva categor&iacute;a" /></g:link>
			</g:form>
			Categor&iacute;as asociadas al tipo
			<table>
				<thead>
					<tr>
					
						<g:sortableColumn property="descripcion" title="${message(code: 'tipo.descripcion.label', default: 'Descripción')}" />
					
					</tr>
				</thead>
				<tbody>
				<g:each in="${categoriaList}" status="i" var="categoriaInstance">
					<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
					
						<td><g:link params="[idTipo:tipoInstance?.id]" controller="categoria" action="show" id="${categoriaInstance.id}">${fieldValue(bean: categoriaInstance, field: "descripcion")}</g:link></td>
					
					</tr>
				</g:each>
				</tbody>
			</table>


			<g:form>
	<fieldset class="uk-form uk-form-horizontal">
					<g:hiddenField name="id" value="${tipoInstance?.id}" />
					<g:link class="edit" action="edit" id="${tipoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
