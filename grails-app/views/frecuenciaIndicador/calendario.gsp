
<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Dependencia" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'frecuenciaIndicador.label', default: 'FrecuenciaIndicador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'fullcalendar.print.css')}" type="text/css" media='print'>
		
		<g:javascript src="moment.js"/>
		<g:javascript src="fullcalendar.js" />
		<g:javascript src="lang_fullcalendar/es.js" />
		
		<script type="text/javascript">
	  	function actualizar(){	  	
		  	$.ajax({type:'POST', 
	            url:CONTEXT_ROOT+'/frecuenciaIndicador/actualizarCalendario',
	            data: "id="+$("#dependencias").val()+"&rol="+$("#rol").val(),
	            success:function(data,textStatus){
	            	$('#calendario').html(data);
	            },
	            error:function(XMLHttpRequest,textStatus,errorThrown)
	                {}
		  	});
		}
		
	  	window.onload = actualizar;
	  </script>	 
	</head>
	<body>
		<a href="#list-frecuenciaIndicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		
		<label for="dependencias">Dependencia: ${dependencia?.descripcion}</label>			
			
		<sec:ifAnyGranted roles="ROLE_ADMIN">
			<g:select id="dependencias" name="dependencias" optionKey="id" optionValue="descripcion" onchange="actualizar();" from="${Dependencia.list()}"/>
			<g:hiddenField name="rol" value="1"/>
		</sec:ifAnyGranted>
			
		<sec:ifAnyGranted roles="ROLE_DEP,ROLE_LECTURA,ROLE_NUCLEO">
 		 	<g:hiddenField name="dependencias" value="${dependencia?.id}"/>
 		 </sec:ifAnyGranted>
	 		 
		<div id='calendario'></div>
	</body>
</html>
