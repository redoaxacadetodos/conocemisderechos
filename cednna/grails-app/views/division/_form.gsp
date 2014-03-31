<%@ page import="mx.gob.redoaxaca.cednna.domino.Division" %>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: divisionInstance, field: 'eje', 'error')} ">
		<label for="clave" class="uk-form-label">
		<g:message code="division.eje.label" default="Eje" />
		
	</label>
		<div class="uk-form-controls">
		<g:select id="eje" name="eje.id" from="${mx.gob.redoaxaca.cednna.domino.Eje.list()}" optionKey="id"  optionValue="descripcion"  value="${divisionInstance?.eje?.id}" class="many-to-one" noSelection="['null': '']"/>
		</div>
</div>	

<div class="fieldcontain uk-form-row  ${hasErrors(bean: divisionInstance, field: 'descripcion', 'error')} ">
	<label class="uk-form-label">
		<g:message code="division.descripcion.label" default="Descripción" />
		
	</label>
	<div class="uk-form-controls">
		<input name="descripcion" value="${divisionInstance?.descripcion}"  type="text" style="width:400px"/>
	</div>
</div>

