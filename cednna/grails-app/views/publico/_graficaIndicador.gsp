<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>

<g:if test="${tablaJSON }"> 

<script type="text/javascript">
$(function () {
	$('#container').highcharts(
            ${tablaJSON});
});
</script>	
<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</g:if>
<g:else>
<br>
No existen valores para mostrar la gráfica a este nivel de área geográfica.
<br><br><br>
</g:else>