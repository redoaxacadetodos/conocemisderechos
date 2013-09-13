<%@ page import="mx.gob.redoaxaca.cednna.domino.Eje" %>



<div class="fieldcontain ${hasErrors(bean: ejeInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="eje.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${ejeInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: ejeInstance, field: 'division', 'error')} ">
	<label for="division">
		<g:message code="eje.division.label" default="Division" />
		
	</label>
	<g:select name="division" from="${mx.gob.redoaxaca.cednna.domino.Division.list()}" multiple="multiple" optionKey="id" size="5" value="${ejeInstance?.division*.id}" class="many-to-many"/>
</div>

