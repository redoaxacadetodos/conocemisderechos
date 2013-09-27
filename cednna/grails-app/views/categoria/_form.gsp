<%@ page import="mx.gob.redoaxaca.cednna.domino.Categoria" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: categoriaInstance, field: 'clave', 'error')} ">
	<label for="clave" class="uk-form-label">
		<g:message code="categoria.clave.label" default="Clave" />
		
	</label>
	<div class="uk-form-controls">
	<g:textField name="clave" value="${categoriaInstance?.clave}"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: categoriaInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion" class="uk-form-label">
		<g:message code="categoria.descripcion.label" default="Descripcion" />
		
	</label>
	<div class="uk-form-controls">
	<g:textField name="descripcion" value="${categoriaInstance?.descripcion}"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: categoriaInstance, field: 'tipo', 'error')} required">
	<label class="uk-form-label">
		<g:message code="categoria.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<label class="uk-form-label">
		${tipoInstance.descripcion}
		<g:hiddenField name="tipo.id" value="${tipoInstance.id}"/>
	</label>

	
</div>



