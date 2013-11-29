
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
			<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
		
	</head>
	<body>
<%--		<p>--%>
<%--			En la sección directorio de la página econtrará la información de contacto.--%>
<%--		</p>--%>
		
		<g:each var="${c }" in="${contactos }">
			<div>
			<br><br> <b>${c.titulo }</b>	<br><br>
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
		</g:each>
		
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



			
