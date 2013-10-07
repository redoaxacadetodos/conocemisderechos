<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!-- Google Maps -->
	  
	  	<script>
	  	var geocoder;
	  	var map;
	  	var coordendasList;
	  	var infoWindow;

function initialize() {
	geocoder = new google.maps.Geocoder();
	var latlng = new google.maps.LatLng(17.05, -96.72);
  var mapOptions = {
    zoom: 7,
    center: latlng,
    disableDefaultUI: true,
    mapTypeId: google.maps.MapTypeId.ROADMAP
  };

  map = new google.maps.Map(document.getElementById('map-canvas'),
      mapOptions);
    
	coordendasList = ${coordenadasList ? coordenadasList:'[]' };	

	var aux = ${aux ? aux:'[]'};
	$.each(aux['lugar']['ubicaciones'], function(k, v){
	
		var coordenadas = coordendasList[k];

		var p = [new google.maps.LatLng(17.248596,-97.41013800000002),new google.maps.LatLng(17.238701,-97.476759),new google.maps.LatLng(17.235694,-97.483396)];		
		
		coordenadas.nombre = v['descripcion'];

		for(var i in v['datos']){
			//alert(v['datos'][i]);
		}
		
		ubicacion = new google.maps.Polygon({
			paths: coordenadas,
			strokeColor: '#FF0000',
			strokeOpacity: 0.8,
			strokeWeight: 2,
			fillColor: '#FF0000',
			fillOpacity: 0.35
			});

		ubicacion.setMap(map);
		infoWindow = new google.maps.InfoWindow();
		
		google.maps.event.addListener(ubicacion, 'click', function(event) {
			var contentString = '<b>'+coordenadas.nombre+'</b><br><br>';
			contentString += '<table><thead><th>Año</th><th>Indicador</th></thead><tbody>';	
			for(var i in v['anios']){				
				contentString +="<tr><td>"+v['anios'][i]+"</td><td>"+v['datos'][i]+"</td></tr>";
			}  	
			contentString +="</tbody></table>";		  
			// Replace our Info Window's content and position
			infoWindow.setContent(contentString);
			infoWindow.setPosition(event.latLng);
			infoWindow.open(map);
		});
	});			
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
	  
<div>		
	<g:if test="${coordenadasList}">
	  	<div id="map-canvas" style="width: 100%; height: 480px;"></div>
	</g:if>
	<g:else>
		No se pueden mostrar los indicadores a este nivel 
		<div id="map-canvas" style="width: 100%; height: 480px; display:none;"></div>
	</g:else>	  	  
</div>
