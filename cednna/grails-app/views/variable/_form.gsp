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
		<g:message code="variable.descripcion.label" default="Descripción" />
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
	<g:select id="region" name="region.id" from="${mx.gob.redoaxaca.cednna.domino.Region.list()}" optionKey="id" optionValue="descripcion"  class="chosen-select" style="width:350px;"  value="${variableInstance?.region?.id}"  noSelection="['null': '-Selecciona una región-']"/>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label class="uk-form-label" for="municipio">
		<g:message code="variable.municipio.label" default="Municipio" />
		
	</label>
	<div class="uk-form-controls">
	<div id="divMun">
	<g:select id="municipio" name="municipio.id" from="${mx.gob.redoaxaca.cednna.domino.Municipio.list()}" optionKey="id"  class="chosen-select" style="width:350px;"  optionValue="descripcion"  value="${variableInstance?.municipio?.id}"   noSelection="['null':'-Selecciona un municipio-']"/>
	</div>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label class="uk-form-label" for="localidad">
		<g:message code="variable.localidad.label" default="Localidad" />

	</label>
	<div class="uk-form-controls">
	<div id="divLoc">
	<g:select id="localidad" name="localidad.id" from="${mx.gob.redoaxaca.cednna.domino.Localidad.list()}" optionKey="id"  class="chosen-select" style="width:350px;"  optionValue="descripcion" value="${variableInstance?.localidad?.id}" noSelection="['null': '-Selecciona un localidad-']"/>
	</div>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'poblacionTotal', 'error')} required">
	<label class="uk-form-label" for="poblacionTotal">
		<g:message code="variable.poblacionTotal.label" default="Poblacion Total" />

	</label>
	<div class="uk-form-controls">
	<g:field name="poblacionTotal" type="number" value="${variableInstance.poblacionTotal}" required=""/>
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

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'hombres', 'error')} required">
	<label class="uk-form-label" for="hombres">
		<g:message code="variable.hombres.label" default="Hombres" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
	<g:field name="hombres" type="number" value="${variableInstance.hombres}" required="" class="uk-button"/>
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


<br>


			
				
				
				
				
				
				<g:if  test="${variableInstance?.categorias}">
			
			
					<g:each   status="i" var="cat"  in="${variableInstance.categorias}" >
			
						<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
							<div class="uk-grid">
											<div class="uk-width-1-2">
													<label class="uk-form-label" for="localidad">
														<g:message code="indicador.localidad.label" default="Tipo de categoria" />
												
													</label>
													<g:select id="tipo_${i+1}" name="tipo_${i+1}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"  class="chosen-select" value="${cat?.tipo?.id}"   optionValue="descripcion"/>
											</div>
											<div class="uk-width-1-2">
														<label class="uk-form-label" for="localidad">
															<g:message code="indicador.localidad.label" default="Categoria" />
													
														</label>
														<div id="divTipo_${i+1}">
														<g:select id="categoria" name="categoria_${i+1}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" class="chosen-select"  value="${cat?.id}" optionValue="descripcion"   />
														</div>
											</div>
								</div>
						</div>
			
					</g:each>
					
					<g:hiddenField name="numCategorias" value="${variableInstance.categorias.size()}"/>
			</g:if>
			<g:else>
				<div class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">	
					 <div class="uk-grid">
								<div class="uk-width-1-2">
										<label for="localidad" class="uk-form-label">
											<g:message code="indicador.localidad.label" default="Tipo de categoria" />
									
									
										</label>
										<g:select id="tipo_1" name="tipo_1" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"  class="chosen-select" optionValue="descripcion"/>
										
								</div>
								<div class="uk-width-1-2">
											<label for="localidad" class="uk-form-label">
												<g:message code="indicador.localidad.label" default="Categoria" />
										
										
											</label>
											<div id="divTipo_1">
											<g:select id="categoria_1" name="categoria_1" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" class="chosen-select" optionValue="descripcion"   />
											</div>
									
								</div>
					</div>
			  </div>
			  	<g:hiddenField name="numCategorias" value="1"/>
			</g:else>
				
				
				
				

	<div id="divCate">
	
	
	</div>



	<br>

	<input id="addCat" name="addCat"  value="Agregar Categoria" type="button"  class="uk-button"/>






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


	asignaEventorRegion();

	

	
	asignaEventorMunicipio();
	
	 asignaEventorTipo(1);



		$("#addCat").click(function(){

			var cont=	parseInt($("#numCategorias").val());
			cont=cont+1;
			$("#numCategorias").val(cont);				  
	
				var unused = $.ajax({type:'POST', 
						              url:CONTEXT_ROOT+'/variable/categorias',
						              data: "con="+cont,
						              success:function(data,textStatus)
						              {
						              
						              		$('#divCate').append(data);
						              		asignaEventorTipo(cont);
						             
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

	 
	
});


function asignaEventorRegion(){


$("#region").change(function(){

		
		llenaCombo({
			url : CONTEXT_ROOT+'/variable/getMunicipioByRegion/'+$("#region").val(),
			htmlOptions : {
				name : "municipio.id",
				id : "municipio",
				clase : "chosen-select"
			},
			index : 0,
			chained : false,
			anchor : "#municipio",
			combo : true,
			valorDefault:true,
			valorDefaultText:" Seleccione el municipio ",
			delTag: true,
			tag:"#divMun"
		});  

		});
		
		asignaEventorMunicipio();
	
	
}

function asignaEventorMunicipio(){

	$("#municipio").change(function(){


		
		
		llenaCombo({
			url : CONTEXT_ROOT+'/variable/getLocalidadByMunicipio/'+$("#municipio").val(),
			htmlOptions : {
				name : "localidad.id",
				id : "localidad",
				clase : "chosen-select",
				
			},
			index : 0,
			chained : false,
			anchor : "#localidad",
			combo : true,
			valorDefault:true,
			valorDefaultText:" Seleccione la localidad ",
			delTag: true,
			tag:"#divLoc"
		});  


		});
	


	
}


function asignaEventorTipo(num){

	

	$("#tipo_"+num).change(function(){


		
		
		llenaCombo({
			url : CONTEXT_ROOT+'/variable/getCategoriaByTipo/'+$("#tipo_"+num).val(),
			htmlOptions : {
				name : "categoria_"+num,
				id : "categoria_"+num,
				clase : "chosen-select",
				
			},
			index : 0,
			chained : false,
			anchor : "#categoria_"+num,
			combo : true,
			valorDefault:false,
			valorDefaultText:" Seleccione la categoria ",
			delTag: true,
			tag:"#divTipo_"+num
		});  


		});
	


	
}







</script>

