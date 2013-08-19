<%@ page import="mx.gob.redoaxaca.cednna.domino.Estado" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: estadoInstance, field: 'clave', 'error')} ">
	<label for="clave" class="uk-form-label">
		<g:message code="estado.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${estadoInstance?.clave}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: estadoInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="estado.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${estadoInstance?.descripcion}"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: estadoInstance, field: 'prefijo', 'error')} ">
	<label for="prefijo" class="uk-form-label">
		<g:message code="estado.prefijo.label" default="Prefijo" />
		
	</label>
	<g:textField name="prefijo" value="${estadoInstance?.prefijo}"/>
</div>

