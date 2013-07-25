<%@ page import="mx.gob.redoaxaca.cednna.domino.Estado" %>



<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'clave', 'error')} ">
	<label for="clave">
		<g:message code="estado.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${estadoInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="estado.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${estadoInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: estadoInstance, field: 'prefijo', 'error')} ">
	<label for="prefijo">
		<g:message code="estado.prefijo.label" default="Prefijo" />
		
	</label>
	<g:textField name="prefijo" value="${estadoInstance?.prefijo}"/>
</div>

