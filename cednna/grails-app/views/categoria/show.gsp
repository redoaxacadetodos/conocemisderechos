
<%@ page import="mx.gob.redoaxaca.cednna.domino.Categoria" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'categoria.label', default: 'Categoria')}" />
		<title><g:message code="default.show.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#show-categoria" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><g:link class="list" controller="tipo" action="show" id="${categoriaInstance.tipo?.id}"><g:message code="categoria.father" args="[entityName]" /></g:link></li>
				<li><g:link class="create" action="create" params="[idtipo:categoriaInstance.tipo.id]"><g:message code="categoria.new"  /></g:link></li>
			</ul>
		</div>
		<div id="show-categoria" class="content scaffold-show" role="main">
			<h1><g:message code="default.show.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<ol class="property-list categoria">
			
				<g:if test="${categoriaInstance?.clave}">
				<li class="fieldcontain">
					<span id="clave-label" class="property-label"><g:message code="categoria.clave.label" default="Clave" /></span>
					
						<span class="property-value" aria-labelledby="clave-label"><g:fieldValue bean="${categoriaInstance}" field="clave"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${categoriaInstance?.descripcion}">
				<li class="fieldcontain">
					<span id="descripcion-label" class="property-label"><g:message code="categoria.descripcion.label" default="Descripcion" /></span>
					
						<span class="property-value" aria-labelledby="descripcion-label"><g:fieldValue bean="${categoriaInstance}" field="descripcion"/></span>
					
				</li>
				</g:if>
			
				<g:if test="${categoriaInstance?.tipo}">
				<li class="fieldcontain">
					<span id="tipo-label" class="property-label"><g:message code="categoria.tipo.label" default="Tipo" /></span>
					
						<span class="property-value" aria-labelledby="tipo-label"><g:link controller="tipo" action="show" id="${categoriaInstance?.tipo?.id}">${categoriaInstance?.tipo?.descripcion.encodeAsHTML()}</g:link></span>
					
				</li>
				</g:if>
			

			</ol>
			<g:form>
				<fieldset class="buttons">
					<g:hiddenField name="id" value="${categoriaInstance?.id}" />
					<g:link class="edit" action="edit" id="${categoriaInstance?.id}"><g:message code="default.button.edit.label" default="Edit" /></g:link>
					<g:actionSubmit class="delete" action="delete" value="${message(code: 'default.button.delete.label', default: 'Delete')}" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Are you sure?')}');" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
