<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'variable.label', default: 'Variable')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#create-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>


<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li><g:link class="list" action="list">Datos estad&iacute;sticos</g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Agregar datos</g:link></li>


</nav>




		<div id="create-variable" class="content scaffold-create" role="main">
			<h1 class="uk-article-title">Nuevo origen de datos</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:hasErrors bean="${variableInstance}">
			<ul class="errors" role="alert">
				<g:eachError bean="${variableInstance}" var="error">
				<li <g:if test="${error in org.springframework.validation.FieldError}">data-field-id="${error.field}"</g:if>><g:message error="${error}"/></li>
				</g:eachError>
			</ul>
			</g:hasErrors>
			<g:form action="save"  name="frmVariable">
				<fieldset class="uk-form uk-form-horizontal">
					<g:render template="form"/>
				
					
					<input type="button" id="newVariable" name="newVariable"  value="${message(code: 'default.button.create.label', default: 'Create')}" class="uk-button"/>
					<br/>

				</fieldset>
			</g:form>
		</div>
	</body>
	
	<script type="text/javascript">


	$(function(){

		
	//	alert("asdasd");
		


		
		

	});
			



	</script>
</html>
