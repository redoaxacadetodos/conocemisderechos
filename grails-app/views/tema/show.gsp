
<%@ page import="mx.gob.redoaxaca.cednna.domino.Tema" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'tema.label', default: 'Tema')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-tema" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-tema" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list tema">
			
				<g:if test="${temaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="tema.descripcion.label" default="Descripci&oacute;n" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${temaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${temaInstance?.eje}">
				<li class="fieldcontain">
					<span id="ped-label" class="property-label"><g:message code="tema.ped.label" default="Eje" /></span>
					
						<span class="property-value" aria-labelledby="ped-label"><g:link controller="PNDesarrollo" action="show" id="${temaInstance?.eje?.id}">${temaInstance?.eje?.descripcion}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form-horizontal">
					<g:hiddenField name="id" value="${temaInstance?.id}" />
					<g:link class="uk-button" action="edit" id="${temaInstance?.id}"><i class="uk-icon-small uk-icon-edit"></i><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="uk-button" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
