
<%@ page import="mx.gob.redoaxaca.cednna.domino.Municipio" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'municipio.label', default: 'Municipio')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-municipio" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</nav>
		<div id="show-municipio" class="content scaffold-show" role="main">
			<h1 class="uk-article-title"><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list municipio">
			
				<g:if test="${municipioInstance?.clave}">
				<li class="fieldcontain">
					<span id="clave-label" class="property-label"><g:message code="municipio.clave.label" default="Clave" /></span>
					
						<span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${municipioInstance}" field="clave"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${municipioInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="municipio.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${municipioInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${municipioInstance?.distrito}">
				<li class="fieldcontain">
					<span id="distrito-label" class="property-label"><g:message code="municipio.distrito.label" default="Distrito" /></span>
					
						<span class="property-value" aria-labelledby="distrito-label"><g:link controller="distrito" action="show" id="${municipioInstance?.distrito?.id}">${municipioInstance?.distrito?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${municipioInstance?.id}" />
					<g:link class="edit" action="edit" id="${municipioInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
