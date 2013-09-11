<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	  
	  <script type="text/javascript" src="http://latex.codecogs.com/latexit.js"></script>
						
		<link href="${resource(dir: 'bootstrap', file: 'css/bootstrap.min.css')}" rel="stylesheet">		
		
		<script>
	  $(function () {
		  $( "#tabs" ).tabs();
	        $('#container').highcharts(
	            ${tablaJSON});
	        $("#tabs").tabs({
	            show: function(e, ui) {
	                
		                alert('prueba');
	                    google.maps.event.trigger(map, "resize");
	                
	            }
	        });
	        $('#myTab a').click(function (e) {
	      	  e.preventDefault();
	      	  $(this).tab('show');
	      	})
	    });

	
	  </script>
	  
	  <!-- Google Maps -->
	  
	  	<script>
	  	var geocoder;
	  	var map;
function initialize() {
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(17.05, -96.72);
  var mapOptions = {
    zoom: 7,
    center: latlng,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
    
	var ubicaciones = ${ubicaciones};
	
	for(i=0; i<ubicaciones.length; i++){
	var address = ubicaciones[i] + " oaxaca";
    geocoder.geocode( { 'address': address}, function(results, status) {
      if (status == google.maps.GeocoderStatus.OK) {
        map.setCenter(results[0].geometry.location);
        var marker = new google.maps.Marker({
            map: map,
            position: results[0].geometry.location
        });
      } 
    });
	}
	alert(ubicaciones.lenght());
}

function loadScript() {
  var script = document.createElement('script');
  script.type = 'text/javascript';
  script.src = 'https://maps.googleapis.com/maps/api/js?key=AIzaSyDGVv816kgzORqTzz8IQjaBS0GW-QTr4hI&sensor=false&' +
      'callback=initialize';
  document.body.appendChild(script);
}

//window.onload = loadScript;



    </script>
	  <!-- Termina Google Maps -->
	  
	</head>
	<body>
	  
<div id="division">
	<h3>${indicadorInstance?.nombre }</h3>
	<ul class="nav nav-tabs" id="myTab">
	  <li class="active"><a href="#indicador">Indicador</a></li>
	  <li><a href="#metadato">Metadato</a></li>
	  <li><a href="#serie">Serie histórica</a></li>
	  <li><a href="#calculo">Datos para el cálculo</a></li>
	  <li><a onclick="loadScript();" href="#mapa">Mapa</a></li>
	</ul>
	 
	<div class="tab-content">
	  <div class="tab-pane active" id="indicador">
	  	<div>
			  <!-- Tabla indicador general -->
			  <table>
				  	<caption>${indicadorInstance?.nombre }</caption>
				  	<thead>
				  		<tr><th>Periodo </th><th>Total</th></tr>
				  	</thead>
				  	<tbody>
					  	<g:each var="resultado" in="${resultados }">
					  	<tr><td>${resultado?.anio }</td><td>${resultado?.indicador }</td></tr>
					  	</g:each>
				  	</tbody>
			  	</table>
			  	<!-- Termina tabla indicador general -->
			  	<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	  	
				<!-- Tabla de variación -->
				<table style="text-align: center">
			  		<tr>
			  			<th rowspan="2" valign="middle">Indicador</th>
			  			<th colspan="${resultados.size()-1}">Variación</th>			  			
			  		</tr>
			  		<tr>			  			
			  			<g:set value="false" var="imprimir"></g:set>
			  			<g:each var="resultado" in="${resultados}">
			  				<g:if test="${imprimir=='true'}">
			  					<th>${anio}/${resultado?.anio}</th>
			  					<g:set value="${resultado?.anio}" var="anio"></g:set>			  					
			  				</g:if>
			  				<g:else>
			  					<g:set value="${resultado?.anio}" var="anio"></g:set>
			  					<g:set value="true" var="imprimir"></g:set>
			  				</g:else>
					  	</g:each>				  		
			  		</tr>
			  		<tr>
			  			<g:set value="false" var="imprimirDatos"></g:set>
			  			<td>${indicadorInstance?.nombre}</td>
				  		<g:each var="resultado" in="${resultados}">
					  		<g:if test="${imprimirDatos=='true'}">
			  					<td>${Math.round( (resultado?.indicador-anio) * 100.0 ) / 100.0} puntos</td>
			  					<g:set value="${resultado?.indicador}" var="anio"></g:set>			  					
			  				</g:if>
			  				<g:else>
			  					<g:set value="${resultado?.indicador}" var="anio"></g:set>
			  					<g:set value="true" var="imprimirDatos"></g:set>
			  				</g:else>
					  	</g:each>	
			  		</tr>
			  	</table>
			  	<!-- termina tabla de variación -->
	  	
	  </div>
	  <div class="tab-pane" id="metadato">
	  	<table>
	  	<tbody>
	  		<tr><td>Nombre del indicador:</td><td>${indicadorInstance?.nombre }</td></tr>
	  		<tr><td>Objetivo del indicador:</td><td>${indicadorInstance?.objetivo }</td></tr>
	  		<tr><td>Dependencia Responsable:</td><td>${indicadorInstance?.dependencia?.descripcion }</td></tr>
	  		<tr><td>Unidad Administrativa Ejecutora:</td><td>${indicadorInstance?.ejecutora?.descripcion }</td></tr>
	  		<tr><td>Sentido esperado:</td><td>${indicadorInstance?.sentido?.descripcion }</td></tr>
	  		<tr><td>Frecuencia de medición:</td><td>${indicadorInstance?.frecuencia?.descripcion }</td></tr>	  			  	
	  		
	  		<tr><td>Fórmula de cálculo:</td>
	  		<td>
	  		<p lang="latex">$${indicadorInstance.formula?.sentencia}$</p> 
	  		<p>${indicadorInstance.formula?.descripcion}</p>
	  		</td></tr>
	  		
	  		<tr><td>Medios de verificación:</td><td>${indicadorInstance?.mediosVerificacion }</td></tr>
	  		<tr><td>Comentarios técnicos:</td><td>${indicadorInstance?.comentarios }</td></tr>
	  		<tr><td>Fecha de actualización:</td><td>${indicadorInstance?.fechaActualizacion }</td></tr>	  		  		
	  	</tbody>
	  	</table>
	  </div>
	  <div class="tab-pane" id="serie"></div>
	  <div class="tab-pane" id="calculo"></div>
	  <div class="tab-pane" id="mapa"><div id="map-canvas" style="width: 100%; height: 480px;"></div></div>
	</div>	 	

	
	<script src="${resource(dir: 'js', file: 'highcharts/js/highcharts.js')}"  type="text/javascript" charset="utf-8"></script>
	  	<script src="${resource(dir: 'js', file: 'highcharts/js/modules/exporting.js')}"  type="text/javascript" charset="utf-8"></script>		
</div>	
 <script src="${resource(dir: 'bootstrap', file: 'js/bootstrap.min.js')}"></script>
</body>
</html>

	  