
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="show-indicador" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list indicador">
			
				<g:if test="${indicadorInstance?.nombreResponsable}">
				<li class="fieldcontain">
					<span id="nombreResponsable-label" class="property-label"><g:message code="indicador.nombreResponsable.label" default="Nombre Responsable" /></span>
					
						<span class="property-value" aria-labelledby="nombreResponsable-label"><g:fieldValue bean="${indicadorInstance}" field="nombreResponsable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.fechaActualizacion}">
				<li class="fieldcontain">
					<span id="fechaActualizacion-label" class="property-label"><g:message code="indicador.fechaActualizacion.label" default="Fecha Actualizacion" /></span>
					
						<span class="property-value" aria-labelledby="fechaActualizacion-label"><g:formatDate date="${indicadorInstance?.fechaActualizacion}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.areaResponsable}">
				<li class="fieldcontain">
					<span id="areaResponsable-label" class="property-label"><g:message code="indicador.areaResponsable.label" default="Area Responsable" /></span>
					
						<span class="property-value" aria-labelledby="areaResponsable-label"><g:fieldValue bean="${indicadorInstance}" field="areaResponsable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.mailResponsable}">
				<li class="fieldcontain">
					<span id="mailResponsable-label" class="property-label"><g:message code="indicador.mailResponsable.label" default="Mail Responsable" /></span>
					
						<span class="property-value" aria-labelledby="mailResponsable-label"><g:fieldValue bean="${indicadorInstance}" field="mailResponsable"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.anio}">
				<li class="fieldcontain">
					<span id="anio-label" class="property-label"><g:message code="indicador.anio.label" default="Anio" /></span>
					
						<span class="property-value" aria-labelledby="anio-label"><g:fieldValue bean="${indicadorInstance}" field="anio"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.sentido}">
				<li class="fieldcontain">
					<span id="sentido-label" class="property-label"><g:message code="indicador.sentido.label" default="Sentido" /></span>
					
						<span class="property-value" aria-labelledby="sentido-label"><g:link controller="sentido" action="show" id="${indicadorInstance?.sentido?.id}">${indicadorInstance?.sentido?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.nombre}">
				<li class="fieldcontain">
					<span id="nombre-label" class="property-label"><g:message code="indicador.nombre.label" default="Nombre" /></span>
					
						<span class="property-value" aria-labelledby="nombre-label"><g:fieldValue bean="${indicadorInstance}" field="nombre"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.objetivo}">
				<li class="fieldcontain">
					<span id="objetivo-label" class="property-label"><g:message code="indicador.objetivo.label" default="Objetivo" /></span>
					
						<span class="property-value" aria-labelledby="objetivo-label"><g:fieldValue bean="${indicadorInstance}" field="objetivo"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.dependencia}">
				<li class="fieldcontain">
					<span id="dependencia-label" class="property-label"><g:message code="indicador.dependencia.label" default="Dependencia" /></span>
					
						<span class="property-value" aria-labelledby="dependencia-label"><g:link controller="dependencia" action="show" id="${indicadorInstance?.dependencia?.id}">${indicadorInstance?.dependencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.ejecutora}">
				<li class="fieldcontain">
					<span id="ejecutora-label" class="property-label"><g:message code="indicador.ejecutora.label" default="Ejecutora" /></span>
					
						<span class="property-value" aria-labelledby="ejecutora-label"><g:link controller="unidadEjecutora" action="show" id="${indicadorInstance?.ejecutora?.id}">${indicadorInstance?.ejecutora?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.frecuencia}">
				<li class="fieldcontain">
					<span id="frecuencia-label" class="property-label"><g:message code="indicador.frecuencia.label" default="Frecuencia" /></span>
					
						<span class="property-value" aria-labelledby="frecuencia-label"><g:link controller="frecuencia" action="show" id="${indicadorInstance?.frecuencia?.id}">${indicadorInstance?.frecuencia?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.formula}">
				<li class="fieldcontain">
					<span id="formula-label" class="property-label"><g:message code="indicador.formula.label" default="Formula" /></span>
					
						<span class="property-value" aria-labelledby="formula-label"><g:link controller="formula" action="show" id="${indicadorInstance?.formula?.id}">${indicadorInstance?.formula?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.resultadoIndicador}">
				<li class="fieldcontain">
					<span id="resultadoIndicador-label" class="property-label"><g:message code="indicador.resultadoIndicador.label" default="Resultado Indicador" /></span>
					
						<span class="property-value" aria-labelledby="resultadoIndicador-label"><g:fieldValue bean="${indicadorInstance}" field="resultadoIndicador"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.comentarios}">
				<li class="fieldcontain">
					<span id="comentarios-label" class="property-label"><g:message code="indicador.comentarios.label" default="Comentarios" /></span>
					
						<span class="property-value" aria-labelledby="comentarios-label"><g:fieldValue bean="${indicadorInstance}" field="comentarios"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.estado}">
				<li class="fieldcontain">
					<span id="estado-label" class="property-label"><g:message code="indicador.estado.label" default="Estado" /></span>
					
						<span class="property-value" aria-labelledby="estado-label"><g:link controller="estado" action="show" id="${indicadorInstance?.estado?.id}">${indicadorInstance?.estado?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.fecha}">
				<li class="fieldcontain">
					<span id="fecha-label" class="property-label"><g:message code="indicador.fecha.label" default="Fecha" /></span>
					
						<span class="property-value" aria-labelledby="fecha-label"><g:formatDate date="${indicadorInstance?.fecha}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.localidad}">
				<li class="fieldcontain">
					<span id="localidad-label" class="property-label"><g:message code="indicador.localidad.label" default="Localidad" /></span>
					
						<span class="property-value" aria-labelledby="localidad-label"><g:link controller="localidad" action="show" id="${indicadorInstance?.localidad?.id}">${indicadorInstance?.localidad?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.mediosVerificacion}">
				<li class="fieldcontain">
					<span id="mediosVerificacion-label" class="property-label"><g:message code="indicador.mediosVerificacion.label" default="Medios Verificacion" /></span>
					
						<span class="property-value" aria-labelledby="mediosVerificacion-label"><g:fieldValue bean="${indicadorInstance}" field="mediosVerificacion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.municipio}">
				<li class="fieldcontain">
					<span id="municipio-label" class="property-label"><g:message code="indicador.municipio.label" default="Municipio" /></span>
					
						<span class="property-value" aria-labelledby="municipio-label"><g:link controller="municipio" action="show" id="${indicadorInstance?.municipio?.id}">${indicadorInstance?.municipio?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.publico}">
				<li class="fieldcontain">
					<span id="publico-label" class="property-label"><g:message code="indicador.publico.label" default="Publico" /></span>
					
						<span class="property-value" aria-labelledby="publico-label"><g:formatBoolean boolean="${indicadorInstance?.publico}" /></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.region}">
				<li class="fieldcontain">
					<span id="region-label" class="property-label"><g:message code="indicador.region.label" default="Region" /></span>
					
						<span class="property-value" aria-labelledby="region-label"><g:link controller="region" action="show" id="${indicadorInstance?.region?.id}">${indicadorInstance?.region?.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			
				<g:if test="${indicadorInstance?.variables}">
				<li class="fieldcontain">
					<span id="variables-label" class="property-label"><g:message code="indicador.variables.label" default="Variables" /></span>
					
						<g:each in="${indicadorInstance.variables}" var="v">
						<span class="property-value" aria-labelledby="variables-label"><g:link controller="variable" action="show" id="${v.id}">${v?.encodeAsHTML()}</g:link></span>
						</g:each>
					
				</li>
				</g:if>
			
			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${indicadorInstance?.id}" />
					<g:link class="edit" action="edit" id="${indicadorInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
