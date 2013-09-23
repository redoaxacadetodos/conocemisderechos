<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!-- Google Maps -->
	  
	  	<script>
	  	var geocoder;
	  	var map;
	  	var coordendasList;
	  	var infoWindow;
	  	var nombreCoordenadas;
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
	coordendasList = ${coordenadasList ? coordenadasList:'[]' };
	nombreCoordenadas = ${nombreCoordenadas ? nombreCoordenadas:'[]'};

	$.each(coordendasList, function(index, value){
		
		var coordenadas = value;	
		coordenadas.nombre = nombreCoordenadas[index];
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
			var contentString = '<b>'+coordenadas.nombre+'</b><br>';	  			  
			// Replace our Info Window's content and position
			infoWindow.setContent(contentString);
			infoWindow.setPosition(event.latLng);
			infoWindow.open(map);
		});
		
	});		
}

function mostrarVentana(event) {

	  var contentString = '<b>Bermuda Triangle Polygon</b><br>';	  
	  
	  // Replace our Info Window's content and position
	  infoWindow.setContent(contentString);
	  infoWindow.setPosition(event.latLng);

	  infoWindow.open(map);
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
	  <div id="map-canvas" style="width: 100%; height: 480px;"></div>

</div>