
<%@ page import="mx.gob.redoaxaca.cednna.domino.Distrito" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'distrito.label', default: 'Distrito')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-distrito" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</nav>
		<div id="show-distrito" class="content scaffold-show" role="main">
			<h1 class="uk-article-title"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list distrito">
			
				<g:if test="${distritoInstance?.clave}">
				<li class="fieldcontain">
					<span id="clave-label" class="property-label"><g:message code="distrito.clave.label" default="Clave" /></span>
					
						<span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${distritoInstance}" field="clave"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${distritoInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="distrito.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${distritoInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${distritoInstance?.entidad}">
				<li class="fieldcontain">
					<span id="entidad-label" class="property-label"><g:message code="distrito.entidad.label" default="Entidad" /></span>
					
						<span class="property-value" aria-labelledby="entidad-label"><g:link controller="estado" action="show" id="${distritoInstance?.entidad?.id}">${distritoInstance?.entidad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form uk-form-horizontal">
				<div class="uk-form-controls>
					<g:hiddenField name="id" value="${distritoInstance?.id}" />
					<g:link class="edit uk-button" action="edit" id="${distritoInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" class="uk-button"/>
				</div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
