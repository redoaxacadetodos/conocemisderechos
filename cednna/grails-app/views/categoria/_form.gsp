<%@ page import="mx.gob.redoaxaca.cednna.domino.Categoria" %>



<div class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'clave', 'error')} ">
	<label for="clave">
		<g:message code="categoria.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${categoriaInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="categoria.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${categoriaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: categoriaInstance, field: 'tipo', 'error')} required">
	<label>
		<g:message code="categoria.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<label>
		${tipoInstance.descripcion}
		<g:hiddenField name="tipo.id" value="${tipoInstance.id}"/>
	</label>

	
</div>



