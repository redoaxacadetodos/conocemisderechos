<!DOCTYPE html>
<html>
	<head>
		<title>CEDNNA</title>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.min.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.docs.min.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}" type="text/css">
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	
	   <g:javascript src="uikit.min.js" />
	
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'calc.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">

<%--	<link rel="stylesheet" href="${resource(dir: 'css', file: 'docsupport/style.css')}" type="text/css">--%>
<%-- 	<link rel="stylesheet" href="${resource(dir: 'css', file: 'docsupport/prism.css')}" type="text/css">--%>
	<link rel="stylesheet" href="${resource(dir: 'css', file: 'chosen.css')}" type="text/css">
 				
 						<g:javascript src="jquery-ui-1.8.20.custom.min.js" />
 						<g:javascript src="jquery.dataTables.js" />
		

 	<g:javascript src="chosen.jquery.js" />
  	<g:javascript src="docsupport/prism.js"  />
  	

				<g:javascript src="Utilerias.js" />	

		
						<script type="text/javascript">

									var CONTEXT_ROOT = '${request.getContextPath()}';
							</script>
							
	<g:layoutHead/>
		<r:layoutResources />
	</head>							
<body class="tm-background">
			<nav class="tm-navbar uk-navbar uk-navbar-attached">
			<div class="uk-container uk-container-center" >

				<a class="uk-hidden-small" href="${createLink(controller:'publico', action:'indicadores')}"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logo.png" title="CEDNNA" alt="CEDNNA"></a>

				<ul class="uk-navbar-nav uk-hidden-small">

<%-- <li><g:link controller="Indicador" class="iconobar icono_invierte" data-uk-tooltip="{pos:'bottom'}" title="Inversión Social e Infancia"></g:link>	</li> --%>	
		<li><g:link class="iconobar icono_home" action="indicadores" data-uk-tooltip="{pos:'bottom'}" title="Inicio"></g:link></li>
		<li><g:link controller="Indicador" class="iconobar icono_catalogos" data-uk-tooltip="{pos:'bottom'}" title="Catálogo de Indicadores de Infancia y Adolescencia"></g:link></li>
		<li><g:link class="iconobar icono_directorio" controller="publico" action="directorio" data-uk-tooltip="{pos:'bottom'}" title="Directorio"></g:link></li>
		<li><g:link class="iconobar icono_contacto" controller="publico" action="contacto" data-uk-tooltip="{pos:'bottom'}" title="Contacto"></g:link></li>
		<li><g:link class="iconobar icono_ayuda"  controller="publico" action="ayuda" data-uk-tooltip="{pos:'bottom'}" title="Ayuda"></g:link></li>	
				</ul>

				<a href="#tm-offcanvas" class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas></a>

				<div class="uk-navbar-brand uk-navbar-center uk-visible-small"><img src="${request.getContextPath()}/img/logo02.png"  title="CEDNNA" alt="CEDNNA"></div>

			</div>
		</nav>
		


		<div class="tm-middle">
			<div class="uk-container uk-container-center">
				<div class="uk-grid" data-uk-grid-margin>

           
				<div class="uk-container-center" style="border-left:none !important;">
							
							
	
		<g:layoutBody/>
	
	
					</div>
				</div>

			</div>
		</div>
		
		

		<div class="tm-footer">
			<div class="uk-container uk-container-center">
				<p><strong>Mis derechos,</strong> Sistema de Indicadores y Monitoreo del estado que guardan los Derechos de Niñas, Niños y Adolescentes en Oaxaca, es una herramienta del Gorbierno del Estado de Oaxaca que forma parte de su política transversal de Derechos Humanos y su política de Transparencia, participación ciudadana y Gobierno agrieto, desarrollada por:</p>
				<a href="http://www.redoaxaca.oaxaca.gob.mx/" target="_blank"><img src="${request.getContextPath()}/img/logored.png"></a>
				<hr></hr>
				<div class="uk-grid">
                     <div class="uk-width-1-3"><div class="uk-panel uk-panel-box">
                     	<p>www.oaxaca.gob.mx</p>
						<img src="${request.getContextPath()}/img/logo_cednna_footer.png">
						<p>Av. San Felipe del Agua #836 Col. San Felipe del Agua, Oaxaca de Juárez, Oax., C.P. 68020
						(951) 5201146</p></div></div>
                     <div class="uk-width-1-3"><div class="uk-panel uk-panel-box">
                     	<h3>Ligas de interés</h3>
						<p>CEDNNA @cednna_oaxaca</p>
						<p>Periódico Oficial del @GobOax</p>
						<p>Portal de Transparencia presupuestaria del @GobOax</p>
						<p>Ventanilla Única de Acceso a la Información del @GobOax</p>
						<p>Evaluar para Mejorar 2.0</p>
						<p>Plataforma de reportes ciudadanos de @SAPAOOaxaca</p>
						<p>Oaxtransparente</p>
					</div></div>
                     <div class="uk-width-1-3"><div class="uk-panel uk-panel-box">
                     	<h3>Síguenos en las redes</h3>
                     	<i class="uk-icon-facebook-square"></i> Face
                     </div></div>
                </div>
			</div>
		</div>

		<div id="tm-offcanvas" class="uk-offcanvas">

			<div class="uk-offcanvas-bar">

				<ul class="uk-nav uk-nav-offcanvas uk-nav-parent-icon" data-uk-nav="{multiple:true}">
					<li class="uk-parent uk-active"><a href="#">Índice</a>
						<ul class="uk-nav-sub">
							<li><g:link class="list" action="list">Calendario</g:link></li>
							<li><g:link class="create" action="create">Directorio</g:link></li>
							<li><g:link class="create" action="create">Contacto</g:link></li>
							<li><g:link class="create" action="create">Ayuda</g:link></li>
						</ul>
					</li>

				</ul>

			</div>

		</div>
	</body>
	
</html>
