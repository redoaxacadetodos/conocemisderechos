
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
<head>
<meta name="layout" content="main">
<g:set var="entityName"
	value="${message(code: 'variable.label', default: 'Variable')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'font-awesome.min.css')}" type="text/css">
<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
<g:javascript src="dataTables-1.10.7.js" />
<g:hiddenField name="lectura" value="2" />

<sec:ifAnyGranted roles="ROLE_DEP">
	<script type="text/javascript">
		$("#lectura").val("0");
	</script>
</sec:ifAnyGranted>
<sec:ifAnyGranted roles="ROLE_ADMIN">
	<script type="text/javascript">
		$("#lectura").val("1");
	</script>
</sec:ifAnyGranted>
	<script type="text/javascript">
		function eliminarVariable(id){
			if(confirm("¿Está seguro que desea eliminar la variable?")){
				jQuery.ajax({
					type:'POST', 
					url:CONTEXT_ROOT +"/variable/delete/"+id,
					success:function(data,textStatus){
						document.location.href = CONTEXT_ROOT + "/variable/list";
					},
					error:function(XMLHttpRequest,textStatus,errorThrown){}
				});
			}
		}
		
		function muestraBoton(source, type, val){
			if($("#lectura").val()==0)
				return "<a href='"+ CONTEXT_ROOT+"/variable/edit/"+ source[0] + "'  class='uk-icon-button uk-icon-edit' title='Editar'/> ";
			else if($("#lectura").val()==1){
				var eliminar = "<a id='"+source[0]+"' class='uk-icon-button uk-icon-trash-o' title='Clic para eliminar registro' onclick=\"eliminarVariable("+source[0]+");return false;\" href='#' ></a>";
				return "<a href='"+ CONTEXT_ROOT+"/variable/edit/"+ source[0] + "'  class='uk-icon-button uk-icon-edit' title='Editar'/> " + eliminar;
			}else 
				return "<a href='"+CONTEXT_ROOT+"/variable/show/"+ source[0] + "'  class='uk-icon-button uk-icon-search' title='Ver detalle'/> ";
		}
	</script>

	<script type="text/javascript" charset="utf&minus;8">
		$(document).ready(function(){
			
		    
			var tablaVariable = $('#tablaVariables').DataTable({
				"orderCellsTop": true,
				"sDom": '<"top"l>rt<"bottom"ip><"clear">',	
				"bProcessing": true,
			    "bServerSide": true,
			    "sAjaxSource": "<g:createLink controller='variable' action='getTablaDatosEstadisticos'/>",
			    "oLanguage": {
			    	"sProcessing":     "Procesando...",
			        "sLengthMenu":     "Mostrar _MENU_ registros",
			        "sZeroRecords":    "No se encontraron resultados",
			        "sEmptyTable":     "Ning&uacute;n dato disponible en esta tabla",
			        "sInfo":           "Mostrando registros del _START_ al _END_ de un total de _TOTAL_ registros",
			        "sInfoEmpty":      "Mostrando registros del 0 al 0 de un total de 0 registros",
			        "sInfoFiltered":   "(filtrado de un total de _MAX_ registros)",
			        "sInfoPostFix":    "",
			        "sSearch":         "Buscar:",
			        "sUrl":            "",
			        "sInfoThousands":  ",",
			        "sLoadingRecords": "Cargando...",
			        "oPaginate": {
			            "sFirst":    "Primero",
			            "sLast":     "&Uacute;ltimo",
			            "sNext":     "Siguiente",
			            "sPrevious": "Anterior"
			        },
			        "oAria": {
			            "sSortAscending":  ": Activar para ordenar la columna de manera ascendente",
			            "sSortDescending": ": Activar para ordenar la columna de manera descendente"
			        }
		    	},
				"aoColumns": [
					{ "sTitle": "Id", bVisible: false },
					{ "sTitle": "Clave" },
					{ "sTitle": "Descripción" },
					{ "sTitle": "Región" },
					{ "sTitle": "Municipio" },
					{ "sTitle": "Categoría" },
					{ "sTitle": "Año/Ciclo" },
					{ "sTitle": "Población total" },
					{ "sTitle": "Mujeres" },
					{ "sTitle": "Hombres" },
					{ "sTitle": "Opciones", mData:muestraBoton }
				],
				"aoColumnDefs": [
					{ 'bSortable': false, 'aTargets': [ 10 ] }
				],
				"aaSorting": [[ 0, "asc" ]]
				
			});

			$('#tablaVariables thead tr#filterrow th').each( function () {
		        var title = $('#tablaVariables thead th').eq( $(this).index() ).text();
		        if(title != ""){
		        	$(this).html( '<input class="label-foot-de" type="text" placeholder=" '+title+'" />' );
			    }
		        
		    });

			$("#tablaVariables thead input").on( 'keyup change', function () {
				tablaVariable
		            .column( $(this).parent().index()+':visible' )
		            .search( this.value )
		            .draw();
		    } );
		});
	</script>
</head>
<body>
	<a href="#list-variable" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
			<li class="uk-active"><g:link class="list" action="list">Datos estad&iacute;sticos </g:link></li>
			<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_DEP">
				<li><g:link class="create" action="create">Agregar datos</g:link></li>
				<li><g:link class="create" action="archivo">Subir desde archivo</g:link></li>	
			</sec:ifAnyGranted>
		</ul>	
	</nav>

	<br>
	<br>
	<div class="body">
		<div id="divVariables" class="uk-overflow-container">
			<table class='table table-striped table-hover table-bordered nuevocolortabla' id='tablaVariables'>
				
				<thead>
		            <tr>
		            	<th>Id</th>
		                <th>Clave</th>
		                <th>Descripción</th>
		                <th>Región</th>
		                <th>Municipio</th>
		                <th>Categoría</th>
		                <th>Año/Ciclo</th>
		                <th>Población total</th>
		                <th>Mujeres</th>
		                <th>Hombres</th>
		                <th>Opciones</th>
		            </tr>
		            <tr id="filterrow">
		            	<th>Id</th>
		                <th>Clave</th>
		                <th>Descripción</th>
		                <th>Región</th>
		                <th>Municipio</th>
		                <th>Categoría</th>
		                <th>Año/Ciclo</th>
		                <th>Población total</th>
		                <th>Mujeres</th>
		                <th>Hombres</th>
		                <th>Opciones</th>
		            </tr>
		        </thead>
			</table>
		</div>
	</div>
</body>
</html>
