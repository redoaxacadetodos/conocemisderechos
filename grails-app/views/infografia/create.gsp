<%@ page import="mx.gob.redoaxaca.cednna.domino.Infografia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'infografia.label', default: 'Infografía')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de infografías</g:link></li>
			</ul>
		</div>
		<div id="create-infografia" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${infografiaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${infografiaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" class="uk-form uk-form-horizontal" enctype="multipart/form-data">
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="uk-form-horizontal">
					<button name="create" onclick="form.submit();" class="save uk-button">
						<i class="uk-icon-edit"></i>${message(code: 'default.button.create.label', default: 'Create')}
					</button>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
