<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'manual.label', default: 'manual')}" />
		<title><g:message code="default.edit.label" args="[entityName]" /></title>
	</head>
	<body>
		<a href="#edit-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="manual">Listado de manuales</g:link></li>
			</ul>
		</div>
		<div id="edit-documento" class="content scaffold-edit" role="main">
			<h1>
				<g:message code="default.edit.label" args="[entityName]" /> -
				<g:if test="${id==1 }">
					Vista privada
				</g:if>
				<g:elseif test="${id==2 }">
					Vista pública
				</g:elseif>
				<g:else>
					Catálogo de indicadores
				</g:else>
			</h1>
			<g:if test="${flash.message}">
				<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:form method="post" class="uk-form uk-form-horizontal" enctype="multipart/form-data">
				<g:hiddenField name="id" value="${id}" />
				<fieldset class="form">
					<br/>
					<div class="fieldcontain required uk-form-row">
						<label for="url" class="uk-form-label">
							<g:message code="manual.url.label" default="Url del manual" />
							<span class="required-indicator">*</span>
						</label>
						<div class="uk-form-controls">
							<g:hiddenField name="tieneDocumento" value="${manual?.valor!=null }"/>
							<input id="url" name="url" type="file" value="${manual?.valor}" ${manual?.valor==null?'required=""':'' } >
						</div>
					</div>
				</fieldset>
				<fieldset >
					<g:actionSubmit class="save uk-button" action="guardarManual" value="${message(code: 'default.button.update.label', default: 'Update')}" />
				</fieldset>
			</g:form>
		</div>
	</body>
</html>
