<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>
</head>
<body>
<a href="#create-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>


<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li><g:link class="list" action="list">Origen de datos </g:link></li>
			
			<li class="uk-active"><g:link class="create" action="create">Nuevo origen de datos</g:link></li>
			

<br>
	<br>
	<h2>Resultados del Archivo Procesado</h2>
	<br>
	<br>
	
	<h4>Total de registros procesados:</h4> ${ total }
	<br>
	<h4>Registros cargados exitosamente: </h4> ${ buenos }

	<br>
	<br>
	
	<h4>Registros que no fueron cargados debido a un problema: </h4>${ malos }
	
	
	<br>
	<br>
	<g:if test="${mensaje.size()>0}">
	
	<h4>${mensaje} </h4>
	<br>
	<br>
	</g:if>
	<h4>Detalle de registros no procesados : </h4>
	
	<table  cellspacing="3" cellpadding="6">  
	<tbody>
	<tr><th>ID REGION</th><th>REGION</th><th>ID MUNICIPIO</th><th>MUNICIPIO</th><th>ID LOCALIDAD </th><th>LOCALIDAD</th><th>HOMBRES</th><th>MUJERES</th><th>TOTAL</th></tr>
	
	<g:each in="${rMalos}" status="i" var="row" >
	
	<tr>
	
			   <td> ${row.idRegion}</td>
			   <td> ${row.region}</td>
			   <td> ${row.idMunicipio}</td>
			   <td> ${row.municipio}</td>
		       <td> ${row.idLocalidad}</td>
			   <td> ${row.localidad}</td>
			   <td> ${row.hombres}</td>
			   <td> ${row.mujeres}</td>
			   <td> ${row.total}</td>
	
	
	
	</tr>
	</g:each>
			     
			     
	<tr>
		
		<td>
<%--			<g:form method="post" >--%>
<%--					<g:hiddenField name="claveSHA" value="${claveSHA}" />--%>
<%--				--%>
<%--					<fieldset class="form">--%>
<%--					--%>
<%--					</fieldset>--%>
<%--					<fieldset class="buttons">--%>
<%--						<g:actionSubmit class="btn btn-primary" action="confirmarV" value="Confirmar vuelos "  onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Esta seguro de confirmar estos vuelos?')}');"/>--%>
<%--						<br><br>--%>
<%--						<g:actionSubmit class="btn btn-secondary" action="cancelarV" value="Cancelar carga" onclick="return confirm('${message(code: 'default.button.delete.confirm.message', default: 'Esta seguro de eliminar estos vuelos?')}');"/>--%>
<%--						<br><br>--%>
<%--					</fieldset>--%>
<%--				</g:form>--%>
		</td>
	
	</tr>
	</tbody>
	</table>
			
	
	
</body>
</html>