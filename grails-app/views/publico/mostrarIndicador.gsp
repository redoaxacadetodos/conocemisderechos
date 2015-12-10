<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
<head>		
	<meta name="layout" content="public">
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
    
	<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
	<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
	<link rel="stylesheet" type="text/css" href="${resource(dir: 'css', file: 'dataTables.tableTools.css')}">
    
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    <g:javascript src="themaGraficas.js"/>
	  
    <script type="text/javascript" src="http://latex.codecogs.com/latexit.js"></script>
    <g:javascript src="dataTables-1.10.7.js" />
    <r:require module="export"/>

	<g:javascript src="dataTables.tableTools.min.js"/>
	<link href="${resource(dir: 'bootstrap', file: 'css/bootstrap.min.css')}" rel="stylesheet">		
	<script type="text/javascript">
		
		function addCommas(source, type, val) {
			nStr = source[3];
			if (nStr==undefined)
				return 0;
			nStr += '';
			x = nStr.split('.');
			x1 = x[0];
			x2 = x.length > 1 ? '.' + x[1] : '';
			var rgx = /(\d+)(\d{3})/;
			while (rgx.test(x1)) {
				x1 = x1.replace(rgx, '$1' + ',' + '$2');
			}
			return x1 + x2;
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
						<li><span> <g:link action="detalleIndicador"
									controller="publico" id="${ejeInstance?.id}">
									${ejeInstance?.descripcion }
								</g:link></span></li>
						<li class="uk-active"><span>${nombreIndicador }</span></li>
					</ul>
				</div>
			</div>
		</div>
	</div>


	<g:render template="redes"></g:render>


	<div id="division" class="mascara">
		<g:render template="detalleIndicador"></g:render>
	</div>	
	
 <script src="${resource(dir: 'bootstrap', file: 'js/bootstrap.min.js')}"></script>
</body>
</html>

	  