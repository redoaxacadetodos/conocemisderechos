
<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-frecuenciaIndicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.create.frecuencia.label" default="Nueva fecha" /></g:link></li>
			</ul>
		</div>
		<div id="show-frecuenciaIndicador" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list frecuenciaIndicador">
			
<%--				<g:if test="${frecuenciaIndicadorInstance?.ano}">--%>
<%--				<li class="fieldcontain">--%>
<%--					<span id="ano-label" class="property-label"><g:message code="frecuenciaIndicador.ano.label" default="Ano" /></span>--%>
<%--					--%>
<%--						<span class="property-value" aria-labelledby="ano-label">${frecuenciaIndicadorInstance?.ano}</span>--%>
<%--					--%>
<%--				</li>--%>
<%--				</g:if>--%>
			
				<g:if test="${frecuenciaIndicadorInstance?.fechaActualizacion}">
				<li class="fieldcontain">
					<span id="fechaActualizacion-label" class="property-label"><g:message code="frecuenciaIndicador.fechaActualizacion.label" default="Fecha de actualización" /></span>
					
						<span class="property-value" aria-labelledby="fechaActualizacion-label"><g:formatDate type="date" style="LONG" date="${frecuenciaIndicadorInstance?.fechaActualizacion }"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${frecuenciaIndicadorInstance?.frecuencia}">
				<li class="fieldcontain">
					<span id="frecuencia-label" class="property-label"><g:message code="frecuenciaIndicador.frecuencia.label" default="Frecuencia" /></span>
					
						<span class="property-value" aria-labelledby="frecuencia-label">${frecuenciaIndicadorInstance?.frecuencia?.descripcion}</span>
					
				</li>
				</g:if>
			
				<g:if test="${frecuenciaIndicadorInstance?.variable}">
				<li class="fieldcontain">
					<span id="indicador-label" class="property-label"><g:message code="frecuenciaIndicador.variable.label" default="Variable" /></span>
					
						<span class="property-value" aria-labelledby="indicador-label">${CatOrigenDatos.findByClave(frecuenciaIndicadorInstance?.variable?.claveVar)?.descripcion}</span>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="uk-form-horizontal">
					<g:hiddenField name="id" value="${frecuenciaIndicadorInstance?.id}" />
					<g:link class="uk-button" action="edit" id="${frecuenciaIndicadorInstance?.id}"><i class="uk-icon-small uk-icon-edit"></i><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="uk-button" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
