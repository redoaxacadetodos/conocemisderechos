<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formula.label', default: 'Formula')}" />
		<title><g:message code="default.create.label" args="[entityName]" /></title>
		
		
		
		<style>

#resultInput { 
	display: block;
	width: 99%;
	height: 12%;
	margin: 0 auto;  
	padding: 0;
	font-size: 100%; 
	background-color: #ccc; 
	border: none; 
	text-align: right; 

	border-radius: 3px;
	-webkit-border-radius: 3px;
	-moz-border-radius: 3px;	
	
	box-shadow:inset 0 0 5px #000000;	
	-moz-box-shadow:inset 0 0 5px #000000;
	-webkit-box-shadow:inset 0 0 5px #000000;
}
#basic { 
	margin: 0 auto; 
	padding: 0;
	border-collapse: collapse;
	height: 85%;
}
#basic td {

	border: 10px solid rgb(250, 250, 250);
	padding: 0;
	width: 18%;
	height: 18%;
}
#basic button {
	display: block;
	font-size: 100%;
	width: 100%;
	height: 100%;	
	padding: 0;
	text-align: center;
	vertical-align: middle;
	line-height: 100%;
	color: #eee;
	border: none;
	background-color: #333;
	background: -webkit-gradient(
		linear,
		left bottom,
		left top,
		color-stop(0.50, rgb(5,5,5)),
		color-stop(0.50, rgb(71,71,71))
	);
	background: -moz-linear-gradient(
		center bottom,
		rgb(5,5,5) 50%,
		rgb(71,71,71) 50%
	);	
	border: 1px solid #666;

	border-radius: 5px;
	-webkit-border-radius: 5px;
	-moz-border-radius: 5px;
}
#basic button:hover {
	color: #fff;
	cursor: pointer;
	border: 1px solid #fff;
}

</style>
	</head>
	<body onload="Calc.init()" onresize="Calc.redraw()">
		<a href="#create-formula" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<ul class="uk-subnav uk-subnav-pill">

		
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li class="uk-active"><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>

		</ul>



		<div id="create-formula" class="content scaffold-create" role="main">
			<h1><g:message code="default.create.label" args="[entityName]" /></h1>
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
				<fieldset class="form">
					<g:render template="form"/>
				</fieldset>
				<fieldset class="buttons">
					<g:submitButton name="create" class="save" value="${message(code: 'default.button.create.label', default: 'Create')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
