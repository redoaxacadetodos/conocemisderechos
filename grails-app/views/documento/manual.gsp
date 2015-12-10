
<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'manual.label', default: 'Manual')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#list-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
			</ul>
		</div>
		<div id="list-documento" class="content scaffold-list" role="main">
			<h1>Manuales</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<table class="nuevocolortabla">
				<thead>
					<tr><th>Documento</th><th>Opciones</th></tr>
				</thead>
				<tbody>
					<tr>
						<td>Manual - Vista privada</td>
						<td><g:link action="editarManual" id="1" class="uk-icon-button uk-icon-edit"></g:link></td>
					</tr>
					<tr>
						<td>Manual - Vista pública</td>
						<td><g:link action="editarManual" id="2" class="uk-icon-button uk-icon-edit"></g:link></td>
					</tr>
					<tr>
						<td>Catálogo de indicadores</td>
						<td><g:link action="editarManual" id="3" class="uk-icon-button uk-icon-edit"></g:link></td>
					</tr>
				</tbody>
			</table>
			<g:form name="formDocumento" action="descargarDocumento">
				<g:hiddenField name="tipo"/>
				<g:hiddenField name="documento"/>
				<g:hiddenField name="nivel"/>
			</g:form>
		</div>
	</body>
</html>
