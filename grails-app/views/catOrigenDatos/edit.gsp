<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-catOrigenDatos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="mx.gob.redoaxaca.catOrigenDatos.lista.label" default="Lista" /></g:link></li>
				<li><g:link class="create" action="create">Nueva variable</g:link></li>
			</ul>
		</div>
		<div id="edit-catOrigenDatos" class="content scaffold-edit" role="main">
			<h1><g:message code="mx.gob.redoaxaca.catOrigenDatos.editar.label" default="Editar" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${catOrigenDatosInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${catOrigenDatosInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form method="post" class="uk-form uk-form-horizontal"  >
				<g:hiddenField name="id" value="${catOrigenDatosInstance?.id}" />
				<g:hiddenField name="version" value="${catOrigenDatosInstance?.version}" />
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="uk-form-horizontal">
					<g:actionSubmit class="uk-button" action="update" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					<g:actionSubmit class="uk-button" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" formnovalidate="" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
