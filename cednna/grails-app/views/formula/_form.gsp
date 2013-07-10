<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>



<div class="fieldcontain ${hasErrors(bean: formulaInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="formula.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${formulaInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formulaInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="formula.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="descripcion" required="" value="${formulaInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formulaInstance, field: 'sentencia', 'error')} required">
	<label for="sentencia">
		<g:message code="formula.sentencia.label" default="Sentencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="sentencia" required="" value="${formulaInstance?.sentencia}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: formulaInstance, field: 'numVariables', 'error')} required">
	<label for="numVariables">
		<g:message code="formula.numVariables.label" default="Num Variables" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="numVariables" type="number" value="${formulaInstance.numVariables}" required=""/>
</div>

