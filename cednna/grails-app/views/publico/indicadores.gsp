
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.min.css')}" type="text/css"/>
		<!-- Almost Flat style -->
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.almost-flat.min.css')}" type="text/css"/>
	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	  <script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>
		<script type="text/javascript" src="http://latex.codecogs.com/latexit.js"></script>

		<script src="https://code.jquery.com/jquery-1.10.2.min.js"></script>

			<g:javascript src="uikit.min.js"/>
			
		<link rel="stylesheet" href="css/prettyPhoto.css" type="text/css" media="screen" charset="utf-8" />
		<script src="js/jquery.prettyPhoto.js" type="text/javascript" charset="utf-8"></script>	
		<g:javascript src="jquery.dataTables.js" />
		<script type="text/javascript" charset="utf-8">
		  $(document).ready(function(){
		    $("a[rel^='intro']").prettyPhoto({
		    	theme: 'dark_rounded',
		    	modal: true,
		    	keyboard_shortcuts: false,
		    	social_tools:false
			});
		    $("#linkyoutube").click();

		    $('#buscador').dataTable({	
				"bProcessing": true,
			    "bServerSide": true,
			    "sAjaxSource": "<g:createLink controller='publico' action='getTablaBuscador' />",
			    "oLanguage": {
			    	  "sUrl": "datatables/language/spanish.txt"
			    	},
				"aoColumns": [
					{ "sTitle": "Módulo" },
					{ "sTitle": "Sección" },
					{ "sTitle": "Indicador" },
					{ "sTitle": "Ver indicador" }
				],
				"aoColumnDefs": [
				    { 'bSortable': false, 'aTargets': [ 3 ] }
				],
				"aaSorting": [[ 1, "desc" ]]
				
			});

		    
		  });

		  function buscar(id, ejeid){
			  $.ajax( {
				    "url": "<g:createLink controller='publico' action='mostrarIndicador' />" + "/"+ id+"?ejeInstance=" + ejeid,
				    "success": function ( json ) {
				    	
				    }
				} );
		  }

		</script>
				
	</head>
	<body>
	
		<div class="uk-container uk-container-center">
		<div class="uk-panel-space uk-text-center uk-container-center"><img src="${request.getContextPath()}/img/logo_sist.png"  title="CEDNNA" alt="CEDNNA" witdh="283px" height="215px"></div>
		</div>
		
		<!-- This is the modal -->
		<div id="divBuscador" class="uk-modal">
			<div class="uk-modal-dialog">
			    <a class="uk-modal-close uk-close"></a>
			    <div class="uk-overflow-container">
			    	<table class="table table-striped table-hover table-bordered" id="buscador"></table>
			    </div>
			</div>
		</div>
		
<div class="uk-container uk-container-center">	
	<button class="uk-button uk-button-primary uk-button-large uk-width-1-1" data-uk-modal="{target:'#divBuscador'}">¿Qué indicador buscas?</button>
	<div class="uk-grid" data-uk-grid-margin="">
		<g:each var="eje" in="${mx.gob.redoaxaca.cednna.domino.Eje.list()}">
			<div id="bot_prog" class="uk-width-medium-1-4">
				<div class="uk-panel">
					<g:link action="detalleIndicador" title="${eje?.titulo }" controller="publico"  value="${eje?.descripcion}" id="${eje?.id}">
					<div class="bot${eje.id}"></div>
					</g:link>			
				</div>
			</div>
			
		</g:each>
	</div>
</div>
	
<a id="linkyoutube" href="${urlvideo}" rel="intro" title=""></a>




		
		<script src="${resource(dir: 'js', file: 'highcharts/js/highcharts.js')}"  type="text/javascript" charset="utf-8"></script>
	  	<script src="${resource(dir: 'js', file: 'highcharts/js/modules/exporting.js')}"  type="text/javascript" charset="utf-8"></script>
	  	
	
	</body>
</html>


			
