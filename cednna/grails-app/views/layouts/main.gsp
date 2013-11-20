<!DOCTYPE html>
<html>
	<head>
		<title>CEDNNA</title>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.min.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}" type="text/css">
		
<%--		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>--%>
<%--						--%>
<%--						--%>
	 
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'calc.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'default.css')}" type="text/css">
	    <link rel="stylesheet" href="${resource(dir: 'css', file: 'default.date.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'default.time.css')}" type="text/css">

<%--	<link rel="stylesheet" href="${resource(dir: 'css', file: 'docsupport/style.css')}" type="text/css">--%>
<%-- 	<link rel="stylesheet" href="${resource(dir: 'css', file: 'docsupport/prism.css')}" type="text/css">--%>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'chosen.css')}" type="text/css">
 				
 					
 						<g:javascript src="jquery-1.8.3.js" />
 				 		<g:javascript src="uikit.min.js" />
 						<g:javascript src="jquery-ui-1.8.20.custom.min.js" />
 						<g:javascript src="jquery.dataTables.js" />
						<g:javascript src="picker.js" />
						<g:javascript src="picker.date.js" />
						<g:javascript src="picker.time.js" />
						<g:javascript src="es_ES.js" />
					 	<g:javascript src="chosen.jquery.js" />
					  	<g:javascript src="docsupport/prism.js"  />
					  	<g:javascript src="jquery.blockUI.js" />
  						<g:javascript src="Utilerias.js" />	

		
						<script type="text/javascript">

									var CONTEXT_ROOT = '${request.getContextPath()}';
							</script>
							
	<g:layoutHead/>
		<r:layoutResources />
	</head>							
<body class="tm-background">
			<nav class="tm-navbar uk-navbar uk-navbar-attached">
			<div class="uk-container uk-container-center">

				<a class="uk-hidden-small" href="${createLink(controller:'publico', action:'indicadores')}"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logo.png" title="CEDNNA" alt="CEDNNA"></a>
                

				<a href="#tm-offcanvas" class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas></a>

				<div class="uk-navbar-brand uk-navbar-center uk-visible-small"><img src="${request.getContextPath()}/img/logo02.png"  title="CEDNNA" alt="CEDNNA"></div>

			</div>
		</nav>

		<div class="tm-middle">
			<div class="uk-container uk-container-center">

				<div class="uk-grid" data-uk-grid-margin>
				
					<div class="tm-sidebar uk-width-medium-1-5 uk-hidden-small">

						<ul class="tm-nav uk-nav" data-uk-nav>

							<li class="uk-nav-header">Men&uacute;</li>

							<li><g:link controller="Indicador" >Principal</g:link></li>
							<li><g:link controller="Indicador" action="list">Indicadores</g:link></li>
							   <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_NUCLEO">
								   <li><g:link controller="Formula">F&oacute;rmulas</g:link></li>
							   </sec:ifAnyGranted>
							<li><g:link controller="Variable">Datos estad&iacute;sticos</g:link></li>
 							 <sec:ifAnyGranted roles="ROLE_ADMIN">	
 							 <li><g:link controller="Variable" action="dele">Limpieza de datos estad&iacute;sticos</g:link></li>
							<li class="uk-nav-header">Cat&aacute;logos</li>
							<li><g:link controller="tipo">Categor&iacute;as </g:link></li>
							<li><g:link controller="sentido">Sentido</g:link></li>
							<li><g:link controller="dependencia">Dependencia</g:link></li>
							<li><g:link controller="unidadEjecutora">Unidad ejecutora</g:link></li>
							<li><g:link controller="Frecuencia">Frecuencia de medici&oacute;n</g:link></li>
							<li><g:link controller="unidadMedida">Unidad de medida</g:link></li>
							<li><g:link controller="eje">Modulo</g:link></li>
							<li><g:link controller="division">SubModulo</g:link></li>
							<li><g:link controller="PNDesarrollo">PED</g:link></li>
							<li><g:link controller="objetivoMilenio">Objetivos del milenio</g:link></li>
							<li><g:link controller="catOrigenDatos">Variables</g:link></li>
							</sec:ifAnyGranted>
							<li class="uk-nav-header">Seguimiento</li>
							<li><g:link controller="Indicador" action="semaforo">Sem&aacute;foro</g:link></li>
							<br/>
							<li><g:link controller="logout">Cerrar sesi&oacute;n </g:link></li>

						</ul>

					</div>
                
				<div class="tm-main uk-width-medium-4-5">
							
							
	
		<g:layoutBody/>
	
	
					</div> 
				</div>

			</div>
		</div>

		<div class="tm-footer">
			<div class="uk-container uk-container-center">

				<a href="http://www.redoaxaca.oaxaca.gob.mx/" target="_blank" style="float:right"><img src="${request.getContextPath()}/img/logored.png"></a>
			</div>
		</div>

		<div id="tm-offcanvas" class="uk-offcanvas">

			<div class="uk-offcanvas-bar">

				<ul class="uk-nav uk-nav-offcanvas uk-nav-parent-icon" data-uk-nav="{multiple:true}">
					<li class="uk-parent uk-active"><a href="#">Índice</a>
						<ul class="uk-nav-sub">
							
							<li class="uk-nav-header">Men&uacute;</li>

							<li><g:link controller="Indicador" >Principal</g:link></li>
							<li><g:link controller="Indicador" action="list">Indicadores</g:link></li>
							   <sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_NUCLEO">
								   <li><g:link controller="Formula">F&oacute;rmulas</g:link></li>
							   </sec:ifAnyGranted>
							<li><g:link controller="Variable">Origen de datos</g:link></li>
 							 <sec:ifAnyGranted roles="ROLE_ADMIN">	
 							 <li><g:link controller="Variable" action="dele">Limpieza de origen de datos </g:link></li>
							<li class="uk-nav-header">Cat&aacute;logos</li>
							<li><g:link controller="tipo">Categor&iacute;as </g:link></li>
							<li><g:link controller="sentido">Sentido</g:link></li>
							<li><g:link controller="dependencia">Dependencia</g:link></li>
							<li><g:link controller="unidadEjecutora">Unidad ejecutora</g:link></li>
							<li><g:link controller="Frecuencia">Frecuencia de medici&oacute;n</g:link></li>
							<li><g:link controller="unidadMedida">Unidad de medida</g:link></li>
							<li><g:link controller="eje">Modulo</g:link></li>
							<li><g:link controller="division">SubModulo</g:link></li>
							<li><g:link controller="PNDesarrollo">PED</g:link></li>
							<li><g:link controller="objetivoMilenio">Objetivos del milenio</g:link></li>
							</sec:ifAnyGranted>
							<li class="uk-nav-header">Seguimiento</li>
							<li><g:link controller="Indicador" action="semaforo">Sem&aacute;foro</g:link></li>
							<br/>
							<li><g:link controller="logout">Cerrar sesi&oacute;n </g:link></li>
					
						</ul>
					</li>

				</ul>

			</div>

		</div>
	</body>
	
</html>
