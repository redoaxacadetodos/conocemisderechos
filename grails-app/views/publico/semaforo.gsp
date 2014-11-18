
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
		  	var id = $('select[name="dependencias"]').val();		  		
	  		${ remoteFunction (
					controller:'indicador', 
					action:'actualizarSemaforo', 
					params: '\'id=\' + id',
					update:'semaforo')}
			}
	  	window.onload = actualizar;
	  </script>	  	
	  <link href="${resource(dir: 'bootstrap', file: 'css/bootstrap.min.css')}" rel="stylesheet">	
	</head>
	<body>		
		<div id="division">
			<h3>SEMÁFORO DE CONTROL</h3>
			<h4>ESTATUS DE ACTUALIZACIÓN POR INDICADOR</h4>
			<label for="dependencias">Dependencia: </label>			
			<g:select name="dependencias" optionKey="id" optionValue="descripcion" from="${Dependencia.list()}"		 
			onchange="${ remoteFunction (
				controller:'indicador', 
				action:'actualizarSemaforo', 
				params: '\'id=\' + this.value',
				update:'semaforo')}"/>
				
			<div>
			<i class="icon-ok"></i> La información se encuentra actualizada<br>
			<i class="icon-remove"></i> La información necesita ser revalidada y/o actualizada<br><br>
			</div>
			<div id="semaforo"></div>
		</div>
		<script src="${resource(dir: 'bootstrap', file: 'js/bootstrap.min.js')}"></script>
	</body>
</html>


			
