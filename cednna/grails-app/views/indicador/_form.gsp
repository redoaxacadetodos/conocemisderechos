<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>


<h3>Datos generales del indicador</h3>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">
	<label class="uk-form-label" for="nombre">
		<g:message code="indicador.nombre.label" default="Nombre del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${indicadorInstance?.nombre}"/>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	<label class="uk-form-label" for="objetivo">
		<g:message code="indicador.objetivo.label" default="Objetivo del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="objetivo" required="" value="${indicadorInstance?.objetivo}"/>
</div>


<%--<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'anio', 'error')} required">--%>
<%--	<label class="uk-form-label"for="anio">--%>
<%--		<g:message code="indicador.anio.label" default="Año" />--%>
<%--		<span class="required-indicator">*</span>--%>
<%--	</label>--%>
<%--		<select id="anio" name="anio" ></select> --%>

<%--</div>--%>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'sentido', 'error')} required">
	<label class="uk-form-label"for="sentido">
		<g:message code="indicador.sentido.label" default="Sentido" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sentido" name="sentido.id" from="${mx.gob.redoaxaca.cednna.domino.Sentido.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.sentido?.id}" class="many-to-one"/>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label class="uk-form-label"for="dependencia">
		<g:message code="indicador.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	<label class="uk-form-label"for="ejecutora">
		<g:message code="indicador.ejecutora.label" default="Unidad Ejecutora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ejecutora" name="ejecutora.id" from="${mx.gob.redoaxaca.cednna.domino.UnidadEjecutora.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.ejecutora?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Frecuencia de medicion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="frecuencia" name="frecuencia.id" from="${mx.gob.redoaxaca.cednna.domino.Frecuencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.frecuencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">
	<label class="uk-form-label"for="comentarios">
		<g:message code="indicador.comentarios.label" default="Comentarios adicionales al indicador" />
		
	</label>
	<g:textField name="comentarios" value="${indicadorInstance?.comentarios}"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'mediosVerificacion', 'error')} ">
	<label class="uk-form-label"for="mediosVerificacion">
		<g:message code="indicador.mediosVerificacion.label" default="Medios Verificacion" />
		
	</label>
	<g:textField name="mediosVerificacion" value="${indicadorInstance?.mediosVerificacion}"/>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'publico', 'error')} ">
	<label class="uk-form-label"for="publico">
		<g:message code="indicador.publico.label" default="Publico" />
		
	</label>
	<g:checkBox name="publico" value="${indicadorInstance?.publico}" />
</div>


<br>
<br>


<h3>Datos del responsable del indicador</h3>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombreResponsable', 'error')} required">
	<label class="uk-form-label"for="nombreResponsable">
		<g:message code="indicador.nombreResponsable.label" default="Nombre del responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreResponsable"  maxlength="500" required="" value="${indicadorInstance?.nombreResponsable}"/>
</div>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'areaResponsable', 'error')} required">
	<label class="uk-form-label"for="areaResponsable">
		<g:message code="indicador.areaResponsable.label" default="Area del responsable" />
		<span class="required-indicator">*</span>
	</label>
		<g:textField name="areaResponsable"  maxlength="500" required="" value="${indicadorInstance?.areaResponsable}"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'mailResponsable', 'error')} required">
	<label class="uk-form-label"for="mailResponsable">
		<g:message code="indicador.mailResponsable.label" default="Email de contacto" />
		<span class="required-indicator">*</span>
	</label>
		<g:textField name="mailResponsable"  maxlength="1024" required="" value="${indicadorInstance?.mailResponsable}"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">
	<label class="uk-form-label"for="fechaActualizacion">
		<g:message code="indicador.fechaActualizacion.label" default="Fecha Actualizacion" />
		<span class="required-indicator">*</span>
	</label>
	<input  id="fechaActua" name="fechaActua"  type="text" value="${indicadorInstance?.fechaActualizacion?.format('dd/MM/yyyy')}"  />
	
	

	
</div>



<br>
<br>



<h3>Datos para el cálculo del indicador</h3>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'formula', 'error')} required">
	<label class="uk-form-label"for="formula">
		<g:message code="indicador.formula.label" default="Formula de cálculo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="formula" name="formula.id" from="${mx.gob.redoaxaca.cednna.domino.Formula.list()}" optionKey="id" required="" optionValue="nombre" value="${indicadorInstance?.formula?.id}" class="many-to-one"/>
	<br />${indicadorInstance?.formula?.sentencia}
</div>

<br>
<br>
<div id="divVariables">


</div>


<script type="text/javascript">

$(function(){

	
	$(document).ready(function() {


		$('#fechaActua').pickadate({
		    onOpen: function() {
		        console.log('Opened up!');
		    },
		    onClose: function() {
		    	
		    },
		    onRender: function() {
		        console.log('Just rendered anew');
		    },
		    onStart: function() {
		    	  console.log('Opened up!');
		    },
		    onStop: function() {
		        console.log('See ya');
		    },
		    onSet: function(event) {
		        console.log('Set stuff:', event);
		    }
		});
		
		
		$(".screen").html($(".outcome").val() );

	 	var unused = $.ajax({type:'POST', 
            url:CONTEXT_ROOT+'/indicador/buscadorVariable',
            data: "id="+$("#formula").val()+"&idIndicador="+$("#id").val(),
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
              data: "id="+$("#formula").val()+"&idIndicador="+$("#id").val(),
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


