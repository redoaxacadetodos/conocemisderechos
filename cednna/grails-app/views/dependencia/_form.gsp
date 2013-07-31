<%@ page import="mx.gob.redoaxaca.cednna.domino.Dependencia" %>



<div class="fieldcontain ${hasErrors(bean: dependenciaInstance, field: 'clave', 'error')} ">
	<label for="clave">
		<g:message code="dependencia.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${dependenciaInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: dependenciaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="dependencia.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${dependenciaInstance?.descripcion}"/>
</div>

