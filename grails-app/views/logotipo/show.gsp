
<%@ page import="mx.gob.redoaxaca.cednna.domino.Logotipo" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'logotipo.label', default: 'logotipo')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-logotipo" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de logotipos</g:link></li>
				<li><g:link class="create" action="create">Nueva logotipo</g:link></li>
			</ul>
		</div>
		<div id="show-logotipo" class="content scaffold-show" role="main">
			<h1>Logotipo</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list logotipo">
			
			
				<g:if test="${logotipoInstance?.titulo}">
					<li class="fieldcontain">
						<span id="titulo-label" class="property-label">Título</span>
						
							<span class="property-value" aria-labelledby="titulo-label"><g:fieldValue bean="${logotipoInstance}" field="titulo"/></span>
						
					</li>
				</g:if>
				
				<g:if test="${logotipoInstance?.ruta}">
					<br>
					<li class="fieldcontain">
						<div class="uk-width-small-1-3 uk-width-medium-1-5">
							<div class="uk-panel uk-panel-box colorpanelbox">
						  		<img src="${createLink(controller:'logotipo', action:'renderImage', params:['ruta':logotipoInstance?.ruta])}" alt="${logotipoInstance.titulo}" title="${logotipoInstance.titulo}">
						  	</div>
					  	</div>
					</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form-horizontal">
					<g:hiddenField name="id" value="${logotipoInstance?.id}" />
					<g:link class="uk-button" action="edit" id="${logotipoInstance?.id}"><i class="uk-icon-small uk-icon-edit"></i> <g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete uk-button" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
