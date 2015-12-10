<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>

	  
<div>		
	<g:if test="${coordenadasList}">
		<!-- Google Maps -->
	  
	 	<script type="text/javascript"
	      src="https://maps.googleapis.com/maps/api/js?key=AIzaSyDGVv816kgzORqTzz8IQjaBS0GW-QTr4hI&sensor=false">
	    </script>
		<script>
		var geocoder;
		var map;
		var coordendasList;
		var infoWindow;
		var mensajeError = "Error al cargar la información.";
		
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
			if(aux.length!=0){
				$.each(aux['lugar']['ubicaciones'], function(k, v){		
					var coordenadas = coordendasList[k];	
					var coordenadasObj = [];
					$.each(coordenadas, function(key, val){
						coordenadasObj.push(new google.maps.LatLng(val[0], val[1]));
					});
					ubicacion = new google.maps.Polygon({
						paths: coordenadasObj,
						strokeColor: '${color}',
						strokeOpacity: 0.8,
						strokeWeight: 2,
						fillColor: '${color}',
						fillOpacity: 0.35
					});
				
					ubicacion.setMap(map);
					infoWindow = new google.maps.InfoWindow();
					
					google.maps.event.addListener(ubicacion, 'click', function(event) {
						infoWindow.setContent("<div align='center'><img height='40px' width='40px' alt='cargando' src='${resource(dir:'images',file:'loading.gif') }'></div>");
						infoWindow.setPosition(event.latLng);
						infoWindow.open(map);
								
						$.ajax( {
						    "url": "<g:createLink controller='publico' action='obtenerInformacionIndicador' />" + "/"+${idIndicador}+"?idTipo=" + ${idTipo} + "&idUbicacion="+v['idUbicacion'],
						    "success": function ( data ) {
								infoWindow.setContent(data);
						    },
						    error: function(jqXHR, textStatus, errorThrown) {
						    	infoWindow.setContent(mensajeError);
							},
						    "dataType": "html"
						} );
					});
				});	
			}
		}
	  </script>
	  <!-- Termina Google Maps -->
	  	<div id="map-canvas" style="width: 100%; height: 480px;"></div>	  	
	</g:if>
	<g:elseif test="${resultadosIndicador==null }">
		<div>
		<br>
		No se puede realizar la operación debido a la división entre 0
		<br><br>
		</div>
	</g:elseif>
	<g:else>
		No existen valores para mostrar el mapa a este nivel de área geográfica.
		<div id="map-canvas" style="width: 100%; height: 480px; display:none;"></div>
	</g:else>	  	  
</div>

