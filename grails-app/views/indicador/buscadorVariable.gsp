

<h2>${sentencia}</h2>

<label>${descripcion}</label>


<br><br>
<g:each var="var"  in="${variable?.sort{it?.clave}}" >

<h3>Ubicación de datos para variable ${var.clave}</h3>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">
	<label class="uk-form-label" for="descripcion">
		<g:message code="indicador.estado.label" default="Variable" />

	</label>
		<div id="divDesc_${var?.clave}">
		
		<select id="claveVar_${var?.clave}"></select>
		
	
		</div>
</div>
<div class="fieldcontain uk-form-row">
	<label class="uk-form-label" for="intervalo">
		<g:message code="indicador.estado.label" default="Intervalo" />
	</label>
	<g:select id="intervalo" name="intervalo_${var?.clave}" from="[[k:0, v:0],[k:1, v:1],[k:2, v:2],[k:3, v:3],[k:4, v:4]]" 
		optionKey="k" optionValue="v" value="${var?.intervalo}" class="many-to-one"/>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'estado', 'error')} required">
	<label class="uk-form-label" for="descripcion">
		<g:message code="indicador.estado.label" default="Fuente de información" />

	</label>
		
		<g:textField name="descripcion_${var.clave}"  value="${var?.descripcion}"  style="width:650px;"/>	
</div>
		
<br />




<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<label class="uk-form-label" for="localidad">
		<g:message code="indicador.localidad.label" default="Poblaci&oacute;n" />

	</label>


	<g:select id="poblacion[]" name="poblacion_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Poblacion.list().sort{-it.id}}" 
		optionKey="id" optionValue="descripcion" value="${var?.poblacion?.id}" noSelection="['':'-Seleccione una opción-']"  class="many-to-one" required="required"/>
	
</div>
<h3>Categor&iacute;as</h3>


	<div id="divCate_${var.clave}">

	
			<g:if  test="${var?.categorias}">
			
			
					<g:each   status="i" var="cat"  in="${var.categorias}" >
			
					<div id="divCat_${i+1}_${var.clave}" class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
						<div class="uk-grid">
								<div class="uk-width-1-2">
										<label class="uk-form-label" for="localidad">
											<g:message code="indicador.localidad.label" default="Tipo de categoria" />
									
										</label>
										<g:select id="tipo_${i+1}_${var.clave}" name="tipo_${i+1}_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"  class="chosen-select" value="${cat?.tipo?.id}"   optionValue="descripcion"/>
										<div class="uk-width-1-2">
											<label class="uk-form-label" for="localidad">
												<g:message code="indicador.localidad.label" default="Categoria" />
										
											</label>
											<div id="divTipo_${i+1}_${var.clave}">
											<g:select id="categoria" name="categoria_${i+1}_${var.clave}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" class="chosen-select "   value="${cat?.id}" optionValue="descripcion"   />
											</div>
										</div>
								</div>
						</div>
					<input id="del_${i+1}_${var.clave}" name="del_${i+1}_${var.clave}"  value="-" type="button"  class="uk-button"/>
					<script type="text/javascript" defer="defer">

						
					
							$("#del_${i+1}_${var.clave}").click(function(){
								
								$("#divCat_${i+1}_${var.clave}").remove();

								var num = parseInt($("#numCategorias_${var.clave}").val()); 

								num=num-1;

								$("#numCategorias_${var.clave}").val(num);
								
								
							});	
					</script>
					</div>
					
					</g:each>
					
					<g:hiddenField name="numCategorias_${var.clave}" value="${var.categorias.size()}"/>
			</g:if>
			<g:else>
				
					<g:hiddenField name="numCategorias_${var.clave}" value="0"/>
			</g:else>
	<br>
	</div>



	<input id="addCat_${var.clave}" name="addCat_${var.clave}"  value="Agregar Categor&iacute;a" type="button"  class="uk-button"/>
	



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
					
<%--				--%>
<%--					asignaEventorTipo(1,"${var.clave}");--%>

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
<%--								              		asignaEventorTipo(cont,"${var.clave}");--%>
								             
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
							                		asignaEventorTipo(cont,"${var.clave}");				
								              }
												});
						});


					asignaEventorRegion();

					

					
					asignaEventorMunicipio();






					llenaComboVar({
						url : CONTEXT_ROOT+'/indicador/descripciones',
						htmlOptions : {
							name : "claveVar_${var.clave}",
							id : "claveVar_${var.clave}",
							clase : "chosen-select",
							
						},
						index : "${var.claveVar}",
						chained : false,
						anchor : "#claveVar_${var.clave}",
						combo : true,
						valorDefault:false,
						valorDefaultText:" Busca una variable ",
						delTag: true,
						tag:"#divDesc_${var.clave}"
					});  

					
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



