<%@ page import="mx.gob.redoaxaca.cednna.domino.Division" %>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: divisionInstance, field: 'eje', 'error')} uk-form-row">
		<label for="clave" class="uk-form-label">
		<g:message code="division.eje.label" default="Eje" />
		
	</label>
		<div class="uk-form-controls">
		<g:select id="eje" name="eje.id" from="${mx.gob.redoaxaca.cednna.domino.Eje.list()}" optionKey="id"  optionValue="descripcion"  
			value="${divisionInstance?.eje?.id!=null?divisionInstance?.eje?.id:params.ideje!=null?params.ideje:''}" required=""/>
		</div>
</div>	

<div class="fieldcontain uk-form-row  ${hasErrors(bean: divisionInstance, field: 'descripcion', 'error')} uk-form-row">
	<label class="uk-form-label">
		<g:message code="division.descripcion.label" default="Descripción" />
		
	</label>
	<div class="uk-form-controls">
		<input name="descripcion" value="${divisionInstance?.descripcion}"  type="text" class="uk-form-width-large"/>
	</div>
</div>

