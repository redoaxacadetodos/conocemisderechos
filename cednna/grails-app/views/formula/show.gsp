
<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formula.label', default: 'Formula')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-formula" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list">Listado de formulas</g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
</nav>
		<div id="show-formula" class="content scaffold-show" role="main">
			<h1 class="uk-article-title"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list formula">
			
				<g:if test="${formulaInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="formula.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${formulaInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formulaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="formula.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${formulaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formulaInstance?.sentencia}">
				<li class="fieldcontain">
					<span id="sentencia-label" class="property-label"><g:message code="formula.sentencia.label" default="Sentencia" /></span>
					
						<span class="property-value" aria-labelledby="sentencia-label"><g:fieldValue bean="${formulaInstance}" field="sentencia"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${formulaInstance?.numVariables}">
				<li class="fieldcontain">
					<span id="numVariables-label" class="property-label"><g:message code="formula.numVariables.label" default="Num Variables" /></span>
					
						<span class="property-value" aria-labelledby="numVariables-label"><g:fieldValue bean="${formulaInstance}" field="numVariables"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form uk-form-horizontal">
				<div class="uk-form-controls>
					<g:hiddenField name="id" value="${formulaInstance?.id}" />
					<p>
					<g:link class="edit uk-button" action="edit" id="${formulaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');"  class="uk-button"/>
					</p>
				</div>
				</fieldset>
				
			</g:form>
		</div>
	</body>
</html>
