<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>


<h3>Datos generales del indicador</h3>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="indicador.nombre.label" default="Nombre del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${indicadorInstance?.nombre}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	<label for="objetivo">
		<g:message code="indicador.objetivo.label" default="Objetivo del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="objetivo" required="" value="${indicadorInstance?.objetivo}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="indicador.anio.label" default="Año" />
		<span class="required-indicator">*</span>
	</label>
		<select id="anio" name="anio" ></select> 
<%--	<g:field name="anio" type="number" value="${indicadorInstance.anio}" required=""/>--%>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'sentido', 'error')} required">
	<label for="sentido">
		<g:message code="indicador.sentido.label" default="Sentido" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sentido" name="sentido.id" from="${mx.gob.redoaxaca.cednna.domino.Sentido.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.sentido?.id}" class="many-to-one"/>
</div>




<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="indicador.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	<label for="ejecutora">
		<g:message code="indicador.ejecutora.label" default="Unidad Ejecutora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ejecutora" name="ejecutora.id" from="${mx.gob.redoaxaca.cednna.domino.UnidadEjecutora.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.ejecutora?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Frecuencia de medicion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="frecuencia" name="frecuencia.id" from="${mx.gob.redoaxaca.cednna.domino.Frecuencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.frecuencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="indicador.comentarios.label" default="Comentarios adicionales al indicador" />
		
	</label>
	<g:textField name="comentarios" value="${indicadorInstance?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'mediosVerificacion', 'error')} ">
	<label for="mediosVerificacion">
		<g:message code="indicador.mediosVerificacion.label" default="Medios Verificacion" />
		
	</label>
	<g:textField name="mediosVerificacion" value="${indicadorInstance?.mediosVerificacion}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'publico', 'error')} ">
	<label for="publico">
		<g:message code="indicador.publico.label" default="Publico" />
		
	</label>
	<g:checkBox name="publico" value="${indicadorInstance?.publico}" />
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'fecha', 'error')} required">
	<label for="fecha">
		<g:message code="indicador.fecha.label" default="Fecha de creacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fecha" precision="day"  value="${indicadorInstance?.fecha}"  />
</div>

<br>
<br>


<h3>Datos del responsable del indicador</h3>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'nombreResponsable', 'error')} required">
	<label for="nombreResponsable">
		<g:message code="indicador.nombreResponsable.label" default="Nombre del responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreResponsable"  maxlength="500" required="" value="${indicadorInstance?.nombreResponsable}"/>
</div>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'areaResponsable', 'error')} required">
	<label for="areaResponsable">
		<g:message code="indicador.areaResponsable.label" default="Area del responsable" />
		<span class="required-indicator">*</span>
	</label>
		<g:textField name="areaResponsable"  maxlength="500" required="" value="${indicadorInstance?.areaResponsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'mailResponsable', 'error')} required">
	<label for="mailResponsable">
		<g:message code="indicador.mailResponsable.label" default="Email de contacto" />
		<span class="required-indicator">*</span>
	</label>
		<g:textField name="mailResponsable"  maxlength="1024" required="" value="${indicadorInstance?.mailResponsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">
	<label for="fechaActualizacion">
		<g:message code="indicador.fechaActualizacion.label" default="Fecha Actualizacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaActualizacion" precision="day"  value="${indicadorInstance?.fechaActualizacion}"  />
</div>



<br>
<br>
<h3>Ubicacion de datos para indicador</h3>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="indicador.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado.id" from="${mx.gob.redoaxaca.cednna.domino.Estado.list()}" optionKey="id" required="" optionValue="descripcion" value="${indicadorInstance?.estado?.id}" class="many-to-one"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'region', 'error')} required">
	<label for="region">
		<g:message code="indicador.region.label" default="Region" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="region" name="region.id" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.region?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'municipio', 'error')} required">
	<label for="municipio">
		<g:message code="indicador.municipio.label" default="Municipio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="municipio" name="municipio.id" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.municipio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Localidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="localidad" name="localidad.id" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.localidad?.id}" class="many-to-one"/>
</div>


<br>
<br>

<h3>Datos par el calculo del indicador</h3>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'resultadoIndicador', 'error')} required">
	<label for="resultadoIndicador">
		<g:message code="indicador.resultadoIndicador.label" default="Resultado Indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="resultadoIndicador" value="${fieldValue(bean: indicadorInstance, field: 'resultadoIndicador')}" required=""/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'formula', 'error')} required">
	<label for="formula">
		<g:message code="indicador.formula.label" default="Formula de calculo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="formula" name="formula.id" from="${mx.gob.redoaxaca.cednna.domino.Formula.list()}" optionKey="id" required="" optionValue="nombre" value="${indicadorInstance?.formula?.id}" class="many-to-one"/>
	<h3>${indicadorInstance?.formula?.sentencia}</h3>
</div>

<br>
<br>
<div id="divVariables">


</div>


<script type="text/javascript">

$(function(){

	
	$(document).ready(function() {
		
		$(".screen").html($(".outcome").val() );


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
			

	 	var unused = $.ajax({type:'POST', 
            url:CONTEXT_ROOT+'/indicador/buscadorVariable',
            data: "id="+$("#formula").val(),
            success:function(data,textStatus)
                {
            
            	$('#divVariables').html(data);
        
           
                },
            error:function(XMLHttpRequest,textStatus,errorThrown)
                {$('#diverror').html(XMLHttpRequest.responseText);}
				});
		 

						
	});
	$('#formula').change(function() {

		  	var unused = $.ajax({type:'POST', 
              url:CONTEXT_ROOT+'/indicador/buscadorVariable',
              data: "id="+$("#formula").val(),
              success:function(data,textStatus)
                  {
              
              	$('#divVariables').html(data);
          
             
                  },
              error:function(XMLHttpRequest,textStatus,errorThrown)
                  {$('#diverror').html(XMLHttpRequest.responseText);}
				});




});




});
</script>


