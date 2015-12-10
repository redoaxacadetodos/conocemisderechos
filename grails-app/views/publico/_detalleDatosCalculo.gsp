<div>
<export:formats formats="['csv', 'excel', 'pdf']" action="descargarDetalleDatosCalculo" id="id" params="[areaGeografica:areaGeografica, id:idIndicador, variable:variable, idCategoria:idCategoria]" />
<table id="tableDetalleDatosCalculo" class='table table-striped table-hover table-bordered'>
<thead>
	<tr>
		<th>Descripción</th>
		<g:if test="${areaGeografica.equals('1') }">
			<th>Entidad Federativa</th>
		</g:if>
		<g:elseif test="${areaGeografica.equals('2') }">
			<th>Región</th>
		</g:elseif>
		<th>Municipio</th>
		<th>Categoría</th>
		<th>Año</th>
		<th>Total</th>
		<th>Hombres</th>
		<th>Mujeres</th>
	</tr>
	<tr id="filterrow">
		<th class="filterrow"></th>
		<g:if test="${areaGeografica.equals('1') }">
			<th class="filterrow"></th>
		</g:if>
		<g:elseif test="${areaGeografica.equals('2') }">
			<th class="filterrow"></th>
		</g:elseif>
		<th class="filterrow">Municipio</th>
		<th class="filterrow">Categoría</th>
		<th class="filterrow">Año</th>
		<th class="filterrow"></th>
		<th class="filterrow"></th>
		<th class="filterrow"></th>
	</tr>
</thead>
<tbody>
</tbody>
</table>

</div>
<script>
$(document).ready(function(){
	var tablaDatosCalculo =  $('#tableDetalleDatosCalculo').DataTable( {
		"orderCellsTop": true,
		"sDom": '<"top"l>rt<"bottom"ip><"clear">',	
        "bServerSide":true,
        "bProcessing":true,
        "sAjaxSource":"<g:createLink controller='publico' action='getDetalleDatosCalculo' id='${idIndicador}' />",
        "fnServerParams": function ( aoData ) {
            aoData.push( { "name": "idCategoria", "value": ${idCategoria} } );
            aoData.push( { "name": "variable", "value": ${variable} } );
            aoData.push( { "name": "areaGeografica", "value": ${areaGeografica} } );
          },
    	"bDestroy":true,
    	"bRetrieve":true,
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
    	}
    });

	$('#tableDetalleDatosCalculo thead tr#filterrow th').each( function () {
        var title = $('#tableDetalleDatosCalculo thead th').eq( $(this).index() ).text();
        if(title == "Municipio"){
        	$(this).html( '<input class="label-foot-de filterrow-input" type="text" placeholder=" '+title+'" />' );
	    }
        if(title == "Categoría"){
        	$(this).html( '<input class="label-foot-de filterrow-input" type="text" placeholder=" '+title+'" />' );
	    }
        if(title == "Año"){
        	$(this).html( '<input class="label-foot-de filterrow-input" type="text" placeholder=" '+title+'" />' );
	    }
        
    });

	$("#tableDetalleDatosCalculo thead input").on( 'keyup change', function () {
		tablaDatosCalculo
            .column( $(this).parent().index()+':visible' )
            .search( this.value )
            .draw();
    } );
});
</script>