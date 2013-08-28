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
  			<input id="btnValores" name="btnValores"  value="Mostrar valores" type="button"  class="uk-button"/>
  			
		  	<div id="divResultado">
		  			
		  			
		  					
		  	</div>		
  </div>
  
  
  

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




	asignaEventorRegion();

	

	
	asignaEventorMunicipio();


	$("#btnValores").click(function(){


		buscarVariables();

	});
	
	

	 
	
});	


						function buscarVariables(){


							var unused = $.ajax({type:'POST', 
					              url:CONTEXT_ROOT+'/variable/resultadoPanel',
					              data: "region="+$("#region").val()+"&municipio="+$("#municipio").val()+"&localidad="+$("#localidad").val(),
					              success:function(data,textStatus)
					                  {
					              
					              	$('#divResultado').html(data);
					          
					             
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


