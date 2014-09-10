
<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-documento" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list documento">
			
				<g:if test="${documentoInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="documento.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:fieldValue bean="${documentoInstance}" field="tipo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.titulo}">
				<li class="fieldcontain">
					<span id="titulo-label" class="property-label"><g:message code="documento.titulo.label" default="Titulo" /></span>
					
						<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${documentoInstance}" field="titulo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${documentoInstance?.url}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="documento.url.label" default="Documento" /></span>
					
						<span class="property-value" aria-labelledby="url-label"><g:fieldValue bean="${documentoInstance}" field="url"/></span>
					
				</li>
				</g:if>
				
				<g:if test="${documentoInstance?.nivel}">
				<li class="fieldcontain">
					<span id="url-label" class="property-label"><g:message code="documento.nivel.label" default="Nivel" /></span>
					<span class="property-value" aria-labelledby="url-label">${documentoInstance.nivel.nivel}</span>
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form-horizontal">
					<g:hiddenField name="id" value="${documentoInstance?.id}" />
<%--					<g:link class="edit uk-button" action="edit" id="${documentoInstance?.id}"><i class="uk-icon-edit"></i><g:message code="default.button.edit.label" default="Edit" /></g:link>--%>
					<g:actionSubmit class="delete uk-button" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
