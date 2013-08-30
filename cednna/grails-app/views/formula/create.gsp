<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formula.label', default: 'Formula')}" />
		<title>Nueva formula</title>
		
		
		

	</head>
	<body onload="Calc.init()" onresize="Calc.redraw()">
		<a href="#create-formula" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

		
				<li><g:link class="list" action="list">Listado de formulas</g:link></li>
				<li class="uk-active">	<li><g:link class="create" action="create">Nueva formula</g:link></li></li>

		</ul>
</nav>


		<div id="create-formula" class="content scaffold-create" role="main">

			<h1 class="uk-article-title"><g:message code="default.create.label" args="[entityName]" /></h1>
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
			<g:form action="save" >
				<fieldset class="uk-form uk-form-horizontal">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="uk-form uk-form-horizontal">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" class="uk-button"/>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
