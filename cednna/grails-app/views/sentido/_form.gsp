<%@ page import="mx.gob.redoaxaca.cednna.domino.Sentido" %>



<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="sentido.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="clave" type="number" value="${sentidoInstance.clave}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="sentido.descripcion.label" default="Descripci&oacute;n" />
		
	</label>
	<g:textField name="descripcion" value="${sentidoInstance?.descripcion}"/>
</div>

