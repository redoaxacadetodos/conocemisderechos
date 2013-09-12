<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
    <nav class="uk-navbar">
		<ul class="uk-navbar-nav">

				<li class="uk-active"><g:link class="monitor" action="monitor">Monitor de datos </g:link></li>	
				<li><g:link class="create" action="create">Agregar origen de datos</g:link></li>
				<li><g:link class="create" action="archivo">Subir desde archivo</g:link></li>
				<li ><g:link class="list" action="list">Origen de datos </g:link></li>
				
		</ul>
</nav>
<br>
  <h1>Variable  ${clave} - ${desc} </h1>
  <h2>Parametros busqueda</h2>
  
<g:hiddenField name="region.id"  value="${region?.id}"/>
<g:hiddenField name="municipio.id"  value="${municipio?.id}"/>
<g:hiddenField name="localidad.id"  value="${localidad?.id}"/>  
  
<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
	<label class="uk-form-label" for="region">
		<h3><g:message code="variable.region.label" default="Región" /><h3>
		
	</label>
	<div class="uk-form-controls">
		<label>${region?.descripcion}</label>
	</div>
</div>


<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'municipio', 'error')} ">
	<label class="uk-form-label" for="municipio">
		<h3><g:message code="variable.municipio.label" default="Municipio" /></h3>
	</label>
	<div class="uk-form-controls">
		<label>${municipio?.descripcion}</label>
	</div>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label class="uk-form-label" for="localidad">
		<g:message code="variable.localidad.label" default="Localidad" />

	</label>
	<div class="uk-form-controls">
	<div id="divLoc">
	<label><h3>${localidad?.descripcion}</h3></label>
   </div>
	</div>
</div>	
<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'localidad', 'error')} required">
	<label><h3>Mujeres : ${mujeres}</h3><h3>Hombres : ${hombres}</h3><h3>Total : ${total}</h3></label>
</div>
<br>
<br>
			<h2>Desgloce por categorias</h2>
			<br>
  			<input id="btnValores" name="btnValores"  value="Agregar categoria" type="button"  class="uk-button"/>
  			
  			
  			<g:hiddenField name="numCategorias" value="0"/>
  	
		  	<div id="divResultado">
		  		
		  			
		  					
		  	</div>		
		  	
		  		<div id="dviBtnResult">
		  		<input id="btnResult" name="btnResult"  value="Realiza busqueda" type="button"  class="uk-button"/>
		  		</div>
		  
		  	<br>
			<br>
			<h2>Resultados</h2>
			<br>
  </div>
  
  
 

<script type="text/javascript" defer="defer">




$(function(){


	

	$(document).ready(function() {


		
			$("#dviBtnResult").hide();

			
		

	});

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




	asignaEventorRegion();
	asignaEventorMunicipio();


	


	

	$("#btnValores").click(function(){


		$("#dviBtnResult").show();
		addVariables();

	});
	
	

	 
	
});	


						function addVariables(){

							var contador = $("#numCategorias").val();
							var unused = $.ajax({type:'POST', 
					              url:CONTEXT_ROOT+'/variable/addCat',
					              data: "contador="+contador,
					              success:function(data,textStatus)
					                  {
					              
					              			$('#divResultado').append(data);
					          
					             
					                  },
					             		 error:function(XMLHttpRequest,textStatus,errorThrown)
					                  {$('#diverror').html(XMLHttpRequest.responseText);}
									});


						}


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


</script>
  
  
</body>

</html>


