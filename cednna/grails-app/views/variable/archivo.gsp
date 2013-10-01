<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>


<script type="text/javascript" defer="defer" >




$(function(){



	function bajarArchivo(id){

		
		
		document.location.href=CONTEXT_ROOT+"/variable/generaXLS/"+id+"&clave="++;

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
		tag:"#divTipo_"+num
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
			tag:"#divTipo_"+num
		});  


		});
	
}

</script>
</head>
<body>
<a href="#create-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
<g:form action="generaXLS" controller="variable" method="post" >

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li><g:link class="list" action="list">Origen de datos</g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Nuevo  origen de datos</g:link></li>
			
	
</nav>

	<h1  class="uk-article-title">Procesar datos desde archivo xlsx</h1>
	
	
	<h3  class="uk-article-content">Datos de configuracion del archivo</h3>



							<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
								<label class="uk-form-label" for="region">
									<g:message code="variable.region.label" default="Descripción" />
									
								</label>
								<div class="uk-form-controls">
								<g:select id="origenDatos" name="origenDatos" from="${mx.gob.redoaxaca.cednna.domino.CatOrigenDatos.list()}" optionKey="clave" optionValue="detalleCombo"  class="chosen-select" style="width:800px;"  value="${variableInstance?.clave}"  noSelection="['null': '- Ninguna descripcion-']"/>
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



	<div id="divCate">
	
	
	</div>



	<br>
	<g:hiddenField name="numCategorias" value="0"/>
	<input id="addCat" name="addCat"  value="Agregar Categor&iacute;a" type="button"  class="uk-button"/>


<hr class="uk-article-divider" class="uk-button">
	
	
	<g:submitButton name="create" class="uk-button uk-icon-download" value="Descarga el formato para carga de datos" />

	
	

	
<hr class="uk-article-divider">




</g:form>

	<g:form action="subirArchivo" controller="variable" method="post" enctype="multipart/form-data">
	<fieldset class="uk-form uk-form-horizontal">
		<div class="fieldcontain">
 		   <input type="file" name="fileBase" multiple="multiple" class="uk-form"></input>
		</div><br />
		<input type="submit" name="Procesar"  class="uk-button" value="Procesar archivo">
	</fieldset>
	</g:form>
</body>

</html>

