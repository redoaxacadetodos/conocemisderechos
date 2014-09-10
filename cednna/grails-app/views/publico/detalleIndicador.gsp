<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>

	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	  
	  <script type="text/javascript" src="http://latex.codecogs.com/latexit.js"></script>
						
		<link href="${resource(dir: 'bootstrap', file: 'css/bootstrap.min.css')}" rel="stylesheet">		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<g:javascript src="jquery.dataTables.js" />
		<script type="text/javascript">
			function descargar(source, type, val){
				return "<div class='centrado'><a href='#' onclick=descargarDocumento("+source[3]+","+source[2]+",'"+source[4]+"')><i class='uk-icon-download uk-icon-small'></i></a></div>";
			}

			function descargarDocumento(tipo, nivel, documento){
				$("#nivel").val(nivel);
				$("#tipo").val(tipo);
				$("#documento").val(documento);
				$("#formDocumento").submit();
			}
		</script>
	</head>
	<body>
	
<div class="uk-grid">
<div class="uk-width-1-1">

<div class="tm-middle">
<div class="uk-panel uk-panel-box">
<ul class="uk-breadcrumb">
    <li><span><a href="${createLink(controller:'publico', action:'indicadores')}">Inicio</a></span></li>
    <li class="uk-active"><span>${ejeInstance?.descripcion }</span></li>
</ul>
</div>
</div>	
	
</div>
</div>
	
	
	  
<div id="division" class="mascara">
	<g:render template="division"></g:render>
</div>	
 <script src="${resource(dir: 'bootstrap', file: 'js/bootstrap.min.js')}"></script>
</body>
</html>

	  