<head>	  
	  <script>
	  $(function() {
	    $( "#tabs" ).tabs();
	  });
	  </script>
	  
	  
		<script type="text/javascript">
$(function () {
        $('#container').highcharts({
            title: {
                text: 'Monthly Average Temperature',
                x: -20 //center
            },
            subtitle: {
                text: 'Source: WorldClimate.com',
                x: -20
            },
            xAxis: {
                categories: ['Jan', 'Feb', 'Mar', 'Apr', 'May', 'Jun',
                    'Jul', 'Aug', 'Sep', 'Oct', 'Nov', 'Dec']
            },
            yAxis: {
                title: {
                    text: 'Temperature (°C)'
                },
                plotLines: [{
                    value: 0,
                    width: 1,
                    color: '#808080'
                }]
            },
            tooltip: {
                valueSuffix: '°C'
            },
            legend: {
                layout: 'vertical',
                align: 'right',
                verticalAlign: 'middle',
                borderWidth: 0
            },
            series: [{
                name: 'Tokyo',
                data: [7.0, 6.9, 9.5, 14.5, 18.2, 21.5, 25.2, 26.5, 23.3, 18.3, 13.9, 9.6]
            }, {
                name: 'New York',
                data: [-0.2, 0.8, 5.7, 11.3, 17.0, 22.0, 24.8, 24.1, 20.1, 14.1, 8.6, 2.5]
            }, {
                name: 'Berlin',
                data: [-0.9, 0.6, 3.5, 8.4, 13.5, 17.0, 18.6, 17.9, 14.3, 9.0, 3.9, 1.0]
            }, {
                name: 'London',
                data: [3.9, 4.2, 5.7, 8.5, 11.9, 15.2, 17.0, 16.6, 14.2, 10.3, 6.6, 4.8]
            }]
        });
    });
    

		</script>
</head>

<div>
	<h3>${indicadorInstance?.nombre }</h3>
	<div id="tabs">
	  <ul>
	    <li><a href="#tabs-1">Indicador</a></li>
	    <li><a href="#tabs-2">Metadato</a></li>
	    <li><a href="#tabs-3">Serie histórica</a></li>
	    <li><a href="#tabs-4">Datos para el cálculo</a></li>
	    <li><a href="#tabs-5">Mapa</a></li>
	  </ul>
	  <div id="tabs-1">
	  	<!-- Tabla indicador general -->
	  	<table>
	  	<thead><tr>${indicadorInstance?.nombre }</tr></thead>
	  	<tbody>
	  	<tr><td>Periodo </td><td>Total</td></tr>
	  	<g:each var="resultado" in="${resultados }">
	  	<tr><td>${resultado?.anio }</td><td>${resultado?.indicador }</td></tr>
	  	</g:each>
	  	</tbody>
	  	</table>
	  	
		
		<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
	  	
	  	<h2>Tabla indicador estatal</h2>
	  	<table>
	  	<thead><tr>${indicadorInstance?.nombre }</tr></thead>
	  	<tbody>
	  	<tr><td>Clave</td><td>Desagregación geográfica</td><td>2012</td><td>2013 p/</td></tr>
	  	</tbody>
	  	</table>
	  	<p>
	  	<b>NOTA:</b> P/ Cifras preliminares ${new Date().month+1} 2013 <br>
		<b>FUENTE:</b> <br>
		<b>FECHA DE ACTUALIZACIÓN:</b> ${indicadorInstance?.fechaActualizacion } <br>
		<b>PRÓXIMA ACTUALIZACIÓN:</b> <br>
	  	</p>
	    
	    <h2>Tabla indicadorInstance regional</h2>
	    <table>
	  	<tbody>
	  	<tr><td>Clave</td><td>Desagregación geográfica</td><td>2012</td><td>2013 p/</td></tr>
	  	</tbody>
	  	</table>
	  	<p>
	  	<b>NOTA:</b> P/ Cifras preliminares (mes actual) 2013 <br>
		<b>FUENTE:</b> <br>
		<b>FECHA DE ACTUALIZACIÓN:</b> ${indicadorInstance?.fechaActualizacion } <br>
		<b>PRÓXIMA ACTUALIZACIÓN:</b> <br>
	  	</p>
	    
	    <h2>Tabla indicadorInstance municipal</h2>
	    <table>
	  	<tbody>
	  	<tr><td>Clave</td><td>Desagregación geográfica</td><td>2012</td><td>2013 p/</td></tr>
	  	</tbody>
	  	</table>
	  	<p>
	  	<b>NOTA:</b> P/ Cifras preliminares (mes actual) 2013 <br>
		<b>FUENTE:</b> <br>
		<b>FECHA DE ACTUALIZACIÓN:</b> ${indicadorInstance?.fechaActualizacion } <br>
		<b>PRÓXIMA ACTUALIZACIÓN:</b> <br>
	  	</p>
	    
	  </div>
	  <div id="tabs-2"> <!-- Metadato -->
	  	<table>
	  	<tbody>
	  		<tr><td>Nombre del indicador:</td><td>${indicadorInstance?.nombre }</td></tr>
	  		<tr><td>Objetivo del indicador:</td><td>${indicadorInstance?.objetivo }</td></tr>
	  		<tr><td>Dependencia Responsable:</td><td>${indicadorInstance?.dependencia?.descripcion }</td></tr>
	  		<tr><td>Unidad Administrativa Ejecutora:</td><td>${indicadorInstance?.ejecutora?.descripcion }</td></tr>
	  		<tr><td>Sentido esperado:</td><td>${indicadorInstance?.sentido?.descripcion }</td></tr>
	  		<tr><td>Frecuencia de medición:</td><td>${indicadorInstance?.frecuencia?.descripcion }</td></tr>	  		
	  		<tr><td>Fórmula de cálculo:</td><td>${indicadorInstance.formula?.descripcion }</td></tr>
	  		<tr><td>Medios de verificación:</td><td>${indicadorInstance?.mediosVerificacion }</td></tr>
	  		<tr><td>Comentarios técnicos:</td><td>${indicadorInstance?.comentarios }</td></tr>
	  		<tr><td>Fecha de actualización:</td><td>${indicadorInstance?.fechaActualizacion }</td></tr>	  		  		
	  	</tbody>
	  	</table>	    
	  </div>
	  <div id="tabs-3"> <!-- Serie histórica -->
	    <table>
	  	<thead><tr>${indicadorInstance?.nombre }</tr></thead>
	  	<tbody>
	  	<tr><td>Clave</td><td>Desagregación geográfica</td><td>2013 p/</td><td>2012</td><td>2011</td><td>2010</td></tr>
	  	</tbody>
	  	</table>
	  </div>
	  <div id="tabs-4"> <!-- Datos para el cálculo -->
	    <table>
	  	<thead><tr>${indicadorInstance?.nombre }</tr></thead>
	  	<tbody>
	  	<tr><td>Municipio/Población rural y urbana</td><td>Total</td><td>Hombre</td><td>Mujer</td></tr>	  	
	  	</tbody>
	  	</table>
	  	<p><b>FUENTE:</b> <br></p>
	  	
	  	<table>
	  	<thead><tr>${indicadorInstance?.nombre }</tr></thead>
	  	<tbody>
	  	<tr><td>Municipio/Población rural y urbana</td><td>Total</td><td>Hombre</td><td>Mujer</td></tr>	  	
	  	</tbody>
	  	</table>
	  	<p><b>FUENTE:</b> <br></p>
	  	
	  </div>
	  <div id="tabs-5"> <!-- Mapa -->
	    <p>Mauris eleifend est et turpis. Duis id erat. Suspendisse potenti. Aliquam vulputate, pede vel vehicula accumsan, mi neque rutrum erat, eu congue orci lorem eget lorem. Vestibulum non ante. Class aptent taciti sociosqu ad litora torquent per conubia nostra, per inceptos himenaeos. Fusce sodales. Quisque eu urna vel enim commodo pellentesque. Praesent eu risus hendrerit ligula tempus pretium. Curabitur lorem enim, pretium nec, feugiat nec, luctus a, lacus.</p>
	    <p>Duis cursus. Maecenas ligula eros, blandit nec, pharetra at, semper at, magna. Nullam ac lacus. Nulla facilisi. Praesent viverra justo vitae neque. Praesent blandit adipiscing velit. Suspendisse potenti. Donec mattis, pede vel pharetra blandit, magna ligula faucibus eros, id euismod lacus dolor eget odio. Nam scelerisque. Donec non libero sed nulla mattis commodo. Ut sagittis. Donec nisi lectus, feugiat porttitor, tempor ac, tempor vitae, pede. Aenean vehicula velit eu tellus interdum rutrum. Maecenas commodo. Pellentesque nec elit. Fusce in lacus. Vivamus a libero vitae lectus hendrerit hendrerit.</p>
	  </div>
	  
	</div>		
</div>