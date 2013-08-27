<!DOCTYPE html>
<html>
	<head>
		<title>CEDNNA</title>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.min.css')}" type="text/css">
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
			<div class="uk-container uk-container-center">

				<a class="uk-hidden-small" href="index.html"><img class="uk-margin uk-margin-remove" src="${request.getContextPath()}/img/logo.png" title="CEDNNA" alt="CEDNNA"></a>

				<ul class="uk-navbar-nav uk-hidden-small">
		<li><g:link class="list iconobar icono_calendar" action="calendario" data-uk-tooltip="{pos:'bottom'}" title="Calendario"></g:link></li>
		<li><g:link class="create iconobar icono_directorio" action="directorio" data-uk-tooltip="{pos:'bottom'}" title="Directorio"></g:link></li>
		<li><g:link class="create iconobar icono_contacto" action="contacto" data-uk-tooltip="{pos:'bottom'}" title="Contacto"></g:link></li>
		<li><g:link class="create iconobar icono_ayuda" action="ayuda" data-uk-tooltip="{pos:'bottom'}" title="ayuda"></g:link></li>	
				</ul>

				<a href="#tm-offcanvas" class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas></a>

				<div class="uk-navbar-brand uk-navbar-center uk-visible-small"><img src="${request.getContextPath()}/img/logo02.png"  title="CEDNNA" alt="CEDNNA"></div>

			</div>
		</nav>
		


		<div class="tm-middle">
			<div class="uk-container uk-container-center">

<div class="uk-grid">
<div class="uk-width-1-1">
	<ul class="uk-tab" data-uk-tab>
	<g:each var="eje" in="${mx.gob.redoaxaca.cednna.domino.Eje.list()}">
		<li><a href="#" onclick="${remoteFunction(
			controller:'publico',
			action: 'infoIndicador',
			update: 'division',
			id: eje.id)}" value="${eje.descripcion}" id="${eje.id}">${eje.descripcion}</a>
		</li>
					
	</g:each>
	</ul>
</div>
</div>	
				<div class="uk-grid" data-uk-grid-margin>
				
					<div class="tm-sidebar uk-width-medium-1-5 uk-hidden-small">

						<ul class="tm-nav uk-nav" data-uk-nav>

							<li class="uk-nav-header">Menu</li>
							<li><g:link controller="Indicador">Catálogo de Indicadores de Infancia y Adolescencia</g:link></li>
							<li><g:link controller="Indicador">Inversión Social e Infancia</g:link></li>						
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
