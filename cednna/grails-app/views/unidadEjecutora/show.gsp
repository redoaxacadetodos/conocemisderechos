
<%@ page import="mx.gob.redoaxaca.cednna.domino.UnidadEjecutora" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'unidadEjecutora.label', default: 'UnidadEjecutora')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-unidadEjecutora" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Lista de unidades ejecutoras</g:link></li>
				<li><g:link class="create" action="create">Nueva unidad ejecutora</g:link></li>
			</ul>
		</div>
		<div id="show-unidadEjecutora" class="content scaffold-show" role="main">
			<h1>Unidad ejecutora</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list unidadEjecutora">
			
				<g:if test="${unidadEjecutoraInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="unidadEjecutora.descripcion.label" default="Descripción" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${unidadEjecutoraInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${unidadEjecutoraInstance?.id}" />
					<g:link class="edit" action="edit" id="${unidadEjecutoraInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
