<!DOCTYPE html>
<html>
	<head>
		<title>CEDNNA</title>
		  <meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />
	
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.min.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'uikit.css')}" type="text/css">
			<link rel="stylesheet" href="${resource(dir: 'css', file: 'docs.css')}" type="text/css">
		
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.10.2/jquery.min.js"></script>
	   <g:javascript src="uikit.min.js" />
	
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
                <a class="uk-logo-edos uk-hidden-small" ><img src="${request.getContextPath()}/img/logos_edo.png"  title="Oaxaca de todos" alt="Oaxaca de todos"></a>

				<a href="#tm-offcanvas" class="uk-navbar-toggle uk-visible-small" data-uk-offcanvas></a>

				<div class="uk-navbar-brand uk-navbar-center uk-visible-small"><img src="${request.getContextPath()}/img/logo02.png"  title="CEDNNA" alt="CEDNNA"></div>

			</div>
		</nav>

		<div class="tm-middle">
			<div class="uk-container uk-container-center">

				<div class="uk-grid" data-uk-grid-margin>
				
					<div class="tm-sidebar uk-width-medium-1-4 uk-hidden-small">

						<ul class="tm-nav uk-nav" data-uk-nav>

							<li class="uk-nav-header">Menu</li>
							<li><g:link controller="Indicador">Indicadores</g:link></li>
							<li><g:link controller="Formula">Formulas</g:link></li>
							<li><g:link controller="Variable">Origen de datos</g:link></li>
							<li><g:link controller="logout">Cerrar sesion </g:link></li>

						</ul>

					</div>
                
				<div class="tm-main uk-width-medium-3-4">
							
							
	
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
							<li><g:link controller="Indicador">Indicadores</g:link></li>
							<li><g:link controller="Formula">Fórmulas</g:link></li>
							<li><g:link controller="Variable">Origen de datos</g:link></li>
							<li><g:link controller="logout">Cerrar sesión </g:link></li>
					
						</ul>
					</li>

				</ul>

			</div>

		</div>
	</body>
	
</html>
