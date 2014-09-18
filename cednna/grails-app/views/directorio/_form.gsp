<%@ page import="mx.gob.redoaxaca.cednna.domino.Directorio" %>



<div class="fieldcontain ${hasErrors(bean: directorioInstance, field: 'cargo', 'error')} uk-form-row">
	<label for="cargo" class="uk-form-label">
		<g:message code="directorio.cargo.label" default="Cargo" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="cargo" value="${directorioInstance?.cargo}" class="uk-width-2-3"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: directorioInstance, field: 'correo', 'error')} uk-form-row">
	<label for="correo" class="uk-form-label">
		<g:message code="directorio.correo.label" default="Correo" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="correo" value="${directorioInstance?.correo}" class="uk-width-2-3"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: directorioInstance, field: 'dependencia', 'error')} uk-form-row">
	<label for="dependencia" class="uk-form-label">
		<g:message code="directorio.dependencia.label" default="Dependencia" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="dependencia" value="${directorioInstance?.dependencia}" class="uk-width-2-3"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: directorioInstance, field: 'nombre', 'error')} uk-form-row">
	<label for="nombre" class="uk-form-label">
		<g:message code="directorio.nombre.label" default="Nombre" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="nombre" value="${directorioInstance?.nombre}" class="uk-width-2-3"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: directorioInstance, field: 'telefono', 'error')} uk-form-row">
	<label for="telefono" class="uk-form-label">
		<g:message code="directorio.telefono.label" default="Telefono" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="telefono" value="${directorioInstance?.telefono}" class="uk-width-2-3"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: directorioInstance, field: 'website', 'error')} uk-form-row">
	<label for="website" class="uk-form-label">
		<g:message code="directorio.website.label" default="Website" />
		
	</label>
	<div class="uk-form-controls">
		<g:textField name="website" value="${directorioInstance?.website}" class="uk-width-2-3"/>
	</div>
</div>

