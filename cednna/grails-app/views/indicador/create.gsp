<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

		
				<li><g:link class="list" action="list">Listado</g:link></li>
				<li class="uk-active"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

		</ul>
</nav>

		<div id="create-indicador" class="content scaffold-create" role="main">
			<h1 class="uk-article-title">Crea un nuevo indicador</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${indicadorInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${indicadorInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" name="indicador" >
				<fieldset class="uk-form uk-form-horizontal">
					<g:render template="form"/>
					<g:submitButton name="create" class="uk-button" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
				
			</g:form>
		</div>
	</body>
</html>
