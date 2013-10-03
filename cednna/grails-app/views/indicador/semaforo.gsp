
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Dependencia" %>
<%@ page contentType="text/html" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="main">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	  <script type="text/javascript">




	  
	  	function actualizar(){	  	
	


		  	$.ajax({type:'POST', 
	            url:CONTEXT_ROOT+'/indicador/actualizarSemaforo',
	            data: "id="+$("#dependencias").val()+"&rol="+$("#rol").val(),
	            success:function(data,textStatus)
	                {
	            
	            	$('#semaforo').html(data);
	        
	           
	                },
	            error:function(XMLHttpRequest,textStatus,errorThrown)
	                {$('#diverror').html(XMLHttpRequest.responseText);}
					});
		  		  		

			}
	  	window.onload = actualizar;
	  </script>	  		
	</head>
	<body>		
		<div id="division">
			<h3>SEM&Aacute;FORO DE CONTROL</h3>
			<h4>ESTATUS DE ACTUALIZACI&Oacute;N POR INDICADOR</h4>
			<label for="dependencias">Dependencia: ${dependencia?.descripcion}</label>			
			
			 <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_NUCLEO">
			
			<g:select name="dependencias" optionKey="id" optionValue="descripcion" from="${Dependencia.list()}"		 
			onchange="${ remoteFunction (
				controller:'indicador', 
				action:'actualizarSemaforo', 
				params: '\'id=\' + this.value',
				update:'semaforo')}"/>
				<g:hiddenField name="rol" value="1"/>
			</sec:ifAnyGranted>
			 <sec:ifAnyGranted roles="ROLE_DEP">
 		 
 		 	<g:hiddenField name="dependencias" value="${dependencia?.id}"/>
 		 	
 		 </sec:ifAnyGranted>
			<div>
			1.- La informaci&oacute;n se encuentra actualizada<br>
			2.- La informaci&oacute;n necesita ser revalidada y/o actualizada<br><br>
			</div>
			<div id="semaforo"></div>
		</div>
	</body>
</html>


			
