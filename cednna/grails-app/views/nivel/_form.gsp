<%@ page import="mx.gob.redoaxaca.cednna.domino.Nivel" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.TipoEje" %>



<div class="fieldcontain ${hasErrors(bean: nivelInstance, field: 'nivel', 'error')} ">
	<label for="nivel">
		<g:message code="nivel.nivel.label" default="Nivel" />
		
	</label>
	<g:textField name="nivel" value="${nivelInstance?.nivel}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: nivelInstance, field: 'tipo', 'error')} required">
	<label for="tipo">
		<g:message code="nivel.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select name="tipoNivel.id" value="${nivelInstance.tipoNivel}" from="${TipoEje.list() }"
		optionKey="id" optionValue="tipo"/>
</div>

