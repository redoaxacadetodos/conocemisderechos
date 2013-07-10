<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>

<h3>Datos generales del indicador</h3>
<br>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'nombre', 'error')} required">
	<label for="nombre">
		<g:message code="indicador.nombre.label" default="Nombre del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${indicadorInstance?.nombre}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'objetivo', 'error')} required">
	<label for="objetivo">
		<g:message code="indicador.objetivo.label" default="Objetivo del indicador" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="objetivo" required="" value="${indicadorInstance?.objetivo}"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'anio', 'error')} required">
	<label for="anio">
		<g:message code="indicador.anio.label" default="Año " />
		<span class="required-indicator">*</span>
	</label>
	<g:field name="anio" type="number" value="${indicadorInstance.anio}" required=""/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'sentido', 'error')} required">
	<label for="sentido">
		<g:message code="indicador.sentido.label" default="Sentido de interpretacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="sentido" name="sentido.id" from="${mx.gob.redoaxaca.cednna.domino.Sentido.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.sentido?.id}" class="many-to-one"/>
</div>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
	<label for="dependencia">
		<g:message code="indicador.dependencia.label" default="Dependencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.dependencia?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'ejecutora', 'error')} required">
	<label for="ejecutora">
		<g:message code="indicador.ejecutora.label" default="Unida Ejecutora" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="ejecutora" name="ejecutora.id" from="${mx.gob.redoaxaca.cednna.domino.UnidadEjecutora.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.ejecutora?.id}" class="many-to-one"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} required">
	<label for="frecuencia">
		<g:message code="indicador.frecuencia.label" default="Frecuencia" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="frecuencia" name="frecuencia.id" from="${mx.gob.redoaxaca.cednna.domino.Frecuencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.frecuencia?.id}" class="many-to-one"/>
</div>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'comentarios', 'error')} ">
	<label for="comentarios">
		<g:message code="indicador.comentarios.label" default="Comentarios" />
		
	</label>
	<g:textField name="comentarios" value="${indicadorInstance?.comentarios}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'mediosVerificacion', 'error')} ">
	<label for="mediosVerificacion">
		<g:message code="indicador.mediosVerificacion.label" default="Medios Verificacion" />
		
	</label>
	<g:textField name="mediosVerificacion" value="${indicadorInstance?.mediosVerificacion}"/>
</div>

<br>


<h3>Datos del area responsable del indicador </h3>
<br>



<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'nombreResponsable', 'error')} required">
	<label for="nombreResponsable">
		<g:message code="indicador.nombreResponsable.label" default="Nombre Responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombreResponsable" required=""value="${indicadorInstance?.nombreResponsable}"/>
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'fechaActualizacion', 'error')} required">
	<label for="fechaActualizacion">
		<g:message code="indicador.fechaActualizacion.label" default="Fecha Actualizacion" />
		<span class="required-indicator">*</span>
	</label>
	<g:datePicker name="fechaActualizacion" precision="day"  value="${indicadorInstance?.fechaActualizacion}"  />
</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'areaResponsable', 'error')} required">
	<label for="areaResponsable">
		<g:message code="indicador.areaResponsable.label" default="Area Responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="areaResponsable" required="" value="${indicadorInstance?.areaResponsable}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'mailResponsable', 'error')} required">
	<label for="mailResponsable">
		<g:message code="indicador.mailResponsable.label" default="Mail Responsable" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="mailResponsable" required="" value="${indicadorInstance?.mailResponsable}"/>

</div>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'publico', 'error')} ">
	<label for="publico">
		<g:message code="indicador.publico.label" default="Publico" />
		
	</label>
	<g:checkBox name="publico" value="${indicadorInstance?.publico}" />
</div>

<br>




<h3>Datos para el calculo del indicador</h3>
<br>

<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'formula', 'error')} required">
	<label for="formula">
		<g:message code="indicador.formula.label" default="Formula" />
		<span class="required-indicator">*</span>
	</label>
	<g:select id="formula" name="formula.id" from="${mx.gob.redoaxaca.cednna.domino.Formula.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.formula?.id}" class="many-to-one"/>
</div>


<div class="fieldcontain ${hasErrors(bean: indicadorInstance, field: 'variables', 'error')} ">
	<label for="variables">
		<g:message code="indicador.variables.label" default="Variables" />
		
	</label>
	<g:select name="variables" from="${mx.gob.redoaxaca.cednna.domino.Variable.list()}" multiple="multiple" optionKey="id" optionValue="descripcion"    size="5" value="${indicadorInstance?.variables*.id}" class="many-to-many"/>
</div>

