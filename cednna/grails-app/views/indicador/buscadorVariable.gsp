

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
	<g:select id="region_${var}" name="region_${var}" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id"  optionValue="descripcion" value="${indicadorInstance?.region?.id}"  data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;"   noSelection="['null':'-Selecciona una region-']"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'municipio', 'error')} required">
	<label for="municipio">
		<g:message code="indicador.municipio.label" default="Municipio" />
	
	</label>
	<div id="divMun_${var}">
	<g:select id="municipio_${var}" name="municipio_${var}" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" optionValue="descripcion"  value="${indicadorInstance?.municipio?.id}"   data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;"  noSelection="['null':'-Selecciona un municipio-']"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Localidad" />

	</label>
	<div id="divLoc_${var}">
	<g:select id="localidad_${var}" name="localidad_${var}" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id" optionValue="descripcion"  value="${indicadorInstance?.localidad?.id}"  data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;" noSelection="['null':'-Selecciona una localidad-']"/>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Poblacion" />

	</label>
	<g:select id="poblacion" name="poblacion_${var}" from="${mx.gob.redoaxaca.cednna.domino.Poblacion.list()}" optionKey="id" optionValue="descripcion" class="many-to-one"/>
	
</div>

	<br>
<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Tipo de categoria" />

	</label>
	<g:select id="tipo_1_${var}" name="tipo_1_${var}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"  class="chosen-select" optionValue="descripcion"/>
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Categoria" />

	</label>
	<div id="divTipo_1_${var}">
	<g:select id="categoria" name="categoria_1_${var}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" class="chosen-select" optionValue="descripcion"   />
	</div>
	<div id="divCate_${var}">
	
	
	</div>
	
	<br>

	<input id="addCat_${var}" name="addCat_${var}"  value="Agregar Categoria" type="button"  class="uk-button"/>
	
</div>

<g:hiddenField name="numCategorias_${var}" value="1"/>

<br>

	
	<div id="divResult_${var}">
	
	</div>
<br>


				<script type="text/javascript" defer="defer">
					
				
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

					
						$("#btn_${var}").click(function(){
							
							var datosFrm =$("#indicador").serialize();
					
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
					
				
					asignaEventorTipo(1,"${var}");

					$("#addCat_${var}").click(function(){

					var cont=	parseInt($("#numCategorias_${var}").val());
					cont=cont+1;
					$("#numCategorias_${var}").val(cont);				  
			
						var unused = $.ajax({type:'POST', 
								              url:CONTEXT_ROOT+'/indicador/categorias/${var}',
								              data: "con="+cont,
								              success:function(data,textStatus)
								              {
								              
								              		$('#divCate_${var}').append(data);
								              		asignaEventorTipo(cont,"${var}");
								             
								              },
								           			   error:function(XMLHttpRequest,textStatus,errorThrown)
								                  {		$('#diverror').html(XMLHttpRequest.responseText);}
							                  ,
							                  complete:function(data,textStatus){

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
																				
								              }
												});
						});


					asignaEventorRegion();

					

					
					asignaEventorMunicipio();

					
				});
				




				function asignaEventorRegion(){


				$("#region_${var}").change(function(){

						
						llenaCombo({
							url : CONTEXT_ROOT+'/variable/getMunicipioByRegion/'+$("#region_${var}").val(),
							htmlOptions : {
								name : "municipio_${var}.id",
								id : "municipio_${var}",
								clase : "chosen-select"
							},
							index : 0,
							chained : false,
							anchor : "#municipio_${var}",
							combo : true,
							valorDefault:true,
							valorDefaultText:" Seleccione el municipio ",
							delTag: true,
							tag:"#divMun_${var}"
						});  

						});
						
						asignaEventorMunicipio();
					
					
				}

				function asignaEventorMunicipio(){

					$("#municipio_${var}").change(function(){


						
						
						llenaCombo({
							url : CONTEXT_ROOT+'/variable/getLocalidadByMunicipio/'+$("#municipio_${var}").val(),
							htmlOptions : {
								name : "localidad_${var}.id",
								id : "localidad_${var}",
								clase : "chosen-select",
								
							},
							index : 0,
							chained : false,
							anchor : "#localidad_${var}",
							combo : true,
							valorDefault:true,
							valorDefaultText:" Seleccione la localidad ",
							delTag: true,
							tag:"#divLoc_${var}"
						});  


						});
					


					
				}


				function asignaEventorTipo(num,vari){

			

						$("#tipo_"+num+"_"+vari).change(function(){


							
							
							llenaCombo({
								url : CONTEXT_ROOT+'/variable/getCategoriaByTipo/'+$("#tipo_"+num+"_"+vari).val(),
								htmlOptions : {
									name : "categoria_"+num+"_"+vari+".id",
									id : "categoria_"+num+"_"+vari,
									clase : "chosen-select",
									
								},
								index : 0,
								chained : false,
								anchor : "#categoria_"+num+"_"+vari,
								combo : true,
								valorDefault:false,
								valorDefaultText:" Seleccione la categoria ",
								delTag: true,
								tag:"#divTipo_"+num+"_"+vari
							});  


							});
						


						
					}

				
				
				</script>



</g:each>



