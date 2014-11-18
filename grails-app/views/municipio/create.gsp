<%@ page import="mx.gob.redoaxaca.cednna.domino.Municipio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'municipio.label', default: 'Municipio')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-municipio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</nav>
		<div id="create-municipio" class="content scaffold-create" role="main">
			<h1 class="uk-article-title"><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${municipioInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${municipioInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" >
				<fieldset class="uk-form uk-form-horizontal">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="uk-form uk-form-horizontal">
				<div>
					<g:submitButton name="create" class="save uk-button" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
