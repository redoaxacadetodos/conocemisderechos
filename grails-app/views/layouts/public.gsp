<%@ page import="mx.gob.redoaxaca.cednna.domino.Valor" %>
<!DOCTYPE html>
<html>
	<head>
		<title>Mis derechos</title>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
			<link rel="shortcut icon" type="${request.getContextPath()}/img/favicon.ico" href="${request.getContextPath()}/img/favicon.ico" type="image/x-icon"/>
			
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
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'default.css')}" type="text/css">
	    <link rel="stylesheet" href="${resource(dir: 'css', file: 'default.date.css')}" type="text/css">
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'default.time.css')}" type="text/css">

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
							
	<!-- Scripts de google plus y twitter-->						
	<script src="https://apis.google.com/js/platform.js" async defer>
	  {lang: 'es'}
	</script>	
	<script>!function(d,s,id){var js,fjs=d.getElementsByTagName(s)[0];if(!d.getElementById(id)){js=d.createElement(s);js.id=id;js.src="https://platform.twitter.com/widgets.js";fjs.parentNode.insertBefore(js,fjs);}}(document,"script","twitter-wjs");</script>
			<g:javascript src="jquery-1.10.2.js" />
				
	<g:layoutHead/>
		<r:layoutResources />
	</head>							
<body class="tm-background">
		<g:if test="${Valor.findByKey("suspendidoHabilitado")?.valor?.equals('true')}">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'bootstrap.min.css')}" type="text/css">
			<g:javascript src="bootstrap.js" />	
		</g:if>
		<g:overlayer/>
		
			<div id="fb-root"></div>
			<script>(function(d, s, id) {
			  var js, fjs = d.getElementsByTagName(s)[0];
			  if (d.getElementById(id)) return;
			  js = d.createElement(s); js.id = id;
			  js.src = "http://connect.facebook.net/es_LA/sdk.js#xfbml=1&version=v2.0";
			  fjs.parentNode.insertBefore(js, fjs);
			}(document, 'script', 'facebook-jssdk'));
			</script>


			<nav class="tm-navbar uk-navbar">
				<div class="uk-container uk-container-center">
				<div class="uk-grid" >
				<div class="uk-width-medium-8-10 uk-width-large-7-10">	
					<a class="uk-hidden-small" href="http://www.sidnna.oaxaca.gob.mx" target="_blank"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logostop_01.png" title="CEDNNA" alt="CEDNNA" target="_blank"></a>
					<a class="uk-hidden-small" href="http://www.oaxaca.gob.mx/" target="_blank"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logo-g-cafe.png" title="Gobierno del Estado de Oaxaca" alt="Gobierno del Estado de Oaxaca"></a>
					<a class="uk-hidden-small" href="http://www.bienestar.oaxaca.gob.mx" target="_blank"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logo-bien-n.png" title="Generando Bienestar" alt="Generando Bienestar"></a>
					<a class="uk-hidden-small" href="http://www.oaxtransparente.oaxaca.gob.mx/" target="_blank"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logostop_04.png" title="OaxTransparente" alt="OaxTransparente"></a>
				</div>
				<div class="uk-width-medium-2-10 uk-width-large-3-10">	
					<ul class="uk-navbar-nav uk-hidden-small">

						<li><g:link class="iconobar icono_home" action="indicadores" data-uk-tooltip="{pos:'bottom'}" title="Inicio"></g:link></li>
<%--						<li><g:link controller="Indicador" class="iconobar icono_catalogos" data-uk-tooltip="{pos:'bottom'}" title="Catálogo de Indicadores de Infancia y Adolescencia"></g:link></li>--%>
						<li><g:link class="iconobar icono_directorio" controller="publico" action="directorio" data-uk-tooltip="{pos:'bottom'}" title="Directorio"></g:link></li>
						<li><g:link class="iconobar icono_contacto" controller="publico" action="contacto" data-uk-tooltip="{pos:'bottom'}" title="Contacto"></g:link></li>
						<li><g:link class="iconobar icono_catalogos"  controller="publico" action="descargarCatalogoIndicadores" data-uk-tooltip="{pos:'bottom'}" title="Catálogo de indicadores"></g:link></li>
						<li><g:link class="iconobar icono_ayuda"  controller="publico" action="descargarManual" data-uk-tooltip="{pos:'bottom'}" title="Ayuda"></g:link></li>
					</ul>
				</div>
				<div class="uk-width-1-10">
					<a href="#tm-offcanvas" class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas></a>
				</div>

				<div class="uk-navbar-brand uk-navbar-center uk-visible-small">
					<img src="${request.getContextPath()}/img/logomini_cednna.png"  title="CEDNNA" alt="CEDNNA" class="fondologomini">
				</div>

				</div>
				</div>
			</nav>
	
		
			<div class="uk-container uk-container-center altopag">
				<div data-uk-grid-margin>
				<div class="uk-container-center">
					<g:layoutBody/>
				</div>
				</div>

			</div>
		

		<div class="tm-footer">
			<div class="uk-container uk-container-center">
				<p><strong>Mis derechos,</strong> Sistema de Monitoreo de Derechos de la Niñez y Adolescencia en Oaxaca, es una plataforma de <a href="https://github.com/redoaxacadetodos/conocemisderechos.git">código abierto</a> del Gobierno del Estado de Oaxaca que forma parte de su política transversal de Derechos Humanos, y su política de Transparencia, Participación Ciudadana y Gobierno abierto.</p>
				<a href="http://www.redoaxaca.oaxaca.gob.mx/" target="_blank"><img src="${request.getContextPath()}/img/logored.png"></a>
				<hr></hr>
				<div class="uk-grid">
                     <div class="uk-width-1-3"><div class="uk-panel">
                     	<a href="http://www.oaxaca.gob.mx" target="_blank"><p>www.oaxaca.gob.mx</a>
                     	<br> Gobierno del Estado de Oaxaca 2010-2016</p>
						<img src="${request.getContextPath()}/img/logo_cednna_footer.png">
						<p>Av. San Felipe del Agua #836 Col. San Felipe del Agua, Oaxaca de Juárez, Oax., C.P. 68020	
						<br>Tel: (951) 5201146
						<a href="http://www.sidnna.oaxaca.gob.mx" target="_blank"><br>www.sidnna.oaxaca.gob.mx</a></p></div></div>
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

        <div class="uk-container uk-container-center">
            <div class="banco">
                <p>
                    <strong>*</strong>Certamen convocado por la Auditoría Superior de la Federación (ASF), el Banco Mundial (BM), el Instituto Nacional de Transparencia, Acceso a la Información y Protección de Datos Personales (INAI), el Instituto Nacional de Administración Pública (INAP) y la Secretaría de la Función Pública (SFP). Los resultados y toda la información relacionada con el certamen pueden consultarse en: <a target="_blank" href="http://premiotransparencia.org.mx"><span class="link-certamen">http://premiotransparencia.org.mx</span></a>
                </p>
            </div>
        </div>

		<div id="tm-offcanvas" class="uk-offcanvas">

			<div class="uk-offcanvas-bar">

				<ul class="uk-nav uk-nav-offcanvas" data-uk-nav="{multiple:true}">
					<li class="uk-parent uk-active"><a href="">Índice</a>
						<ul class="uk-nav-sub">
						<li><g:link controller="publico" action="indicadores">Inicio</g:link></li>
						<li><g:link controller="publico" action="directorio">Directorio</g:link></li>
						<li><g:link controller="publico" action="contacto" >Contacto</g:link></li>
						<li><g:link controller="publico" action="descargarCatalogoIndicadores">Catálogo de indicadores</g:link></li>	
						<li><g:link controller="publico" action="descargarManual">Ayuda</g:link></li>	
						</ul>
					</li>

				</ul>

			</div>

		</div>
	</body>
	
</html>
