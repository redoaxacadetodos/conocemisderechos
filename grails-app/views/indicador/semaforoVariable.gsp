
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Dependencia" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>
<%@ page import="groovy.time.TimeCategory" %>
<%@ page contentType="text/html" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="main">
		<script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />

	  <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
	  <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
	  <link href="${resource(dir: 'bootstrap', file: 'css/bootstrap.min.css')}" rel="stylesheet">	
	  <script src="${resource(dir: 'bootstrap', file: 'js/bootstrap.min.js')}"></script>
	  
	</head>
	<body>		
		<div id="division">
			<h3>SEM&Aacute;FORO DE CONTROL</h3>
			<h4>ESTATUS DE ACTUALIZACI&Oacute;N POR VARIABLE</h4>
			
			<div>
				<img src="${resource(dir: 'img', file: 'star.png')}" width="20px" height="20px"> La información se encuentra actualizada<br>
				<img src="${resource(dir: 'img', file: 'edit.png')}" width="20px" height="20px"> La información necesita ser actualizada<br><br>
			</div>
			<div id="semaforo">
				<table class="table nuevocolortabla">
					<thead>
						<tr>
							<th>CLAVE</th><th>VARIABLE</th><th>MEDICI&Oacute;N</th><th>&Uacute;LTIMA ACTUALIZACI&Oacute;N</th><th>ESTATUS</th>
							<g:if test="${rol=='1'}">
								<th>ENVIAR</th>
							</g:if>
						</tr>
					</thead>
					<tbody>
						<g:each in="${filas }" var="r">
							<tr>
								<td>${r[0] }</td>
								<td>${r[1] }</td>
								<td>${r[2] }</td>
								<td><g:formatDate type="date" style="LONG" date="${r[3] }"/></td>
								<td>
								<g:if test="${r[4] }">
									<img src="${resource(dir: 'img', file: 'star.png')}" width="20px" height="20px"> 
								</g:if>
								<g:else>
									<img src="${resource(dir: 'img', file: 'edit.png')}" width="20px" height="20px">
								</g:else>
								</td>
							</tr>
						</g:each>
					</tbody>
				</table>	
			</div>
		</div>
	</body>
</html>


			
