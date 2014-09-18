
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
			<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
		
	</head>
	<body>
		<h2>Contacto</h2>
		<div class="uk-grid">
		  <div class="uk-width-1-2">
		  	<br>
		  	<g:form action="enviarMensaje"  name="frmVariable">
				<fieldset class="uk-form ">
					<div class="fieldcontain uk-form-row">
						<label class="uk-form-label" for="nombre"> 
							<g:message code="contacto.nombre.label" default="Nombre" /> *
						</label>
						<div class="uk-form-controls">
							<g:field name="nombre" type="text" value="" required="" class="uk-width-2-3"/>
						</div>
					</div>
					<div class="fieldcontain uk-form-row">
						<label class="uk-form-label" for="correo"> 
							<g:message code="contacto.correo.label" default="Correo electrónico" /> *
						</label>
						<div class="uk-form-controls">
							<g:field name="correo" type="email" value="" required="" class="uk-width-2-3"/>
						</div>
					</div>
					<div class="fieldcontain uk-form-row">
						<label class="uk-form-label" for="asunto"> 
							<g:message code="contacto.asunto.label" default="Asunto" />
						</label>
						<div class="uk-form-controls">
							<g:field name="asunto" type="text" value="" class="uk-width-2-3"/>
						</div>
					</div>
					<div class="fieldcontain uk-form-row">
						<label class="uk-form-label" for="mensaje"> 
							<g:message code="contacto.mensaje.label" default="Mensaje" /> *
						</label>
						<div class="uk-form-controls">
							<g:textArea name="mensaje" required="" class="uk-width-2-3"></g:textArea>
						</div>
					</div>
					<input type="submit" value="Enviar mensaje" class="uk-button"/>
					<br/>
					<b>Ayúdanos a mejorar.</b> 
					Por favor comunícate con nosotros para dudas, sugerencias y reportes de errores en este sistema. Agradecemos tu colaboración.
				</fieldset>
			</g:form>
			
		</div>
		  <div class="uk-width-1-2">
			<g:each var="${c }" in="${contactos }">
			<div>
			 <b>${c.titulo }</b>	<br><br>
			${c.grado } ${c.nombre } ${c.paterno } ${c.materno } <br>
			${c.descripcion } <br>
			<g:if test="${c.email }">
			 	<br><br> <b>E-MAIL</b> <br>
			 	<g:each var="${e }" in="${c.email }">
			 		${e?.email } <br>
			 	</g:each>			 	
			</g:if>
			<g:if test="${c.domicilio }">
			 	<br><br> <b>DOMICILIO</b> <br><br>
			 	${c.domicilio }
			</g:if>
			<g:if test="${c.telefono }">
			 	<br><br> <b>TELÉFONO</b> <br><br>
			 	${c.telefono }
			</g:if>
			</div>
			<br><br>
			</g:each>
		  </div>
		</div>
		
		
		
	</body>
</html>



<div class="container">
    <div class="row">
        <div class="col-xs-12 col-sm-6 col-md-6">
            <div class="well well-sm">
                <div class="row">
                    <div class="col-sm-6 col-md-4">
                        <img src="http://placehold.it/380x500" alt="" class="img-rounded img-responsive" />
                    </div>
                    <div class="col-sm-6 col-md-8">
                        <h4>
                            Bhaumik Patel</h4>
                        <small><cite title="San Francisco, USA">San Francisco, USA <i class="glyphicon glyphicon-map-marker">
                        </i></cite></small>
                        <p>
                            <i class="glyphicon glyphicon-envelope"></i>email@example.com
                            <br />
                            <i class="glyphicon glyphicon-globe"></i><a href="http://www.jquery2dotnet.com">www.jquery2dotnet.com</a>
                            <br />
                            <i class="glyphicon glyphicon-gift"></i>June 02, 1988</p>
                        <!-- Split button -->
                        <div class="btn-group">
                            <button type="button" class="btn btn-primary">
                                Social</button>
                            <button type="button" class="btn btn-primary dropdown-toggle" data-toggle="dropdown">
                                <span class="caret"></span><span class="sr-only">Social</span>
                            </button>
                            <ul class="dropdown-menu" role="menu">
                                <li><a href="#">Twitter</a></li>
                                <li><a href="https://plus.google.com/+Jquery2dotnet/posts">Google +</a></li>
                                <li><a href="https://www.facebook.com/jquery2dotnet">Facebook</a></li>
                                <li class="divider"></li>
                                <li><a href="#">Github</a></li>
                            </ul>
                        </div>
                    </div>
                </div>
            </div>
        </div>
    </div>
</div>



			
