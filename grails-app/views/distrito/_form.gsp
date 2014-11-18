<%@ page import="mx.gob.redoaxaca.cednna.domino.Distrito" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: distritoInstance, field: 'clave', 'error')} ">
	<label for="clave" class="uk-form-label">
		<g:message code="distrito.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${distritoInstance?.clave}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: distritoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="distrito.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${distritoInstance?.descripcion}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: distritoInstance, field: 'entidad', 'error')} required">
	<label for="entidad" class="uk-form-label">
		<g:message code="distrito.entidad.label" default="Entidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="entidad" name="entidad.id" from="${mx.gob.redoaxaca.cednna.domino.Estado.list()}" optionKey="id" required="" value="${distritoInstance?.entidad?.id}" class="many-to-one"/>
</div>

