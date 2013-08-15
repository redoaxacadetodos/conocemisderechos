

<h2>${sentencia}</h2>

<label>${descripcion}</label>


<br><br>
<g:each var="var"  in="${variable}" >


<h3>Ubicación de datos para variable ${var.clave}</h3>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">
	<label for="descripcion">
		<g:message code="indicador.estado.label" default="Descripción de la variable" />

	</label>

	<g:textField name="descripcion_${var.clave}" value="${var.descripcion}"/>
	</div><br />



<%----%>
<%--<g:hiddenField name="estado_${var}" />--%>


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
	<g:select id="region_${var.clave}" name="region_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id"  optionValue="descripcion" value="${var?.region?.id}"  data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;"   noSelection="['null':'-Selecciona una region-']"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'municipio', 'error')} required">
	<label for="municipio">
		<g:message code="indicador.municipio.label" default="Municipio" />
	
	</label>
	<div id="divMun_${var.clave}">
	<g:select id="municipio_${var.clave}" name="municipio_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id" optionValue="descripcion"  value="${var?.municipio?.id}"   data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;"  noSelection="['null':'-Selecciona un municipio-']"/>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Localidad" />

	</label>
	<div id="divLoc_${var.clave}">
	<g:select id="localidad_${var.clave}" name="localidad_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id" optionValue="descripcion"  value="${var?.localidad?.id}"  data-placeholder="Choose a Country..." class="chosen-select" style="width:350px;" noSelection="['null':'-Selecciona una localidad-']"/>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label for="localidad">
		<g:message code="indicador.localidad.label" default="Poblacion" />

	</label>
	<g:select id="poblacion" name="poblacion_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Poblacion.list()}" optionKey="id" optionValue="descripcion" value="${var?.poblacion?.id}"  class="many-to-one"/>
	
</div>

	<br>
	<div id="divCate_${var.clave}">
	
			<g:if  test="${var.categorias.size()>0}">
			
			
					<g:each   status="i" var="cat"  in="${var.categorias}" >
			
			
								<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
										<label for="localidad">
											<g:message code="indicador.localidad.label" default="Tipo de categoria" />
									
										</label>
										<g:select id="tipo_${i+1}_${var.clave}" name="tipo_${i+1}_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"  class="chosen-select" value="${cat?.tipo?.id}"   optionValue="descripcion"/>
										<label for="localidad">
											<g:message code="indicador.localidad.label" default="Categoria" />
									
										</label>
										<div id="divTipo_${i+1}_${var.clave}">
										<g:select id="categoria" name="categoria_${i+1}_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" class="chosen-select"  value="${cat?.id}" optionValue="descripcion"   />
										</div>
					
								</div>
			
			
					</g:each>
			</g:if>
			<g:else>
					<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
							<label for="localidad">
								<g:message code="indicador.localidad.label" default="Tipo de categoria" />
						
							</label>
							<g:select id="tipo_1_${var.clave}" name="tipo_1_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"  class="chosen-select" value="${var?.localidad?.id}"   optionValue="descripcion"/>
							<label for="localidad">
								<g:message code="indicador.localidad.label" default="Categoria" />
						
							</label>
							<div id="divTipo_1_${var.clave}">
							<g:select id="categoria" name="categoria_1_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" class="chosen-select"  value="${var?.localidad?.id}" optionValue="descripcion"   />
							</div>
		
					</div>
			</g:else>
	<br>
	</div>

	<input id="addCat_${var.clave}" name="addCat_${var.clave}"  value="Agregar Categoria" type="button"  class="uk-button"/>
	

<g:hiddenField name="numCategorias_${var.clave}" value="${var.categorias.size()}"/>

<br>

	
	<div id="divResult_${var.clave}">
	
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

					
						$("#btn_${var.clave}").click(function(){
							
							var datosFrm =$("#indicador").serialize();
					
									  	var unused = $.ajax({type:'POST', 
								              url:CONTEXT_ROOT+'/indicador/resultadoVariable/${var.clave}',
								              data: datosFrm,
								              success:function(data,textStatus)
								                  {
								              
								              		$('#divResult_${var.clave}').html(data);
								          
								             
								                  },
								           			   error:function(XMLHttpRequest,textStatus,errorThrown)
								                  {		$('#diverror').html(XMLHttpRequest.responseText);}
												});
						});
					
				
					asignaEventorTipo(1,"${var.clave}");

					$("#addCat_${var.clave}").click(function(){

					var cont=	parseInt($("#numCategorias_${var.clave}").val());
					cont=cont+1;
					$("#numCategorias_${var.clave}").val(cont);				  
			
						var unused = $.ajax({type:'POST', 
								              url:CONTEXT_ROOT+'/indicador/categorias/${var.clave}',
								              data: "con="+cont,
								              success:function(data,textStatus)
								              {
								              
								              		$('#divCate_${var.clave}').append(data);
								              		asignaEventorTipo(cont,"${var.clave}");
								             
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


				$("#region_${var.clave}").change(function(){

						
						llenaCombo({
							url : CONTEXT_ROOT+'/variable/getMunicipioByRegion/'+$("#region_${var.clave}").val(),
							htmlOptions : {
								name : "municipio_${var.clave}.id",
								id : "municipio_${var.clave}",
								clase : "chosen-select"
							},
							index : 0,
							chained : false,
							anchor : "#municipio_${var.clave}",
							combo : true,
							valorDefault:true,
							valorDefaultText:" Seleccione el municipio ",
							delTag: true,
							tag:"#divMun_${var.clave}"
						});  

						});
						
						asignaEventorMunicipio();
					
					
				}

				function asignaEventorMunicipio(){

					$("#municipio_${var.clave}").change(function(){


						
						
						llenaCombo({
							url : CONTEXT_ROOT+'/variable/getLocalidadByMunicipio/'+$("#municipio_${var.clave}").val(),
							htmlOptions : {
								name : "localidad_${var.clave}.id",
								id : "localidad_${var.clave}",
								clase : "chosen-select",
								
							},
							index : 0,
							chained : false,
							anchor : "#localidad_${var.clave}",
							combo : true,
							valorDefault:true,
							valorDefaultText:" Seleccione la localidad ",
							delTag: true,
							tag:"#divLoc_${var.clave}"
						});  


						});
					


					
				}


				function asignaEventorTipo(num,vari){

			

						$("#tipo_"+num+"_"+vari).change(function(){


							
							
							llenaCombo({
								url : CONTEXT_ROOT+'/variable/getCategoriaByTipo/'+$("#tipo_"+num+"_"+vari).val(),
								htmlOptions : {
									name : "categoria_"+num+"_"+vari,
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



