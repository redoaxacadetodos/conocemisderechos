<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>



<div class="fieldcontain ${hasErrors(bean: catOrigenDatosInstance, field: 'dependencia', 'error')} ">
	<label for="dependencia">
		<g:message code="catOrigenDatos.dependencia.label" default="Dependencia" />
		
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" value="${catOrigenDatosInstance?.dependencia?.id}" class="many-to-one" noSelection="['null': 'Elige una dependencia']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: catOrigenDatosInstance, field: 'clave', 'error')} ">
	<label for="clave">
		<g:message code="catOrigenDatos.clave.label" default="Clave" />
		
	</label>
	<g:textField name="clave" value="${catOrigenDatosInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: catOrigenDatosInstance, field: 'descripcion', 'error')} ">
	<label for="descripcion">
		<g:message code="catOrigenDatos.descripcion.label" default="Descripcion" />
		
	</label>
	<g:textField name="descripcion" value="${catOrigenDatosInstance?.descripcion}"/>
</div>
