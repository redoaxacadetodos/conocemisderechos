<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
<%--		<link rel="stylesheet" href="${resource(dir: 'css', file: 'datepicker.css')}" type="text/css">--%>
<%--		<g:javascript src="bootstrap-datepicker.js"/>--%>
		
		<!-- Bootstrap DateTimePicker -->
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap-datetimepicker.min.css')}" type="text/css">
		<g:javascript src="moment.js"/>
		<g:javascript src="locale/es.js"/>
		<g:javascript src="bootstrap-datetimepicker.min.js"/>
	</head>
	<body>
		<a href="#create-frecuenciaIndicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="create-frecuenciaIndicador" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.frecuencia.label" default="Nueva fecha" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${frecuenciaIndicadorInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${frecuenciaIndicadorInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save" class="uk-form uk-form-horizontal">
				<fieldset class="form">
					<g:render template="formFrecuencia"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
