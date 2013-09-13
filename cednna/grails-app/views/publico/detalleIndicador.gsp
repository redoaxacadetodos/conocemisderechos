<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	  
	  <script type="text/javascript" src="http://latex.codecogs.com/latexit.js"></script>
						
		<link href="${resource(dir: 'bootstrap', file: 'css/bootstrap.min.css')}" rel="stylesheet">		
		
		
	  
	</head>
	<body>
	
<div class="uk-grid">
<div class="uk-width-1-1">
<div id="menubloque" style="padding-left:25px;">
	<g:each var="eje" in="${mx.gob.redoaxaca.cednna.domino.Eje.list()}">
		<a href="#" onclick="${remoteFunction(
			controller:'publico',
			action: 'infoIndicador',
			update: 'division',
			id: eje.id)}" value="${eje.descripcion}" id="${eje.id}" class="men${eje.id} botmenubloque"></a>
		
					
	</g:each>
	</div>	
	
</div>
</div>
	
	
	  
<div id="division" class="mascara">
	<g:if test="${divisiones!=null}">
		<g:render template="division"></g:render>
	</g:if>
	<g:else>
		<g:render template="detalleIndicador"></g:render>
	</g:else>
			
</div>	
 <script src="${resource(dir: 'bootstrap', file: 'js/bootstrap.min.js')}"></script>
</body>
</html>

	  