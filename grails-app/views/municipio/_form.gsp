<%@ page import="mx.gob.redoaxaca.cednna.domino.Municipio" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: municipioInstance, field: 'clave', 'error')} ">
	<label for="clave" class="uk-form-label">
		<g:message code="municipio.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${municipioInstance?.clave}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: municipioInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="municipio.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${municipioInstance?.descripcion}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: municipioInstance, field: 'distrito', 'error')} required">
	<label for="distrito" class="uk-form-label">
		<g:message code="municipio.distrito.label" default="Distrito" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="distrito" name="distrito.id" from="${mx.gob.redoaxaca.cednna.domino.Distrito.list()}" optionKey="id" required="" value="${municipioInstance?.distrito?.id}" class="many-to-one"/>
</div>

