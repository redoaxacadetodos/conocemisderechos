<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>
</head>
<body>
  <div class="body">
  
  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="indicador.nombre.label" default="Nombre del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<div>${indicadorInstance?.nombre}</div>
</div>
  
  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	<label for="objetivo">
		<g:message code="indicador.objetivo.label" default="Objetivo del indicador" />
		<span class="required-indicator">*</span>
	</label>
		<div>${indicadorInstance?.objetivo}</div>
</div>
 
 
<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'formula', 'error')} required">
	<label for="formula">
		<g:message code="indicador.formula.label" default="Formula de calculo" />
		<span class="required-indicator">*</span>
	</label>
		<div>${indicadorInstance?.formula?.sentencia}<div>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Frecuencia de medicion" />
		<span class="required-indicator">*</span>
	</label>
	<div>${indicadorInstance?.frecuencia?.descripcion}</div>
</div>


  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">
	<label for="fechaActualizacion">
		<g:message code="indicador.fechaActualizacion.label" default="Fecha Actualizacion" />
		<span class="required-indicator">*</span>
	</label>
	<div>${indicadorInstance?.fechaActualizacion}</div>
</div>
  
  
  <div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="indicador.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<div>${indicadorInstance?.dependencia?.descripcion}</div>   
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	<label for="ejecutora">
		<g:message code="indicador.ejecutora.label" default="Unidad Ejecutora" />
		<span class="required-indicator">*</span>
	</label>
		<div>${indicadorInstance?.ejecutora?.descripcion}</div>
</div>
  
  
  
  
  
  <h3>Resultados de indicador </h3>
  
  
  <table>
  <tbody>
  <tr><th>Año </th><th>Resultado</th></tr>
  <g:each  in="${indicadorInstance?.resultados}" var="${resul}">
  <tr>
	  <td>resul.anio</td><td>resul.resultado</td>
  </tr>
  </g:each>
  </tbody>
  </table>
  
 
  </div>
</body>
</html>