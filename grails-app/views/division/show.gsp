
<%@ page import="mx.gob.redoaxaca.cednna.domino.Division" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'division.label', default: 'División')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-division" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de divisiones</g:link></li>
				<li><g:link class="create" action="create">Nueva divisi&oacute;n</g:link></li>
			</ul>
		</div>
		<div id="show-division" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list division">
			
				<g:if test="${divisionInstance?.eje}">
				<li class="fieldcontain">
					<span id="eje-label" class="property-label"><g:message code="division.eje.label" default="Eje:" /></span>
					
						<span class="property-value" aria-labelledby="eje-label"><g:link controller="eje" action="show" id="${divisionInstance?.eje?.id}">${divisionInstance?.eje?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${divisionInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="division.descripcion.label" default="Descripci&oacute;n:" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${divisionInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form-horizontal" >
					<g:hiddenField name="id" value="${divisionInstance?.id}" />
					<g:link class="uk-button" action="edit" id="${divisionInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="uk-button" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
