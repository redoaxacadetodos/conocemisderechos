<html>
<head>
	<meta name='layout' content='mainLogin'/>
	<title>CEDNNAS</title>

</head>

<body>
<div class="titulo"><img src="${request.getContextPath()}/img/logo_sist.png"  title="CEDNNA" alt="CEDNNA" witdh="283px" height="215px"></div>
<div class="uk-panel uk-panel-box uk-container-center uk-width-1-3">
							<form action='${postUrl}' method='POST' id='loginForm' class="uk-form uk-form-stacked" autocomplete='off'>
                            
                            <h3 class="uk-panel-title">Entrada al sistema</h3>

									<div class="uk-form-row">
										<label class="uk-form-label" for="form-s-it">Usuario</label>
										
											<input type="text" name='j_username' id='username' placeholder="Usuario" class="uk-form-large uk-form-width-large text_">
										
									</div>
									<div class="uk-form-row">
										<label class="uk-form-label" for="form-s-ip">Clave</label>
										
											<input type="password" placeholder="Clave" class="uk-form-large uk-form-width-large text_" name='j_password' id='password'>
										
									</div>

									<div class="uk-form-row">
										<label class="uk-form-label" for='remember_me'>Recordarme</label>
										
											<input type='checkbox' class='chk' name='${rememberMeParameter}' id='remember_me' <g:if test='${hasCookie}'>checked='checked'</g:if>/>
										
									</div>

							

							<input class="uk-button" type="submit">
							</form>

</div>
<script type='text/javascript'>
	<!--
	(function() {
		document.forms['loginForm'].elements['j_username'].focus();
	})();
	// -->
</script>
</body>
</html>
