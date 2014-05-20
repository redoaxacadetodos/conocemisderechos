<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>


<h3>Datos generales del indicador</h3>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">
	<label class="uk-form-label" for="nombre">
		<g:message code="indicador.nombre.label" default="Nombre del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${indicadorInstance?.nombre}" style="width:600px;"/>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	<label class="uk-form-label" for="objetivo">
		<g:message code="indicador.objetivo.label" default="Objetivo del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<textarea  name="objetivo" rows="5" cols="40">${indicadorInstance?.objetivo}</textarea>
	
</div>

<g:if test="${dep}">	

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label class="uk-form-label"for="dependencia">
		<g:message code="indicador.dependencia.label" default="Dependencia responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:hiddenField name="dependencia.id" value="${dep?.id}"/>
	<input  name="dep"  value="${dep?.descripcion}" style="width:600px;" type="text" readonly="readonly"/>
</div>


</g:if>
<g:else>
<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label class="uk-form-label"for="dependencia">
		<g:message code="indicador.dependencia.label" default="Dependencia responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list().sort{it.descripcion}}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.dependencia?.id}" class="many-to-one"/>
</div>
</g:else>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	<label class="uk-form-label"for="ejecutora">
		<g:message code="indicador.ejecutora.label" default="Unidad administrativa ejecutora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ejecutora" name="ejecutora.id" from="${mx.gob.redoaxaca.cednna.domino.UnidadEjecutora.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.ejecutora?.id}" class="many-to-one"/>
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
		<g:message code="indicador.sentido.label" default="Sentido esperado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sentido" name="sentido.id" from="${mx.gob.redoaxaca.cednna.domino.Sentido.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.sentido?.id}" class="many-to-one"/>
</div>





<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Frecuencia de medici&oacute;n" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="frecuencia" name="frecuencia.id" from="${mx.gob.redoaxaca.cednna.domino.Frecuencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.frecuencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Unidad de medida" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="uMedida" name="uMedida.id" from="${mx.gob.redoaxaca.cednna.domino.UnidadMedida.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.uMedida?.id}" class="many-to-one"/>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="M&oacute;dulo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="eje" name="eje.id" from="${mx.gob.redoaxaca.cednna.domino.Eje.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.division?.eje?.id}" class="many-to-one"/>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Categor&iacute;a" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="division" name="division.id" from="${mx.gob.redoaxaca.cednna.domino.Division.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.division?.id}" class="many-to-one"/>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} ">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Eje" />

	</label>
	<g:select id="pnDesarrollo" name="pnDesarrollo.id" from="${mx.gob.redoaxaca.cednna.domino.PNDesarrollo.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.pnDesarrollo?.id}" class="many-to-one"   noSelection="['null': '–No pertenece al PED-']"/>
</div>
<br>
<div id="divPND">
	
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')}">
		<label class="uk-form-label" for="nombre">
			<g:message code="indicador.nombre.label" default="Tema" />
		
		</label>
		<g:select id="tema" name="tema.id" from="${mx.gob.redoaxaca.cednna.domino.Tema.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.tema?.id}" class="many-to-one"   noSelection="['null': '–No pertenece al PED-']"/>

		</div>
		
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
				<g:message code="indicador.nombre.label" default="Objetivo PED"/>
		
			</label>
			<textarea  name="objetivoPND" rows="5" cols="40">${indicadorInstance?.objetivoPND}</textarea>
		</div>
		
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
				<g:message code="indicador.nombre.label" default="Estrategia" />
		
			</label>
			<g:textField name="estrategia"  value="${indicadorInstance?.estrategia}" style="width:600px;"/>
		</div>
		
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
				<g:message code="indicador.nombre.label" default="Nombre del programa" />
			
			</label>
			<g:textField name="nombrePrograma"  value="${indicadorInstance?.nombrePrograma}" style="width:600px;"/>
		</div>
	
	
</div>
<br>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} ">
	<label class="uk-form-label"for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Indicador ODM" />

	</label>
	<g:select id="objetivosMilenio" name="objetivosMilenio.id" from="${mx.gob.redoaxaca.cednna.domino.ObjetivoMilenio.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.objetivosMilenio?.id}" class="many-to-one"   noSelection="['null': '-No pertenece a un ODM-']"/>
</div>


<%--<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'mediosVerificacion', 'error')} ">--%>
<%--	<label class="uk-form-label"for="mediosVerificacion">--%>
<%--		<g:message code="indicador.mediosVerificacion.label" default="Medios de verificaci&oacute;n" />--%>
<%--		--%>
<%--	</label>--%>
<%--	<textarea  name="mediosVerificacion" rows="5" cols="40">${indicadorInstance?.mediosVerificacion}</textarea>--%>
<%--</div>--%>
<%----%>
<%----%>
<%--<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">--%>
<%--	<label class="uk-form-label"for="comentarios">--%>
<%--		<g:message code="indicador.comentarios.label" default="Fuente de informacion del indicador" />--%>
<%--		--%>
<%--	</label>--%>
<%--	<textarea  name="fuenteInformacion" rows="5" cols="40">${indicadorInstance?.fuenteInformacion}</textarea>--%>
<%--</div>--%>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">
	<label class="uk-form-label"for="comentarios">
		<g:message code="indicador.comentarios.label" default="Comentarios adicionales al indicador" />
		
	</label>
	<textarea  name="comentarios" rows="5" cols="40" required="required">${indicadorInstance?.comentarios}</textarea>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'publico', 'error')} ">
	<label class="uk-form-label"for="publico">
		<g:message code="indicador.publico.label" default="P&uacute;blico" />
		
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
	<g:textField name="nombreResponsable"  maxlength="500" required="" value="${indicadorInstance?.nombreResponsable}" style="width:500px;"/>
</div>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'areaResponsable', 'error')} required">
	<label class="uk-form-label"for="areaResponsable">
		<g:message code="indicador.areaResponsable.label" default="Área del responsable"  />
		<span class="required-indicator">*</span>
	</label>
		<g:textField name="areaResponsable"  maxlength="500" required="" value="${indicadorInstance?.areaResponsable}"  style="width:500px;" />
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'mailResponsable', 'error')} required">
	<label class="uk-form-label"for="mailResponsable">
		<g:message code="indicador.mailResponsable.label" default="Email de contacto" />
		<span class="required-indicator">*</span>
	</label>
		<input type="email"  name="mailResponsable" id="mailResponsable"maxlength="1024" required="" value="${indicadorInstance?.mailResponsable}"  style="width:500px;">
</div>

<%--<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">--%>
<%--	<label class="uk-form-label"for="fechaActualizacion">--%>
<%--		<g:message code="indicador.fechaActualizacion.label" default="Fecha de actualizaci&oacute;n"  />--%>
<%--		<span class="required-indicator">*</span>--%>
<%--	</label>--%>
<%--	<input  id="fechaActua" name="fechaActua"  type="text" value="${indicadorInstance?.fechaActualizacion?.format('dd/MM/yyyy')}" style="width:500px;"  />--%>
<%--</div>--%>


<br>
<br>



<h3>Datos para el cálculo del indicador</h3>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'formula', 'error')} required">
	<label class="uk-form-label"for="formula">
		<g:message code="indicador.formula.label" default="F&oacute;rmula de cálculo" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="formula" name="formula.id" from="${mx.gob.redoaxaca.cednna.domino.Formula.list()}" optionKey="id" required="" optionValue="nombre" value="${indicadorInstance?.formula?.id}" class="many-to-one"/>

</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
				<g:message code="indicador.nombre.label" default="No. decimales" />
			</label>
			<g:select name="decimales" from="${decimalesList }" value="${indicadorInstance?.decimales!=null ? indicadorInstance?.decimales: '2'}"/>
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
		    }}
		);
		

		
		llenaCombo({
			url : CONTEXT_ROOT+'/indicador/categoriasByModulo/'+$("#eje").val(),
				htmlOptions : {
				name : "division.id",
				id : "division"
			},
			index : "${indicadorInstance?.division?.id}",
			chained : false,
			anchor : "#division",
			combo : true,
			valorDefault:false,
			valorDefaultText:" Busca una variable ",
			
		});  

		llenaCombo({
			url : CONTEXT_ROOT+'/indicador/temaByEje/'+$("#pnDesarrollo").val(),
				htmlOptions : {
				name : "tema.id",
				id : "tema"
			},
			index : "${indicadorInstance?.tema?.id}",
			chained : false,
			anchor : "#tema",
			combo : true,
			valorDefault:false,
			valorDefaultText:" Busca una variable ",
			
		});  


		$('#eje').change(function() {

			llenaCombo({
				url : CONTEXT_ROOT+'/indicador/categoriasByModulo/'+$("#eje").val(),
				
				htmlOptions : {
					name : "division.id",
					id : "division"
					
				},
		
				chained : false,
				anchor : "#division",
				combo : true,
				valorDefault:false,
				valorDefaultText:" Busca una variable ",
				
			});  


			});

		$('#pnDesarrollo').change(function() {
			llenaCombo({
				url : CONTEXT_ROOT+'/indicador/temaByEje/'+$("#pnDesarrollo").val(),
					htmlOptions : {
					name : "tema.id",
					id : "tema"
				},
				index : "${indicadorInstance?.tema?.id}",
				chained : false,
				anchor : "#tema",
				combo : true,
				valorDefault:false,
				valorDefaultText:" Busca una variable ",
				
			}); 
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


