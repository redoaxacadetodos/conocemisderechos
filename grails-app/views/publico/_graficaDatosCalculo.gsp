<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>

<g:if test="${tablaJSON }"> 
	<script type="text/javascript">
	$(function () {
		Highcharts.setOptions({
			lang: {
				contextButtonTitle: "Guardar gráfica",
				downloadJPEG: "Descargar imagen JPEG",
				downloadPNG: "Descargar imagen PNG",
				downloadSVG: "Descargar vector SVG",
				downloadPDF: "Descargar documento PDF",
				printChart: "Imprimir Gráfica"
			}
		});
		
		var json = ${tablaJSON};
		json.title = {"text":"Gráfica a nivel: ${nivel}","x":-20};
		json.exporting = {
            buttons: {
                contextButton: {
                    text: 'Guardar gráfica'
                }
            }
        };
		
		$('#divGraficaDatosCalculo').highcharts(json);
	});
	</script>	
	<div id="divGraficaDatosCalculo" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
</g:if>
<g:else>
	<br>
	No existen valores para mostrar la gráfica a este nivel de área geográfica.
	<br><br><br>
</g:else>