
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'catOrigenDatos.label', default: 'CatOrigenDatos')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-catOrigenDatos" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de variables</g:link></li>
				<li><g:link class="create" action="create">Nueva variable</g:link></li>
			</ul>
		</div>
		<div id="show-catOrigenDatos" class="content scaffold-show" role="main">
			<h1>Variable</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list catOrigenDatos">
			
				<g:if test="${catOrigenDatosInstance?.dependencia}">
				<li class="fieldcontain">
					<span id="dependencia-label" class="property-label"><g:message code="catOrigenDatos.dependencia.label" default="Dependencia" /></span>
					
						<span class="property-value" aria-labelledby="dependencia-label"><g:link controller="dependencia" action="show" id="${catOrigenDatosInstance?.dependencia?.id}">${catOrigenDatosInstance?.dependencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${catOrigenDatosInstance?.clave}">
				<li class="fieldcontain">
					<span id="clave-label" class="property-label"><g:message code="catOrigenDatos.clave.label" default="Clave" /></span>
					
						<span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${catOrigenDatosInstance}" field="clave"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${catOrigenDatosInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="catOrigenDatos.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${catOrigenDatosInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${catOrigenDatosInstance?.id}" />
					<g:link class="edit" action="edit" id="${catOrigenDatosInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
