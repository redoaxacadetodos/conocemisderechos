
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>	  
		<script type="text/javascript" src="http://latex.codecogs.com/latexit.js"></script>
	</head>
	<body>
	
	<div class="titulo">Sistema de Indicadores y Monitoreo del estado que guardan los Derechos de Niñas, Niños y Adolescentes en Oaxaca.</div>
<div id="dashboard">
	<g:each var="eje" in="${mx.gob.redoaxaca.cednna.domino.Eje.list()}">

		<div id="bot_prog">
			<g:link action="detalleIndicador" params="[infoIndicador: 'true']" controller="publico"  value="${eje.descripcion}" id="${eje.id}" class="bot${eje.id}"></g:link>			
		</div>
					
	</g:each>

</div>
	
		


		
		<script src="${resource(dir: 'js', file: 'highcharts/js/highcharts.js')}"  type="text/javascript" charset="utf-8"></script>
	  	<script src="${resource(dir: 'js', file: 'highcharts/js/modules/exporting.js')}"  type="text/javascript" charset="utf-8"></script>
	  	
	</body>
</html>


			
