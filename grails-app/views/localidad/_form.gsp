<%@ page import="mx.gob.redoaxaca.cednna.domino.Localidad" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: localidadInstance, field: 'clave', 'error')} ">
	<label for="clave" class="uk-form-label">
		<g:message code="localidad.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${localidadInstance?.clave}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: localidadInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="localidad.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${localidadInstance?.descripcion}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: localidadInstance, field: 'municipio', 'error')} required">
	<label for="municipio" class="uk-form-label">
		<g:message code="localidad.municipio.label" default="Municipio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="municipio" name="municipio.id" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" required="" value="${localidadInstance?.municipio?.id}" class="many-to-one"/>
</div>

