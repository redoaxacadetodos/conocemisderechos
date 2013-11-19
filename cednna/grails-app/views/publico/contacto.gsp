
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


			
