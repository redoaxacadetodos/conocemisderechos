
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<meta name="layout" content="main">
			<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				<g:javascript src="jquery.dataTables.js"  />
			
		
	</head>
	<body>
		<a href="#list-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
		<li class="uk-active"><g:link class="list" action="list">Listado</g:link></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>
</nav>		
		<br>

	 <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_NUCLEO">
		<div class="${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
			<label for="dependencia">
				<g:message code="indicador.dependencia.label" default="Dependencia" />
			 </label>
			 <g:select id="dependencia" name="dependencia" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.dependencia?.id}" class="many-to-one"/>
		
		</div>
	
		<br>
 		</sec:ifAnyGranted>
 		
 		 <sec:ifAnyGranted roles="ROLE_DEP">
 		 
 		 	<g:hiddenField name="dependencia" value="${dependencia}"/>
 		 		<g:hiddenField name="lectura" value="0"/>
 		 </sec:ifAnyGranted>
 		  <sec:ifAnyGranted roles="ROLE_LECTURA">
 		 
 		 	<g:hiddenField name="dependencia" value="${dependencia}"/>
 		 	<g:hiddenField name="lectura" value="1"/>
 		 	
 		 </sec:ifAnyGranted>
 		 
		<div id="divTabla">
		
				
		
		</div>


<script type="text/javascript" >


		function muestraBoton(source, type, val) 	
		{
			if($("#lectura").val()==0)
			return "<a href='#'  class='uk-icon-button uk-icon-search'  onclick='mostrarRegistro(" + source[0] + "); '\/><a href='#'  class='uk-icon-button uk-icon-edit'  onclick='editaRegistro(" + source[0] + "); '\/>"
			else{
			return "<a href='#'  class='uk-icon-button uk-icon-search'  onclick='mostrarRegistro(" + source[0] + "); '\/>"
			}

		}

		


		function mostrarRegistro(id){
			
			document.location.href=CONTEXT_ROOT+"/indicador/visor/"+id;

		}


		function editaRegistro(id){
			
		
			document.location.href=CONTEXT_ROOT+"/indicador/edit/"+id;
		


		}
			
				
				$(function(){

				
				
					$(document).ready(function() {


						var unused = $.ajax({type:'POST', 
				              url:CONTEXT_ROOT+'/indicador/tabla/'+$("#dependencia").val(),
				              success:function(data,textStatus)
				                  {
				              
				              		$('#divTabla').html(data);
				          
				             
				                  },
				           			   error:function(XMLHttpRequest,textStatus,errorThrown)
				                  {		$('#diverror').html(XMLHttpRequest.responseText);}
								});
							
						
						$("#dependencia").change(function(){
							
									
									  	var unused = $.ajax({type:'POST', 
								              url:CONTEXT_ROOT+'/indicador/tabla/'+$("#dependencia").val(),
								              success:function(data,textStatus)
								                  {
								              
								              		$('#divTabla').html(data);
								          
								             
								                  },
								           			   error:function(XMLHttpRequest,textStatus,errorThrown)
								                  {		$('#diverror').html(XMLHttpRequest.responseText);}
												});
						});
					

					});


					
			
				});
				
				
				
				
				
				</script>

	</body>
</html>


			
