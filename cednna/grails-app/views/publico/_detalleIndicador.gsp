<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>

<script>
	  $(function () {
		  $( "#tabs" ).tabs();
	        $('#container').highcharts(
	            ${tablaJSON});
            	        
	        $('#myTab a').click(function (e) {
	      	  e.preventDefault();
	      	  $(this).tab('show');
	      	})
	    });

		function mostrarCargandoImg(div){
	  		$( "#"+div ).html("<div align='center'><img height='80px' width='80px' alt='cargando' src='${resource(dir:'images',file:'loading.gif') }'></div>")											
		}

		function ocultarCargandoImg(div){
			$( "#"+div ).html("")			
		}
				
		function goToByScroll(id){
			loadScript();		      
		    id = id.replace("link", "");
		      
		    $('html,body').animate({
		        scrollTop: $("#"+id).offset().top},
		        'slow');
		}
		
	  </script>

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
	  		<label for="opciones">Área geográfica:</label>
	  		<select id="opciones" name="opciones" 
	  			onchange="${remoteFunction(
					  controller:'publico',
					  action: 'actualizarTablaIndicador',
					  params: '\'idTipo=\' + this.value',
					  update: 'tablaIndicador',
					  onLoading: "mostrarCargandoImg('tablaIndicador')",
					  onLoaded: "ocultarCargandoImg('tablaIndicador')",					  
					  id: indicadorInstance?.id  )}">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  		
	  		<div id="tablaIndicador">
	  			<g:render template="tablaIndicador"></g:render>	  		
			</div>	  				  
			<div id="container" style="min-width: 310px; height: 400px; margin: 0 auto"></div>
		</div>
	  	
				<!-- Tabla de variación -->
				<table style="text-align: center">
			  		<tr>
			  			<th rowspan="2" valign="middle">Indicador</th>
			  			<th colspan="${resultados?.size()-1}">Variación</th>			  			
			  		</tr>
			  		<tr>			  			
			  			<g:set value="false" var="imprimir"></g:set>
			  			<g:each var="resultado" in="${resultados}">
			  				<g:if test="${imprimir=='true'}">
			  					<th class="subcelda">${anio}/${resultado?.anio}</th>
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
			  					<td>${resultado?.indicador-anio==0 ? 0 : Math.round( (resultado?.indicador-anio) * 100.0 ) / 100.0} puntos</td>
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
	  		<tr class="odd"><td class="marked">Nombre del indicador:</td><td>${indicadorInstance?.nombre }</td></tr>
	  		<tr class="even"><td class="marked">Objetivo del indicador:</td><td>${indicadorInstance?.objetivo }</td></tr>
	  		<tr class="odd"><td class="marked">Dependencia Responsable:</td><td>${indicadorInstance?.dependencia?.descripcion }</td></tr>
	  		<tr class="even"><td class="marked">Unidad Administrativa Ejecutora:</td><td>${indicadorInstance?.ejecutora?.descripcion }</td></tr>
	  		<tr class="odd"><td class="marked">Sentido esperado:</td><td>${indicadorInstance?.sentido?.descripcion }</td></tr>
	  		<tr class="even"><td class="marked">Frecuencia de medición:</td><td>${indicadorInstance?.frecuencia?.descripcion }</td></tr>	  			  	
	  		
	  		<tr class="odd"><td class="marked">Fórmula de cálculo:</td>
	  		<td>
	  		<p lang="latex">$${indicadorInstance?.formula?.sentencia}$</p>
	  		<g:each var="variable" in="${ indicadorInstance?.variables}">
	  			<p>${variable?.clave} = ${CatOrigenDatos.findByClave(variable?.claveVar)?.descripcion} </p>
	  		</g:each>	  			  			  		
	  		</td></tr>
	  		
	  		<tr class="even"><td class="marked">Medios de verificación:</td><td>${indicadorInstance?.mediosVerificacion }</td></tr>
	  		<tr class="odd"><td class="marked">Comentarios técnicos:</td><td>${indicadorInstance?.comentarios }</td></tr>
	  		<tr class="even"><td class="marked">Fecha de actualización:</td><td><g:formatDate type="date" style="LONG" date="${indicadorInstance?.fechaActualizacion }"/></td></tr>	  		  		
	  	</tbody>
	  	</table>
	  </div>
	  <div class="tab-pane" id="serie">
	  	<label for="opcionSerie">Área geográfica:</label>
	  		<select id="opcionSerie" name="opcionSerie" 
	  			onchange="${remoteFunction(
					  controller:'publico',
					  action: 'actualizarTablaIndicador',
					  params: '\'idTipo=\' + this.value',
					  update: 'tablaIndicadorSerie',
					  onLoading: "mostrarCargandoImg('tablaIndicadorSerie')",
					  onLoaded: "ocultarCargandoImg('tablaIndicadorSerie')",
					  id: indicadorInstance?.id  )}">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  		
	  		<div id="tablaIndicadorSerie">
	  			<g:render template="tablaIndicador"></g:render>	  		
			</div>	  	
	  </div>
	  
	  <div class="tab-pane" id="calculo">
	  	<label for="opcionDatosCalculo">Área geográfica:</label>
	  		<select id="opcionDatosCalculo" name="opcionDatosCalculo" 
	  			onchange="${remoteFunction(
					  controller:'publico',
					  action: 'actualizarDatosCalculo',
					  params: '\'idTipo=\' + this.value',
					  update: 'datosCalculo',
					  onLoading: "mostrarCargandoImg('datosCalculo')",
					  onLoaded: "ocultarCargandoImg('datosCalculo')",					  
					  id: indicadorInstance?.id  )}">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  	<br><br>
	  	<!-- <p><b>Fórmula de cálculo:</b> <span lang="latex">$${indicadorInstance?.formula?.sentencia}$</span><br><br></p> -->
	  		 <p><b>Fórmula de cálculo:</b> <span>${formula}</span><br><br></p>  	
	  	<div id="datosCalculo">
	  		<g:render template="datosCalculo"></g:render>  
	  	</div>	
	  </div>
	  
	  <div class="tab-pane" id="mapa">
	  	<label for="opcionesMapa">Área geográfica:</label>
	  		<select id="opcionesMapa" name="opcionesMapa" 
	  			onchange="${remoteFunction(
					  controller:'publico',
					  action: 'actualizarMapa',
					  params: '\'idTipo=\' + this.value',
					  update: 'mapaIndicador',
					  onLoading: "mostrarCargandoImg('mapaIndicador')",
					  onLoaded: "ocultarCargandoImg('mapaIndicador')",						  
					  onComplete: "goToByScroll('opcionesMapa')",					  					  					
					  id: indicadorInstance?.id  )}">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>	  		
	  	
	  	<div id="cargandoMapa" style="display: none" align="center"><img height="80px" width="80px" alt="cargando" src="${resource(dir:'images',file:'loading.gif') }"></div>
	  	<div id="mapaIndicador">	  		
	  		<g:render template="mapa"></g:render>
	  	</div>
	  </div>
	</div>	 	
	
	<script src="${resource(dir: 'js', file: 'highcharts/js/highcharts.js')}"  type="text/javascript" charset="utf-8"></script>
	  	<script src="${resource(dir: 'js', file: 'highcharts/js/modules/exporting.js')}"  type="text/javascript" charset="utf-8"></script>