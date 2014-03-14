<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>


<script type="text/javascript" defer="defer" >
	function submit(){
		$("#formDescargar").submit();
	}



$(function(){



	function bajarArchivo(id){

		
		
		document.location.href=CONTEXT_ROOT+"/variable/generaXLS/"+id;

	}
	

		$(document).ready(function() {
	
			llenaCombo({
				url : CONTEXT_ROOT+'/json/anos.json',
				htmlOptions : {	
					name : "anio",
					id : "anio",
					clase : "chosen-select",
					style: "width:150px"
						
				},
				index : 0,
				chained : false,
				anchor : "#anio",
				combo : true,
				valorDefault:false,
				valorDefaultText:" Seleccione la año ",
				delTag: false,
				load:false
			});  
		    
		});
		
		$("#addCat").click(function(){

			var cont=	parseInt($("#numCategorias").val());
			cont=cont+1;
			$("#numCategorias").val(cont);				  
	
				var unused = $.ajax({type:'POST', 
						              url:CONTEXT_ROOT+'/variable/categorias',
						              data: {con:cont,valida:$("#valida").val()},
						              success:function(data,textStatus)
						              {
						              
						              		$('#divCate').append(data);
						              		
						             
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
										
					                	  asignaEventorTipo(cont);
						              }
										});
				});

		
	

});




function asignaEventorTipo(num){

	
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
		tag:"#divTipo_"+num,
		load:true
	});  

	
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
			tag:"#divTipo_"+num,
			load:true
		});  


		});
	
}

</script>
</head>
<body>

<g:form action="generaXLS" controller="variable" method="post" name="formDescargar" >

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li><g:link class="list" action="list">Datos estad&iacute;sticos</g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Nuevo  datos estad&iacute;sticos</g:link></li>
			
	
</nav>

	<h1  class="uk-article-title">Procesar datos desde archivo xlsx</h1>
	
	
	<h3  class="uk-article-content">Datos de configuraci&oacute;n del archivo</h3>


 							<g:if test="${dependencia}">
							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
								<label class="uk-form-label" for="region">
									<g:message code="variable.region.label" default="Variable" />
									
								</label>
								<div class="uk-form-controls">
								<g:select id="origenDatos" name="origenDatos" from="${mx.gob.redoaxaca.cednna.domino.CatOrigenDatos.findAllByDependencia(dependencia)}" optionKey="clave" optionValue="detalleCombo"  class="chosen-select" style="width:800px;"  value="${variableInstance?.clave}"  noSelection="['null': '- Ninguna variable-']"/>
								</div>
							</div>
						
							</g:if><g:else>
							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
								<label class="uk-form-label" for="region">
									<g:message code="variable.region.label" default="Variable" />
									
								</label>
								<div class="uk-form-controls">
								<g:select id="origenDatos" name="origenDatos" from="${mx.gob.redoaxaca.cednna.domino.CatOrigenDatos.list()}" optionKey="clave" optionValue="detalleCombo"  class="chosen-select" style="width:800px;"  value="${variableInstance?.clave}"  noSelection="['null': '- Ninguna variable-']"/>
								</div>
							</div>
							
							</g:else>
				
							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
								<label class="uk-form-label" for="anio">
									<g:message code="variable.anio.label" default="Año" />
									<span class="required-indicator">*</span>
								</label>
								<div class="uk-form-controls">
								<select id="anio" name="anio" style="width:150px" ></select>
								</div>
							</div>


							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
						  	<label class="uk-form-label">Zona geografica a procesar:</label>
						  	<div class="uk-form-controls">
						  		<select id="opcionSerie" name="opcionSerie" >
						  			<option value="1">Estatal</option>
						  			<option value="2">Regional</option>
						  			<option value="3">Municipal</option>	  			
						  		</select>
						  	</div>
						  </div>



		<div id="divCate"></div>

		<br>
		<g:hiddenField name="valida" value="1" />
		<g:hiddenField name="numCategorias" value="0" />
		<input id="addCat" name="addCat" value="Agregar Categor&iacute;a"
			type="button" class="uk-button" />


		<hr class="uk-article-divider" class="uk-button">


		<g:submitToRemote class="uk-button uk-icon-download" 
			value="Descarga el formato para carga de datos" url="[action: 'tieneDatosOrigen']" 
			update="respuesta" />

		


		<hr class="uk-article-divider">




</g:form>
	<div id="respuesta"></div>
	<g:form action="subirArchivo" controller="variable" method="post" enctype="multipart/form-data">
	<fieldset class="uk-form uk-form-horizontal">
		<div class="fieldcontain">
 		   <input type="file" name="fileBase" multiple="multiple" class="uk-form"></input>
		</div><br />
		<input type="submit" name="Procesar"  class="uk-button" value="Procesar archivo"  onclick="spinerLoad('Procesando datos...');">
	</fieldset>
	</g:form>
</body>

</html>

