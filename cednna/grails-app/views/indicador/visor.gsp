<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
<br />
<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

		
				<li><g:link class="list" action="list">Listado</g:link></li>


		</ul>
</nav>

<h3>Datos generales del indicador</h3>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">
	<label class="uk-form-label" for="nombre">
		<b>
		<g:message code="indicador.nombre.label" default="Nombre del indicador: " />
		</b>
	</label>
	<label>${indicadorInstance?.nombre}</label>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	<label class="uk-form-label" for="objetivo">
		<b>
		<g:message code="indicador.objetivo.label" default="Objetivo del indicador: " />
		</b>
	</label>
	<p>${indicadorInstance?.objetivo}</p>
	
</div>

<g:if test="${dep}">	

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label class="uk-form-label"for="dependencia">
	<b>
		<g:message code="indicador.dependencia.label" default="Dependencia responsable: " />
	</b>	
	</label>
	<g:hiddenField name="dependencia.id" value="${dep?.id}"/>
	<label>${dep?.descripcion}</label>
</div>


</g:if>
<g:else>
<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label class="uk-form-label"for="dependencia">
	<b>
		<g:message code="indicador.dependencia.label" default="Dependencia responsable: " />
	</b>	
	</label>
	<label>${indicadorInstance?.dependencia?.descripcion}</label>
</div>
</g:else>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	<label class="uk-form-label"for="ejecutora">
	<b>
		<g:message code="indicador.ejecutora.label" default="Unidad administrativa ejecutora: " />
	</b>	
	</label>
<label>${indicadorInstance?.ejecutora?.descripcion}</label>
</div>



<%--<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'anio', 'error')} required">--%>
<%--	<label class="uk-form-label"for="anio">--%>
<%--		<g:message code="indicador.anio.label" default="Año" />--%>
<%--		--%>
<%--	</label>--%>
<%--		<select id="anio" name="anio" ></select> --%>

<%--</div>--%>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'sentido', 'error')} required">
	<label class="uk-form-label"for="sentido">
	<b>
		<g:message code="indicador.sentido.label" default="Sentido esperado: " />
	</b>	
	</label>
<label>${indicadorInstance?.sentido?.descripcion}</label>
</div>





<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="Frecuencia de medici&oacute;n: " />
	</b>	
	</label>
	<label>${indicadorInstance?.frecuencia?.descripcion}</label>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} ">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="Periodo: " />
	</b>
	</label>
	<label>${indicadorInstance?.periodo?.descripcion}</label>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="Unidad de medida: " />
	</b>	
	</label>
	<label>${indicadorInstance?.uMedida?.descripcion}</label>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="M&oacute;dulo:" />
	</b>	
	</label>
	<label>${indicadorInstance?.division?.eje?.descripcion}</label>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="Categor&iacute;a: " />
	</b>	
	</label>
	<label>${indicadorInstance?.division?.descripcion}</label>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} ">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="Eje: " />
	</b>
	</label>
	<label>${indicadorInstance?.pnDesarrollo?.descripcion}</label>
</div>
<br>
<div id="divPND">
	
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')}">
		<label class="uk-form-label" for="nombre">
		<b>
			<g:message code="indicador.nombre.label" default="Tema: " />
		</b>
		</label>
		<label>${indicadorInstance?.tema}</label>
		</div>
		
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
			<b>
				<g:message code="indicador.nombre.label" default="Objetivo PED: "/>
			</b>
			</label>
		<label>${indicadorInstance?.objetivoPND}</label>
		</div>
		
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
			<b>
				<g:message code="indicador.nombre.label" default="Estrategia: " />
			</b>
			</label>
			<label>${indicadorInstance?.estrategia}</label>
		</div>
		
		<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} ">
			<label class="uk-form-label" for="nombre">
			<b>
				<g:message code="indicador.nombre.label" default="Nombre del Programa: " />
			</b>
			</label>
			<label>${indicadorInstance?.nombrePrograma}</label>
		</div>
	
	
</div>
<br>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} ">
	<label class="uk-form-label"for="frecuencia">
	<b>
		<g:message code="indicador.frecuencia.label" default="Indicador ODM: " />
	</b>
	</label>
	<label>${indicadorInstance?.objetivosMilenio?.descripcion}</label>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'mediosVerificacion', 'error')} ">
	<label class="uk-form-label"for="mediosVerificacion">
	<b>
		<g:message code="indicador.mediosVerificacion.label" default="Medios de Verificaci&oacute;n: " />
	</b>	
	</label>
	<p>${indicadorInstance?.mediosVerificacion}</p>
</div>


<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">
	<label class="uk-form-label"for="comentarios">
	<b>
		<g:message code="indicador.comentarios.label" default="Fuente de informacion del indicador: " />
	</b>
	</label>
	<p>${indicadorInstance?.fuenteInformacion}</p>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">
	<label class="uk-form-label"for="comentarios">
	<b>
		<g:message code="indicador.comentarios.label" default="Comentarios adicionales al indicador:" />
	</b>
	</label>
	<p>${indicadorInstance?.comentarios}</p>
</div>




<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'publico', 'error')} ">
	<label class="uk-form-label"for="publico">
	<b>
		<g:message code="indicador.publico.label" default="P&uacute;blico:" />
	</b>
	</label>
	<g:checkBox name="publico" value="${indicadorInstance?.publico}" disabled=""/>
</div>


<br>
<br>


<h3>Datos del responsable del indicador</h3>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'nombreResponsable', 'error')} required">
	<label class="uk-form-label"for="nombreResponsable">
	<b>
		<g:message code="indicador.nombreResponsable.label" default="Nombre del responsable:" />
	</b>	
	</label>
	<label>${indicadorInstance?.nombreResponsable}</label>
</div>



<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'areaResponsable', 'error')} required">
	<label class="uk-form-label"for="areaResponsable">
	<b>
		<g:message code="indicador.areaResponsable.label" default="Área del responsable:"  />
	</b>
	</label>
		<label>${indicadorInstance?.areaResponsable}</label>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'mailResponsable', 'error')} required">
	<label class="uk-form-label"for="mailResponsable">
	<b>
		<g:message code="indicador.mailResponsable.label" default="Email de contacto:" />
	</b>
	</label>
		<label>${indicadorInstance?.mailResponsable}</label>
</div>

<div class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">
	<label class="uk-form-label"for="fechaActualizacion">
	<b>
		<g:message code="indicador.fechaActualizacion.label" default="Fecha de actualizaci&oacute;n:"  />
	</b>
	</label>
	<label>${indicadorInstance?.fechaActualizacion?.format('dd/MM/yyyy')}</label>
	
	

	
</div>
  
  
  
  
<%--  --%>
<%--  <h3>Resultados del indicador por año </h3>--%>
<%--  --%>
<%--  --%>
<%--  <table>--%>
<%--  <tbody>--%>
<%--  <tr><th>Año </th><th>Resultado</th></tr>--%>
<%--	 <g:each in="${resultados}" var="resul">--%>
<%--	  <tr>--%>
<%--		  <td>${resul.anio}</td><td>${String.format(new Locale('es','MX'),'%1$,.2f',resul.indicador)}</td>--%>
<%--	  </tr>--%>
<%--	</g:each>--%>
<%--  </tbody>--%>
<%--  </table>--%>
<%--  --%>
<%-- --%>
<%--  </div>--%>
</body>
</html>
