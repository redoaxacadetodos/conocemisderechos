<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>



<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'clave', 'error')} required">
	<label for="clave">
		<g:message code="variable.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="clave" required="" value="${variableInstance?.clave}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'descripcion', 'error')} required">
	<label for="descripcion">
		<g:message code="variable.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="500" required="" value="${variableInstance?.descripcion}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'estado', 'error')} ">
	<label for="estado">
		<g:message code="variable.estado.label" default="Estado" />
		
	</label>
	<g:select id="estado" name="estado.id" from="${mx.gob.redoaxaca.cednna.domino.Estado.list()}" optionKey="id" value="${variableInstance?.estado?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
	<label for="region">
		<g:message code="variable.region.label" default="Region" />
		
	</label>
	<g:select id="region" name="region.id" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id" optionValue="descripcion"  value="${variableInstance?.region?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>


<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label for="municipio">
		<g:message code="variable.municipio.label" default="Municipio" />
		
	</label>
	<g:select id="municipio" name="municipio.id" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" value="${variableInstance?.municipio?.id}" class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="variable.localidad.label" default="Localidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="localidad" name="localidad.id" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id"  optionValue="descripcion" required="" value="${variableInstance?.localidad?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'poblacionTotal', 'error')} required">
	<label for="poblacionTotal">
		<g:message code="variable.poblacionTotal.label" default="Poblacion Total" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="poblacionTotal" type="number" value="${variableInstance.poblacionTotal}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'hombres', 'error')} required">
	<label for="hombres">
		<g:message code="variable.hombres.label" default="Hombres" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="hombres" type="number" value="${variableInstance.hombres}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'mujeres', 'error')} required">
	<label for="mujeres">
		<g:message code="variable.mujeres.label" default="Mujeres" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="mujeres" type="number" value="${variableInstance.mujeres}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="variable.anio.label" default="Anio" />
		<span class="required-indicator">*</span>
	</label>
	<select id="anio" name="anio" ></select>
</div>


<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label for="municipio">
		<g:message code="variable.municipio.label" default="Tipo" />
		
	</label>
	<g:select id="tipo" name="tipo.id" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"   optionValue="descripcion"  class="many-to-one" noSelection="['null': '']"/>
</div>

<div class="fieldcontain ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="variable.localidad.label" default="Categoria" />

	</label>
	<g:select id="categoria" name="categoria.id" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id"  optionValue="descripcion"  class="many-to-one"/>
</div>





<script type="text/javascript">

$(function(){


	llenaCombo({
		url : CONTEXT_ROOT+'/json/anos.json',
		htmlOptions : {
			name : "anio",
			id : "anio",
			clase : ""
		},
		index : 0,
		chained : false,
		anchor : "#anio",
		combo : true
	});  


	
});







</script>

