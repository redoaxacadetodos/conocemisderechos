<%@ page import="mx.gob.redoaxaca.cednna.domino.ObjetivoMilenio" %>



<div class="fieldcontain ${hasErrors(bean: objetivoMilenioInstance, field: 'clave', 'error')} ">
	<label for="clave">
		<g:message code="objetivoMilenio.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${objetivoMilenioInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: objetivoMilenioInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="objetivoMilenio.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${objetivoMilenioInstance?.descripcion}"/>
</div>

