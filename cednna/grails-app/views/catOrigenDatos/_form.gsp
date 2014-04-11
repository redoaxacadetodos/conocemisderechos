<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>



<div class="fieldcontain ${hasErrors(bean: catOrigenDatosInstance, field: 'dependencia', 'error')} uk-form-row">
	<label for="dependencia" class="uk-form-label">
		<g:message code="catOrigenDatos.dependencia.label" default="Dependencia" />
		
	</label>
	<div class="uk-form-controls">
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" value="${catOrigenDatosInstance?.dependencia?.id}" class="many-to-one" noSelection="['null': 'Elige una dependencia']"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: catOrigenDatosInstance, field: 'clave', 'error')} uk-form-row">
	<label for="clave" class="uk-form-label">
		<g:message code="catOrigenDatos.clave.label" default="Clave" />
		
	</label>
	<div class="uk-form-controls">
	<g:textField name="clave" value="${catOrigenDatosInstance?.clave}"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: catOrigenDatosInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="descripcion" class="uk-form-label">
		<g:message code="catOrigenDatos.descripcion.label" default="Descripción" />
		
	</label>
	<div class="uk-form-controls">
	<g:textField name="descripcion" value="${catOrigenDatosInstance?.descripcion}"/>
	</div>
</div>
