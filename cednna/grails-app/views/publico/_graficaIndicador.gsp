<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<script type="text/javascript">
$(function () {
	$('#container').highcharts(
            ${tablaJSON});
});
</script>	
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>