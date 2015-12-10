
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'variable.label', default: 'Variable')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list">Datos estadísticos</g:link></li>
				<li><g:link class="create" action="create">Nuevo dato</g:link></li>
			</ul>
</nav>
		<div id="show-variable" class="content scaffold-show" role="main">
			<h1 class="uk-article-title">Variable</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list variable">
			
				<g:if test="${variableInstance?.clave}">
				<li class="fieldcontain">
					<span id="clave-label" class="property-label"><g:message code="variable.clave.label" default="Clave" /></span>
					
						<span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${variableInstance}" field="clave"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${variableInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="variable.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${variableInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
	
			
			<g:if test="${variableInstance?.region}">
				<li class="fieldcontain">
					<span id="region-label" class="property-label"><g:message code="variable.region.label" default="Region" /></span>
					
						<span class="property-value" aria-labelledby="region-label"><g:link controller="region" action="show" id="${variableInstance?.region?.id}">${variableInstance?.region?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				<g:if test="${variableInstance?.municipio}">
				<li class="fieldcontain">
					<span id="municipio-label" class="property-label"><g:message code="variable.municipio.label" default="Municipio" /></span>
					
						<span class="property-value" aria-labelledby="municipio-label"><g:link controller="municipio" action="show" id="${variableInstance?.municipio?.id}">${variableInstance?.municipio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
				
					<g:if test="${variableInstance?.localidad}">
				<li class="fieldcontain">
					<span id="localidad-label" class="property-label"><g:message code="variable.localidad.label" default="Localidad" /></span>
					
						<span class="property-value" aria-labelledby="localidad-label"><g:link controller="localidad" action="show" id="${variableInstance?.localidad?.id}">${variableInstance?.localidad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${variableInstance?.poblacionTotal}">
				<li class="fieldcontain">
					<span id="poblacionTotal-label" class="property-label"><g:message code="variable.poblacionTotal.label" default="Poblacion Total" /></span>
					
						<span class="property-value" aria-labelledby="poblacionTotal-label"><g:fieldValue bean="${variableInstance}" field="poblacionTotal"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${variableInstance?.hombres}">
				<li class="fieldcontain">
					<span id="hombres-label" class="property-label"><g:message code="variable.hombres.label" default="Hombres" /></span>
					
						<span class="property-value" aria-labelledby="hombres-label"><g:fieldValue bean="${variableInstance}" field="hombres"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${variableInstance?.mujeres}">
				<li class="fieldcontain">
					<span id="mujeres-label" class="property-label"><g:message code="variable.mujeres.label" default="Mujeres" /></span>
					
						<span class="property-value" aria-labelledby="mujeres-label"><g:fieldValue bean="${variableInstance}" field="mujeres"/></span>
					
				</li>
				</g:if>
			
				
				
				<g:if test="${variableInstance?.periodo}">
					<li class="fieldcontain">
						<span id="periodo-label" class="property-label"><g:message code="variable.periodo.label" default="Periodo" /></span>
						
							<span class="property-value" aria-labelledby="anio-label">${variableInstance.periodo.descripcion}</span>
						
					</li>
				</g:if>
				<g:else>
					<li class="fieldcontain">
						<span id="anio-label" class="property-label"><g:message code="variable.anio.label" default="Año" /></span>
						
							<span class="property-value" aria-labelledby="anio-label">${variableInstance.anio}</span>
						
					</li>
				</g:else>
			</ol>
		</div>
	</body>
</html>
