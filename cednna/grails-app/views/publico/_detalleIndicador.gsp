<head>	  
	  <script>
	  $(function() {
	    $( "#tabs" ).tabs();
	  });
	  </script>
	  
	  
		<script type="text/javascript">
$(function () {
        $('#container').highcharts(
            ${tablaJSON});
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