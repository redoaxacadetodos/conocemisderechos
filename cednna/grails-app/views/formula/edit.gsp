<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formula.label', default: 'Formula')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-formula" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
</nav></br>
		<div id="edit-formula" class="content scaffold-edit" role="main">
			<h1 class="uk-article-title"><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${formulaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${formulaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" >
				<g:hiddenField name="id" value="${formulaInstance?.id}" />
				<g:hiddenField name="version" value="${formulaInstance?.version}" />
				<fieldset class="uk-form uk-form-horizontal">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="uk-form uk-form-horizontal">
				<div class="uk-form-controls>
					<g:actionSubmit class="save" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" class="uk-button"/>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" class="uk-button"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
