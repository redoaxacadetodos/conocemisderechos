<!DOCTYPE html>
<html>
	<head>
		<title>CEDNNA</title>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.min.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'custom.css')}" type="text/css">
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	   <g:javascript src="uikit.min.js" />
	 <g:javascript src="jquery.form.js" />
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<meta http-equiv="X-UA-Compatible" content="IE=edge,chrome=1">
	
		<meta name="viewport" content="width=device-width, initial-scale=1.0">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'calc.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'main.css')}" type="text/css">
<%--		<link rel="shortcut icon" href="${resource(dir: 'images', file: 'favicon.ico')}" type="image/x-icon">--%>
<%--				<link rel="apple-touch-icon" href="${resource(dir: 'images', file: 'apple-touch-icon.png')}">--%>
<%--				<link rel="apple-touch-icon" sizes="114x114" href="${resource(dir: 'images', file: 'apple-touch-icon-retina.png')}">--%>
<%--				--%>
<%--				<link rel="stylesheet" href="${resource(dir: 'css', file: 'mobile.css')}" type="text/css">--%>
<%--				<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>--%>
<%--				--%>
<%--	--%>
<%--				   <g:javascript src="bootstrap-datepicker.js" />--%>
<%--	   --%>
<%--	    			<g:javascript src="bootstrap-datetimepicker.min.js" />--%>
<%--			--%>

 	
					<g:javascript src="bootstrap-modal.js" />	
		
				<g:javascript src="Utilerias.js" />	
<%--					<link href="${resource(dir:'css',file:'bootstrap.css')}" rel="stylesheet">--%>
				
		
						<script type="text/javascript">

									var CONTEXT_ROOT = '${request.getContextPath()}';
							</script>
							
	<g:layoutHead/>
		<r:layoutResources />
	</head>							
	<body class="tm-background">
			<nav class="tm-navbar uk-navbar uk-navbar-attached">
			<div class="uk-container uk-container-center">

				<a class="uk-hidden-small" href="index.html"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logo.png" title="CEDNNA" alt="CEDNNA"></a>
 				<ul class="uk-navbar-nav uk-hidden-small">

<%-- <li><g:link controller="Indicador" class="iconobar icono_invierte" data-uk-tooltip="{pos:'bottom'}" title="Inversión Social e Infancia"></g:link>	</li> --%>	
		<li><g:link class="iconobar icono_home" controller="publico" action="indicadores" data-uk-tooltip="{pos:'bottom'}" title="Inicio"></g:link></li>
		<li><g:link controller="Indicador" class="iconobar icono_catalogos" data-uk-tooltip="{pos:'bottom'}" title="Catálogo de Indicadores de Infancia y Adolescencia"></g:link></li>
		<li><g:link class="iconobar icono_directorio" controller="publico" action="directorio" data-uk-tooltip="{pos:'bottom'}" title="Directorio"></g:link></li>
		<li><g:link class="iconobar icono_contacto" controller="publico" action="contacto" data-uk-tooltip="{pos:'bottom'}" title="Contacto"></g:link></li>
		<li><g:link class="iconobar icono_ayuda"  controller="publico" action="ayuda" data-uk-tooltip="{pos:'bottom'}" title="Ayuda"></g:link></li>	
				</ul>

				
				<div class="uk-width-1-10">
					<a href="#tm-offcanvas" class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas></a>
				</div>

				<div class="uk-navbar-brand uk-navbar-center uk-visible-small">
					<img src="${request.getContextPath()}/img/logomini_cednna.png"  title="CEDNNA" alt="CEDNNA" class="fondologomini">
				</div>

			</div>
		</nav>

		<div class="tm-middle">
			<div class="uk-container uk-container-center">

				<div class="uk-grid" data-uk-grid-margin>
				

                
				<div class="uk-width-1-1">
							
							
	
		<g:layoutBody/>
	
	
					</div>
				</div>

			</div>
		</div>

		<div class="tm-footer">
			<div class="uk-container uk-container-center">
				<p><strong>Un clic x sus derechos,</strong> Sistema de Monitoreo de Derechos de la Niñéz y la Adolescencia en Oaxaca, es una plataforma de código abierto del Gobierno del Estado de Oaxaca que forma parte de su política transversal de Derechos Humanos, y su política de Transparencia, Participación Ciudadana y Gobierno abierto.</p>
				<a href="http://www.redoaxaca.oaxaca.gob.mx/" target="_blank"><img src="${request.getContextPath()}/img/logored.png"></a>
				<hr></hr>
				<div class="uk-grid">
                     <div class="uk-width-1-3"><div class="uk-panel">
                     	<a href="http://www.oaxaca.gob.mx" target="_blank"><p>www.oaxaca.gob.mx</a>
                     	<br> Gobierno del Estado de Oaxaca 2010-2016</p>
						<img src="${request.getContextPath()}/img/logo_cednna_footer.png">
						<p>Av. San Felipe del Agua #836 Col. San Felipe del Agua, Oaxaca de Juárez, Oax., C.P. 68020	
						<br>Tel: (951) 5201146
						<a href="http://www.cednna.oaxaca.gob.mx" target="_blank"><br>www.cednna.oaxaca.gob.mx</a></p></div></div>
                     <div class="uk-width-1-3"><div class="uk-panel">
                     	<h3>Ligas de interés</h3>
						<a href="http://www.periodicooficial.oaxaca.gob.mx/" target="_blank"><p>Periódico Oficial del @GobOax</p></a>
						<a href="http://www.transparenciapresupuestaria.oaxaca.gob.mx/" target="_blank"><p>Portal de Transparencia presupuestaria del @GobOax</p></a>
						<a href="http://www.infopublica.oaxaca.gob.mx/" target="_blank"><p>Ventanilla Única de Acceso a la Información del @GobOax</p></a>
						<a href="http://www.oaxtransparente.oaxaca.gob.mx/evaluarparamejorar/evaluar.php" target="_blank"><p>Evaluar para Mejorar 2.0</p></a>
						<a href="http://sapao.herokuapp.com/" target="_blank"><p>Plataforma de reportes ciudadanos de @SAPAOOaxaca</p></a>
						<a href="http://www.oaxtransparente.oaxaca.gob.mx/" target="_blank"><p>Oaxtransparente</p></a>
					</div></div>
                     <div class="uk-width-1-3"><div class="uk-panel">
                     	<h3>Síguenos en las redes</h3>
                     	<p><a href="https://www.facebook.com/cednna.oaxaca" target="_blank"><i class="uk-icon-facebook-square uk-icon-large"></i> Facebook</a></p>
                     	<p><a href="https://twitter.com/cednna_oaxaca" target="_blank"><i class="uk-icon-twitter-square uk-icon-large"></i> Twitter</a></p>
                     </div></div>
                </div>
			</div>
		</div>

		<div id="tm-offcanvas" class="uk-offcanvas">

			<div class="uk-offcanvas-bar">

				<ul class="uk-nav uk-nav-offcanvas uk-nav-parent-icon" data-uk-nav="{multiple:true}">
					<li class="uk-parent uk-active"><a href="#">Índice</a>
						<ul class="uk-nav-sub">
							<li><g:link controller="Indicador">Indicadores</g:link></li>
							<li><g:link controller="Formula">Formulas</g:link></li>
							<li><g:link controller="Variable">Origen de datos</g:link></li>
							<li><g:link controller="logout">Cerrar sesion </g:link></li>
					
						</ul>
					</li>

				</ul>

			</div>

		</div>
	</body>
	
</html>
