<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Periodo" %>

	<script type="text/javascript" charset="utf-8">
		$(document).ready(function(){
			var idTipo = 1;

			actualizarTabla(idTipo, "divIndicadores");
			actualizarTabla(idTipo, "divIndicadorSerie");
			actualizarSelectGrafica();
			actualizarTablaDatosCalculo(1);
		});

		function actualizarTablaDatosCalculo(idTipo){
			var div = "divDatosCalculo";
			var tabla = "tablaDatosCalculo";
			var idIndicador = ${indicadorInstance?.id};
			
			mostrarCargandoImg("divDatosCalculoCargando");

			$.ajax( {
			    "url": "<g:createLink controller='publico' action='getTitulosDatosCalculo' />" + "/"+ idIndicador+"?idTipo=" + idTipo,
			    "success": function ( data ) {
			    	$('#'+div).html( data );
			    	$.ajax( {
					    "url": "<g:createLink controller='publico' action='getTablaDatosCalculo' />" + "/"+ idIndicador+"?idTipo=" + idTipo,
					    "success": function ( json ) {
						    $('#'+tabla).dataTable( json );
						    $('#'+tabla+'_filter input').addClass('form-control medium mayus');
						    $('#divDatosCalculoCargando').html("");
					    },
					    "dataType": "json"
					} );
			    }
			} );
		}

		function actualizarSelect(){
			var config = {
				      '.chosen-select'           : {},
				      '.chosen-select-deselect'  : {allow_single_deselect:true},
				      '.chosen-select-no-single' : {disable_search_threshold:10},
				      '.chosen-select-no-results': {no_results_text:'Oops, nada encontrado!'},
				      '.chosen-select-width'     : {width:"95%"}
				    }
				    for (var selector in config) {
				      $(selector).chosen(config[selector]);
				    }
		}

		function actualizarTabla(idTipo, div){
			var tabla = "";
			if(div == "divIndicadores"){
				tabla = "tablaIndicadores";
			}else{
				tabla = "tablaIndicadoresSerie";
			}
			var idIndicador = ${indicadorInstance?.id};
			mostrarCargandoImg(div);
			$.ajax( {
			    "url": "<g:createLink controller='publico' action='getTablaIndicador' />" + "/"+ idIndicador+"?idTipo=" + idTipo,
			    data: {tabla:tabla},
			    "success": function ( data ) {
			    	$('#'+div).html( data );
			    }
			} );
		}
		
		function actualizarareaGrafica(){
			var selectVal = $( "#areaGrafica" ).val();
			var select = $( "#opcionesGrafica" ).val();
			
			${remoteFunction(
					controller:'publico',
					action: 'actualizarGrafica',
					params: '\'idTipo=\' + select +\'&idArea=\' + selectVal',
					update: 'grafica',
					onLoading: "mostrarCargandoImg('grafica')",
					id: indicadorInstance?.id 
				 ) }
		}

		function actualizarareaGraficaDatosCalculo(){
			var selectVal = $( "#areaGraficaDatosCalculo" ).val();
			var select = $( "#opcionesGraficaDatosCalculo" ).val();
			
			${remoteFunction(
					controller:'publico',
					action: 'actualizarGraficaDatosCalculo',
					params: '\'idTipo=\' + select +\'&idArea=\' + selectVal',
					update: 'graficaDatosCalculo',
					onLoading: "mostrarCargandoImg('graficaDatosCalculo')",
					id: indicadorInstance?.id 
				 ) }
		}
		
		function actualizarSelectGrafica(){
			var select = $( "#opcionesGrafica" ).val();
			${remoteFunction(
					  controller:'publico',
					  action: 'actualizarAreaGrafica',
					  params: '\'idTipo=\' + select + \'&idSelect=areaGrafica\'' ,
					  update: 'selectGrafica',
					  onLoading: "mostrarCargandoImg('selectGrafica')",
					  onComplete: "actualizarareaGrafica();actualizarSelect();",
					  id: indicadorInstance?.id  )}
		}

		function actualizarSelectGraficaDatosCalculo(){
			var select = $( "#opcionesGraficaDatosCalculo" ).val();
			${remoteFunction(
					  controller:'publico',
					  action: 'actualizarAreaGrafica',
					  params: '\'idTipo=\' + select + \'&idSelect=areaGraficaDatosCalculo\'',
					  update: 'selectGraficaDatosCalculo',
					  onLoading: "mostrarCargandoImg('selectGraficaDatosCalculo')",
					  onComplete: "actualizarareaGraficaDatosCalculo();actualizarSelect();",
					  id: indicadorInstance?.id  )}
		}
		
	  	$(function () {
		  $( "#tabs" ).tabs();
            	        
	       $('#myTab a').click(function (e) {
	      	  e.preventDefault();
	      	  $(this).tab('show');
	      	  if(this.name=="mapa"){
	      		initialize();
		      }else if(this.name=="calculo"){
		    	  $(window).resize();
			      if($("#graficaDatosCalculo").html()==""){
			    	  actualizarSelectGraficaDatosCalculo();
				  }
			  };
	      	})
	    });

		function mostrarCargandoImg(div){
	  		$( "#"+div ).html("<div align='center'><img height='80px' width='80px' alt='cargando' src='${resource(dir:'images',file:'loading.gif') }'></div>");											
		}

		function goToByScroll(id){
			initialize();
		    id = id.replace("link", "");
		      
		    $('html,body').animate({
		        scrollTop: $("#"+id).offset().top},
		        'slow');
		}

	  </script>
	  
<h3>${indicadorInstance?.nombre }</h3>
	<ul class="nav nav-tabs" id="myTab">
	  <li class="active"><a href="#indicador">Indicador</a></li>
	  <li><a href="#metadato">Metadatos</a></li>
	  <li><a href="#planestatal">Alineación al Plan Estatal</a></li>
	  <li><a href="#serie">Serie histórica</a></li>
	  <li><a name="calculo" href="#calculo">Datos para el cálculo</a></li>
	  <li><a href="#oaxacaNivelNacional">Oaxaca a nivel nacional</a></li>
	  <li><a name="mapa" href="#mapa">Mapa</a></li>
	</ul>
	 
	<div class="tab-content">
	  <div class="tab-pane active" id="indicador">
	  	<div>
	  		<label for="opciones">Área geográfica:</label>
	  		<select id="opciones" name="opciones" 
	  			onchange="actualizarTabla(this.value,'divIndicadores')">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  		
	  		<div id="divIndicadores"></div>
	  		
			<label for="opcionesGrafica">Nivel de gráfica:</label>
			<select id="opcionesGrafica" name="opcionesGrafica" 
	  			onchange="actualizarSelectGrafica()">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  		
	  		<div id="selectGrafica"></div>
	  		
			<div id="grafica"></div>
			
		</div>
	  	
				<!-- Tabla de variación -->
				<table style="text-align: center">
			  		<tr>
			  			<th rowspan="2" valign="middle">Indicador</th>
			  			<th colspan="${resultados?.size()!=null?resultados?.size()-1:'0'}">Variación</th>			  			
			  		</tr>
			  		<tr>			  			
			  			<g:set value="false" var="imprimir"></g:set>
			  			<g:each var="resultado" in="${resultados}">
			  				<g:if test="${imprimir=='true'}">
			  					<g:if test="${indicadorInstance?.etiquetaPeriodo}">
				  					<th class="subcelda">${Periodo.findByAnioInicial(anio)?.descripcion}/${Periodo.findByAnioInicial(resultado?.anio)?.descripcion}</th>
			  					</g:if>
			  					<g:else>
			  						<th class="subcelda">${anio}/${resultado?.anio}</th>
			  					</g:else>
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
					  			<g:if test="${resultado?.indicador!=null && anio!='null'}">
			  					<td>			  					
			  					${resultado?.indicador-anio==0 ? 0 : Math.round( (resultado?.indicador-anio) * 100.0 ) / 100} puntos
			  					</td>
			  					<g:set value="${resultado?.indicador}" var="anio"></g:set>
			  					</g:if>
			  					<g:else>
			  						<td>			  					
				  					-			  					
				  					</td>
				  					<g:set value="${resultado?.indicador}" var="anio"></g:set>
			  					</g:else>			  					
			  				</g:if>
			  				<g:else>
			  					<g:if test="${resultado?.indicador!=null }">
				  					<g:set value="${resultado?.indicador}" var="anio"></g:set>
				  					<g:set value="true" var="imprimirDatos"></g:set>
			  					</g:if>
			  					<g:else>
			  						<g:set value="null" var="anio"></g:set>
				  					<g:set value="true" var="imprimirDatos"></g:set>
			  					</g:else>			  				
			  				</g:else>
					  	</g:each>
					  	
			  		</tr>
			  	</table>
			  	<!-- termina tabla de variación -->
	  	
	  </div>
	  <div class="tab-pane" id="metadato">
	  	<table class="nuevocolortabla">
	  	<tbody>
	  		<tr class="odd"><td class="marked">Nombre del indicador:</td><td>${indicadorInstance?.nombre }</td></tr>
	  		<tr class="even"><td class="marked">Objetivo del indicador:</td><td>${indicadorInstance?.objetivo }</td></tr>
	  		<tr class="odd"><td class="marked">Dependencia responsable:</td><td>${indicadorInstance?.dependencia?.descripcion }</td></tr>
	  		<tr class="even"><td class="marked">Unidad administrativa ejecutora:</td><td>${indicadorInstance?.ejecutora?.descripcion }</td></tr>
	  		<tr class="odd"><td class="marked">Sentido esperado:</td><td>${indicadorInstance?.sentido?.descripcion }</td></tr>
	  		<tr class="even"><td class="marked">Frecuencia de medición:</td><td>${indicadorInstance?.frecuencia?.descripcion }</td></tr>	  			  	
	  		
	  		<tr class="odd"><td class="marked">Fórmula de cálculo:</td>
	  		<td>
	  		<p lang="latex">$${indicadorInstance?.formula?.sentencia}$</p>
	  		<g:each var="variable" in="${ indicadorInstance?.variables?.sort{it.clave}}">
	  			<p>${variable?.clave} = ${CatOrigenDatos.findByClave(variable?.claveVar)?.descripcion} </p>
	  		</g:each>	  			  			  		
	  		</td></tr>
	  		
	  		<tr class="even"><td class="marked">Medios de verificación:</td>
	  		<td><g:each var="variable" in="${ indicadorInstance?.variables?.sort{it?.clave}}">
	  			<p>${variable?.clave} = ${variable?.descripcion!=''?variable?.descripcion:'No existe información'} </p>
	  		</g:each></td></tr>
	  		<tr class="odd"><td class="marked">Comentarios técnicos:</td><td>${indicadorInstance?.comentarios }</td></tr>
	  		<tr class="even"><td class="marked">Fecha de actualización:</td><td><g:formatDate type="date" style="LONG" date="${indicadorInstance?.fechaActualizacion }"/></td></tr>	  		  		
	  	</tbody>
	  	</table>
	  </div>
	  <div class="tab-pane" id="planestatal">
	  	<g:if test="${indicadorInstance?.pnDesarrollo}">
	  		<table class="nuevocolortabla">
			  	<tbody>
			  		<tr class="odd"><td class="marked">Eje:</td><td>${indicadorInstance?.pnDesarrollo?.descripcion }</td></tr>
			  		<tr class="even"><td class="marked">Tema:</td><td>${indicadorInstance?.tema?.descripcion }</td></tr>
			  		<tr class="odd"><td class="marked">Objetivo:</td><td>${indicadorInstance?.objetivoPND!=null?indicadorInstance.objetivoPND:'-' }</td></tr>
			  		<tr class="even"><td class="marked">Estrategia:</td><td>${indicadorInstance?.estrategia!=null?indicadorInstance.estrategia:'-' }</td></tr>
			  		<tr class="odd"><td class="marked">Programa:</td><td>${indicadorInstance?.nombrePrograma!=null?indicadorInstance.nombrePrograma:'-' }</td></tr>
			  	</tbody>
		  	</table>
	  	</g:if>
	  	<g:else>
	  		<p>Este indicador no está alineado con el Plan Estatal de Desarrollo de Oaxaca.</p>
	  	</g:else>
	  </div>
	  <div class="tab-pane" id="serie">
	  	<label for="opcionSerie">Área geográfica:</label>
	  		<select id="opcionSerie" name="opcionSerie" 
	  			onchange="actualizarTabla(this.value,'divIndicadorSerie')">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  		
	  		<div id="divIndicadorSerie"></div>	  	
	  </div>
	  
	  <div class="tab-pane" id="calculo">
	  <label for="opcionDatosCalculo">Área geográfica:</label>
	  		<select id="opcionDatosCalculo" name="opcionDatosCalculo" 
	  			onchange="actualizarTablaDatosCalculo(this.value)">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>
	  	
	  	<br><br>
	  	<p><b>Fórmula de cálculo:</b> <span>${formula}</span><br><br></p>  	
	  		
	  	<div id="divDatosCalculo"></div>
	  	
	  	<div id="divDatosCalculoCargando"></div>
	  	
	  	<label for="opcionesGraficaDatosCalculo">Nivel de gráfica:</label>
		<select id="opcionesGraficaDatosCalculo" name="opcionesGraficaDatosCalculo" 
  			onchange="actualizarSelectGraficaDatosCalculo()">
  			<option value="1">Estatal</option>
  			<option value="2">Regional</option>
  			<option value="3">Municipal</option>	  			
  		</select>
  		
  		<div id="selectGraficaDatosCalculo"></div>
  		
		<div id="graficaDatosCalculo"></div>
	  	
	  	<br><br>
	  	<div>${indicadorInstance?.html }</div>
	  </div>
	  
	  <div class="tab-pane" id="oaxacaNivelNacional">
	  	<table class="nuevocolortabla">
		  	<tbody>
		  		<g:if test="${indicadorInstance?.calcularPorPeriodoNivelNacional }">
		  			<tr class="odd"><td class="marked">Periodo:</td><td>${indicadorInstance?.periodoNivelNacional?.descripcion!=null?indicadorInstance?.periodoNivelNacional?.descripcion:'-' }</td></tr>
		  		</g:if>
		  		<g:else>
		  			<tr class="odd"><td class="marked">Año:</td><td>${indicadorInstance?.periodoNivelNacional?.anioInicial!=null?indicadorInstance?.periodoNivelNacional?.anioInicial:'-' }</td></tr>
		  		</g:else>
		  		<tr class="odd"><td class="marked">Valor:</td><td>${indicadorInstance?.valorNivelNacional!=null?indicadorInstance.valorNivelNacional:'-' }</td></tr>
		  		<tr class="odd"><td class="marked">Fuente:</td><td>${indicadorInstance?.fuenteNivelNacional!=null?indicadorInstance.fuenteNivelNacional:'-' }</td></tr>
		  	</tbody>
	  	</table>
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
					  onComplete: "goToByScroll('opcionesMapa')",					  					  					
					  id: indicadorInstance?.id  )}">
	  			<option value="1">Estatal</option>
	  			<option value="2">Regional</option>
	  			<option value="3">Municipal</option>	  			
	  		</select>	  		
	  	
	  	<div id="cargandoMapa" style="display: none" align="center"><img height="80px" width="80px" alt="cargando" src="${resource(dir:'images',file:'loading.gif') }"></div>
	  	<div id="mapaIndicador">	  		
	  		<g:render template="mapa" model="[idIndicador:indicadorInstance?.id, idTipo:1]"></g:render>
	  	</div>
	  </div>
	</div>	 
	
	<script src="${resource(dir: 'js', file: 'highcharts/js/highcharts-4.1.9.js')}"  type="text/javascript" charset="utf-8"></script>
	<script src="${resource(dir: 'js', file: 'highcharts/js/modules/exporting.js')}"  type="text/javascript" charset="utf-8"></script>
	<g:javascript src="chosen.jquery.js" />
	  	<script type="text/javascript">
	  	$(function(){
	  		var config = {
			      '.chosen-select'           : {},
			      '.chosen-select-deselect'  : {allow_single_deselect:true},
			      '.chosen-select-no-single' : {disable_search_threshold:10},
			      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
			      '.chosen-select-width'     : {width:"95%"}
			    }
			    for (var selector in config) {
			      $(selector).chosen(config[selector]);
			    }
	  	});
  </script>	