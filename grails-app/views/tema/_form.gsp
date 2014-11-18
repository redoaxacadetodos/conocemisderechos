<%@ page import="mx.gob.redoaxaca.cednna.domino.Tema" %>



<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="tema.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<g:textField name="descripcion" value="${temaInstance?.descripcion}" class="uk-form-width-large"/>
</div>

<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'ped', 'error')} required uk-form-row">
	<label for="eje" class="uk-form-label">
		<g:message code="tema.ped.label" default="Ped" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="eje" name="eje.id" from="${mx.gob.redoaxaca.cednna.domino.PNDesarrollo.list()}" optionKey="id" optionValue="descripcion" required="" value="${temaInstance?.eje?.id}"/>
</div>

