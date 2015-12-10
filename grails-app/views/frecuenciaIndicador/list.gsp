
<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<g:javascript src="jquery.dataTables.js" />
	</head>
	<body>
		<a href="#list-frecuenciaIndicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.create.frecuencia.label" default="Nueva fecha" /></g:link></li>
			</ul>
		</div>
		<div id="list-frecuenciaIndicador" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<table id="tabla" class="table nuevocolortabla">
				<thead>
					<tr>
						<th><g:message code="frecuenciaIndicador.variable.label" default="Variable" /></th>
						<th>${message(code: 'frecuenciaIndicador.fechaActualizacion.label', default: 'Fecha de actualización')}</th>
						<th><g:message code="frecuenciaIndicador.frecuencia.label" default="Frecuencia" /></th>
					</tr>
				</thead>
				<tbody>
				<g:each in="${frecuenciaIndicadorInstanceList}" status="i" var="frecuenciaIndicadorInstance">
					<tr>
						<td><g:link action="show" id="${frecuenciaIndicadorInstance.id}">${CatOrigenDatos.findByClave(frecuenciaIndicadorInstance?.variable?.claveVar)?.descripcion}</g:link></td>
						<td><g:formatDate type="date" style="LONG" date="${frecuenciaIndicadorInstance.fechaActualizacion}" /></td>
						<td>${frecuenciaIndicadorInstance?.frecuencia?.descripcion }</td>
					</tr>
				</g:each>
				</tbody>
			</table>
		</div>
		<script type="text/javascript">
		$(document).ready(function(){
			$('#tabla').dataTable({
				 "oLanguage": {
			    	  "sUrl": "../datatables/language/spanish.txt"
			    	}
			});
		});
		</script>
	</body>
</html>
