
<%@ page import="mx.gob.redoaxaca.cednna.domino.Eje" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'eje.label', default: 'Eje')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-eje" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de modulos</g:link></li>
				<li><g:link class="create" action="create">Nuevo m&oacute;dulo</g:link></li>
			</ul>
		</div>
		<div id="show-eje" class="content scaffold-show" role="main">
			<h1>M&oacute;dulo</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list eje">
			
				<g:if test="${ejeInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="eje.descripcion.label" default="Descripci&oacute;n" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${ejeInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			


<%--				<g:if test="${ejeInstance?.division}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="division-label" class="property-label"><g:message code="eje.division.label" default="Derechos asociados a grupo de derechos" /></span>--%>
<%--					--%>
<%--						<g:each in="${ejeInstance.division}" var="d">--%>
<%--						<span class="property-value" aria-labelledby="division-label"><g:link controller="division" action="show" id="${d.id}">${d?.descripcion.encodeAsHTML()}</g:link></span>--%>
<%--						</g:each>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${ejeInstance?.id}" />
					<g:link class="edit" action="edit" id="${ejeInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
					<g:link class="edit" controller="division" action="create" params="[ideje:ejeInstance?.id]">Nuevo derecho</g:link>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
