<%@ page import="mx.gob.redoaxaca.cednna.domino.TipoEje" %>



<div class="fieldcontain ${hasErrors(bean: tipoEjeInstance, field: 'tipo', 'error')} ">
	<label for="tipo">
		<g:message code="tipoEje.tipo.label" default="Tipo" />
		
	</label>
	<g:textField name="tipo" value="${tipoEjeInstance?.tipo}"/>
</div>

