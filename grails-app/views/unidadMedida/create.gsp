<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadMedida" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'unidadMedida.label', default: 'UnidadMedida')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-unidadMedida" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de unidades de medida</g:link></li>
			</ul>
		</div>
		<div id="create-unidadMedida" class="content scaffold-create" role="main">
			<h1>Nueva unidad de medida</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${unidadMedidaInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${unidadMedidaInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" class="uk-form uk-form-horizontal" >
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="uk-form-horizontal">
					<button  onclick="form.submit();" class="uk-button uk-button-primary">
						<i class="uk-icon-small uk-icon-edit"></i> ${message(code: 'default.button.create.label', default: 'Create')}
					</button>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
