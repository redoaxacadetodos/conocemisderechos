<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'clave', 'error')} required">
	<label class="uk-form-label" for="clave">
		<g:message code="variable.clave.label" default="Clave" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:textField name="clave" required="" value="${variableInstance?.clave}"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'descripcion', 'error')} required">
	<label class="uk-form-label" for="descripcion">
		<g:message code="variable.descripcion.label" default="Descripcion" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:textArea name="descripcion" cols="40" rows="5" maxlength="500" required="" value="${variableInstance?.descripcion}"/>
	</div>
</div>



<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
	<label class="uk-form-label" for="region">
		<g:message code="variable.region.label" default="Región" />
		
	</label>
	<div class="uk-form-controls">
	<g:select id="region" name="region.id" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id" optionValue="descripcion"  class="chosen-select" style="width:350px;"  value="${variableInstance?.region?.id}" class="many-to-one" noSelection="['null': '']"/>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label class="uk-form-label" for="municipio">
		<g:message code="variable.municipio.label" default="Municipio" />
		
	</label>
	<div class="uk-form-controls">
	<g:select id="municipio" name="municipio.id" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id"  class="chosen-select" style="width:350px;"  value="${variableInstance?.municipio?.id}" class="many-to-one" noSelection="['null': '']"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label class="uk-form-label" for="localidad">
		<g:message code="variable.localidad.label" default="Localidad" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:select id="localidad" name="localidad.id" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id"  class="chosen-select" style="width:350px;"  optionValue="descripcion" required="" value="${variableInstance?.localidad?.id}"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'poblacionTotal', 'error')} required">
	<label class="uk-form-label" for="poblacionTotal">
		<g:message code="variable.poblacionTotal.label" default="Poblacion Total" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:field name="poblacionTotal" type="number" value="${variableInstance.poblacionTotal}" required=""/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'hombres', 'error')} required">
	<label class="uk-form-label" for="hombres">
		<g:message code="variable.hombres.label" default="Hombres" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:field name="hombres" type="number" value="${variableInstance.hombres}" required=""/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'mujeres', 'error')} required">
	<label class="uk-form-label" for="mujeres">
		<g:message code="variable.mujeres.label" default="Mujeres" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:field name="mujeres" type="number" value="${variableInstance.mujeres}" required=""/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
	<label class="uk-form-label" for="anio">
		<g:message code="variable.anio.label" default="Año" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<select id="anio" name="anio" ></select>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label class="uk-form-label" for="municipio">
		<g:message code="variable.municipio.label" default="Tipo de categoria" />
		
	</label>

	<div class="uk-form-controls">
	<g:select id="tipo" name="tipo.id" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"   optionValue="descripcion"   class="many-to-one" noSelection="['null': '']"/>
	</div>

</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label class="uk-form-label" for="localidad">
		<g:message code="variable.localidad.label" default="Categoria" />

	</label>
	<div class="uk-form-controls">
	<g:select id="categoria" name="categoria.id" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id"  optionValue="descripcion"  class="many-to-one"/>
	</div>
</div>

 



<script type="text/javascript">

$(function(){


	var config = {
		      '.chosen-select'           : {},
		      '.chosen-select-deselect'  : {allow_single_deselect:true},
		      '.chosen-select-no-single' : {disable_search_threshold:10},
		      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
		      '.chosen-select-width'     : {width:"95%"}
		    }
		    for (var selector in config) {
		      $(selector).chosen(config[selector]);
		    }


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

