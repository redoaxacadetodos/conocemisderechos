

<h2>${sentencia}</h2>

<label>${descripcion}</label>


<br><br>
<g:each var="var"  in="${variable}" >


<h3>Ubicación de datos para variable ${var}</h3>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">
	<label for="descripcion">
		<g:message code="indicador.estado.label" default="Descripción de la variable" />

	</label>

	<g:textField name="descripcion_${var}" value=""/>
	</div><br />

<g:hiddenField name="numCategorias_${var}" value="1"/>


<g:hiddenField name="estado_${var}" />


<%--<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">--%>
<%--	<label for="estado">--%>
<%--		<g:message code="indicador.estado.label" default="Estado" />--%>
<%----%>
<%--	</label>--%>
<%--	<g:select id="estado" name="estado_${var}" from="${mx.gob.redoaxaca.cednna.domino.Estado.list()}" optionKey="id" required="" optionValue="descripcion" value="${indicadorInstance?.estado?.id}" class="many-to-one" noSelection="['null':'-Seleccione un estado-']"/>--%>
<%--</div>--%>


<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'region', 'error')} required">
	<label for="region">
		<g:message code="indicador.region.label" default="Region" />

	</label>
	<g:select id="region" name="region_${var}" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id"  optionValue="descripcion" value="${indicadorInstance?.region?.id}" class="many-to-one" noSelection="['null':'-Selecciona una region-']"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'municipio', 'error')} required">
	<label for="municipio">
		<g:message code="indicador.municipio.label" default="Municipio" />
	
	</label>
	<g:select id="municipio" name="municipio_${var}" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" optionValue="descripcion"  value="${indicadorInstance?.municipio?.id}" class="many-to-one " noSelection="['null':'-Selecciona un municipio-']"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Localidad" />

	</label>
	<g:select id="localidad" name="localidad_${var}" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id" optionValue="descripcion"  value="${indicadorInstance?.localidad?.id}" class="many-to-one" noSelection="['null':'-Selecciona una localidad-']"/>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Poblacion" />

	</label>
	<g:select id="poblacion" name="poblacion_${var}" from="${mx.gob.redoaxaca.cednna.domino.Poblacion.list()}" optionKey="id" optionValue="descripcion" class="many-to-one"/>
	
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Tipo" />

	</label>
	<g:select id="tipo" name="tipo_${var}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id" optionValue="descripcion" class="many-to-one"/>
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Categoria" />

	</label>
	<g:select id="categoria" name="categoria_1_${var}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" optionValue="descripcion"   class="many-to-one"/>
	<div id="divCate">
	
	
	</div>
	
	
	<input id="addCat_${var}" name="addCat_${var}"  value="Agregar Categoria" type="button" />
	
</div>



<br>

	
	<div id="divResult_${var}">
	
	</div>
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
					
				


					$("#addCat_${var}").click(function(){

					var cont=	$("#numCategorias_${var}").val();
					cont=cont+1;
					$("#numCategorias_${var}").val(cont);				  
			
						var unused = $.ajax({type:'POST', 
								              url:CONTEXT_ROOT+'/indicador/categorias/${var}',
								              data: "con="+cont,
								              success:function(data,textStatus)
								              {
								              
								              		$('#divCate').append(data);
								          
								             
								              },
								           			   error:function(XMLHttpRequest,textStatus,errorThrown)
								                  {		$('#diverror').html(XMLHttpRequest.responseText);}
												});
						});
				});
				
				
				
				</script>



</g:each>



