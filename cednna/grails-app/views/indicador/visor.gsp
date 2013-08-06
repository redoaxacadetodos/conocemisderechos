<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  <h1>Datalle del indicador </h1>
  <br>
  <br>
  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">

		<h3><g:message code="indicador.nombre.label" default="Nombre del indicador" /></h2>
		
	
	<div>${indicadorInstance?.nombre}</div>
</div>
 <br> 

  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	
		<h3><g:message code="indicador.objetivo.label" default="Objetivo del indicador" /></h3>
	

		<div>${indicadorInstance?.objetivo}</div>
</div>
 <br>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'formula', 'error')} required">
	
		<h3><g:message code="indicador.formula.label" default="Formula de calculo" /></h3>
	

		<div>${indicadorInstance?.formula?.sentencia}<div>
</div>
<br>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	
		<h3><g:message code="indicador.frecuencia.label" default="Frecuencia de medicion" /></h3>
	

	<div>${indicadorInstance?.frecuencia?.descripcion}</div>
</div>

<br>

  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">

		<h3><g:message code="indicador.fechaActualizacion.label" default="Fecha Actualizacion" /></h3>
		
	
	<div>${indicadorInstance?.fechaActualizacion?.format('dd-MM-yyyy')}</div>
</div>
 <br> 

  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">

		<h3><g:message code="indicador.dependencia.label" default="Dependencia" /></h3>

	<div>${indicadorInstance?.dependencia?.descripcion}</div>   
</div>
<br>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	
		<h3><g:message code="indicador.ejecutora.label" default="Unidad Ejecutora" /></h3>
		

		<div>${indicadorInstance?.ejecutora?.descripcion}</div>
</div>
  
  
  
  
  
  <h3>Resultados del indicador por año </h3>
  
  
  <table>
  <tbody>
  <tr><th>Año </th><th>Resultado</th></tr>
	 <g:each in="${resultados}" var="resul">
	  <tr>
		  <td>${resul.anio}</td><td>${String.format(new Locale('es','MX'),'%1$,.2f',resul.indicador)}</td>
	  </tr>
	</g:each>
  </tbody>
  </table>
  
 
  </div>
</body>
</html>