

<h2>${sentencia}</h2>

<label>${descripcion}</label>


<br><br>
<g:each var="var"  in="${variable}" >


<h4>Ubicacion de datos para variable ${var}</h4>




<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">
	<label for="estado">
		<g:message code="indicador.estado.label" default="Estado" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="estado" name="estado_${var}" from="${mx.gob.redoaxaca.cednna.domino.Estado.list()}" optionKey="id" required="" optionValue="descripcion" value="${indicadorInstance?.estado?.id}" class="many-to-one"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'region', 'error')} required">
	<label for="region">
		<g:message code="indicador.region.label" default="Region" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="region" name="region_${var}" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id"  optionValue="descripcion" required="" value="${indicadorInstance?.region?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'municipio', 'error')} required">
	<label for="municipio">
		<g:message code="indicador.municipio.label" default="Municipio" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="municipio" name="municipio_${var}" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.municipio?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Localidad" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="localidad" name="localidad_${var}" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.localidad?.id}" class="many-to-one"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Poblacion" />
		<span class="required-indicator">*</span>
	</label>
	<select id="poblacion_${var}" name="poblacion_${var}"><option value="T">Todos</option><option value="H">Hombres</option><option value="M">Mujeres</option></select>
	<h3></h3>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">

	<br>
	<label>Rango de edad  De:</label><g:textField name="edadDe_${var}"/><label> Hasta:</label><g:textField name="edadHasta_${var}"/>
</div>


<br>

	<input type="button"  id="btn_${var}" value="Buscar variable" class="save" />
	<div id="divResult_${var}">
	
	</div>
<br>

<br>

				<script type="text/javascript" defer="defer">
					
				
				$(function(){
				
						$("#btn_${var}").click(function(){
							
							var datosFrm =$("#indicador").serialize();
						//	datosFrm+="&anio="+$("#anio").val();
							

												
									  	var unused = $.ajax({type:'POST', 
								              url:CONTEXT_ROOT+'/indicador/resultadoVariable/${var}',
								              data: datosFrm,
								              success:function(data,textStatus)
								                  {
								              
								              		$('#divResult_${var}').html(data);
								          
								             
								                  },
								           			   error:function(XMLHttpRequest,textStatus,errorThrown)
								                  {		$('#diverror').html(XMLHttpRequest.responseText);}
												});
						});
					
				
				
				});
				
				
				
				</script>



</g:each>



