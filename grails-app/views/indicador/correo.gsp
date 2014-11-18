<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Correo')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<div id="edit-indicador" class="content scaffold-edit" role="main">
			<h1 class="uk-article-title"><g:message code="default.edit.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:form method="post" >
				<g:hiddenField name="id" value="${indicadorInstance?.id}" />
				<g:hiddenField name="version" value="${indicadorInstance?.version}" />
				<fieldset class="uk-form uk-form-horizontal">
					<div class="fieldcontain uk-form-row  required">
						<label class="uk-form-label" for="asunto">
							<g:message code="correro.asunto.label" default="Asunto" />
							<span class="required-indicator">*</span>
						</label>
						<div class="uk-form-controls">
							<g:textField name="asunto" required="" value="${asunto}" style="width:600px;"/>
						</div>
					</div>
					<div class="fieldcontain uk-form-row  required">
						<label class="uk-form-label" for="cuerpo">
							<g:message code="correro.cuerpo.label" default="Cuerpo del correo" />
							<span class="required-indicator">*</span>
						</label>
						<div class="uk-form-controls">
							<g:textArea name="cuerpo" required="" value="${cuerpo}" style="width:600px;"></g:textArea>
						</div>
					</div>
				</fieldset>
				<fieldset class="uk-form uk-form-horizontal">
				<div >
					<sec:ifAnyGranted roles="ROLE_ADMIN">
						<g:actionSubmit class="save uk-button" action="actualizarCorreo" value="${message(code: 'default.button.update.label', default: 'Update')}" />
					</sec:ifAnyGranted>
				</div>
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
