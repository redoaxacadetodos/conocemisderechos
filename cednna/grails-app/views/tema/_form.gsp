<%@ page import="mx.gob.redoaxaca.cednna.domino.Tema" %>



<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="tema.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<g:textField name="descripcion" value="${temaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: temaInstance, field: 'ped', 'error')} required">
	<label for="ped">
		<g:message code="tema.ped.label" default="Ped" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ped" name="ped.id" from="${mx.gob.redoaxaca.cednna.domino.PNDesarrollo.list()}" optionKey="id" optionValue="descripcion" required="" value="${temaInstance?.eje?.id}" class="many-to-one"/>
</div>

