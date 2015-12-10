
<export:formats formats="['csv', 'excel', 'pdf']" action="descargarSerieHistorica" id="id" params="[idTipo:tipo, id:id]" />
<table class='table table-striped table-hover table-bordered' id='${tabla }'></table>

<script>
$('#${tabla}').dataTable( ${json} );
$('#${tabla}_filter input').addClass('form-control medium mayus');
</script>