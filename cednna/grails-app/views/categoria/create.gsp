<%@ page import="mx.gob.redoaxaca.cednna.domino.Categoria" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoria.label', default: 'Categoria')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-categoria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" controller="tipo" action="show" id="${tipoInstance.id}"><g:message code="categoria.father" args="[entityName]" /></g:link></li>
			</ul>
		</nav>
		<div id="create-categoria" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${categoriaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${categoriaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="form">
					<g:render template="form"/>
					<g:hiddenField name="idTipo" value="${tipoInstance?.id }"/>
				</fieldset>
	<fieldset class="uk-form uk-form-horizontal">
		<div class="fieldcontain">
					<g:submitButton name="create" class="save uk-button" value="${message(code: 'default.button.create.label', default: 'Create')}" />
					</div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
