<%@ page import="mx.gob.redoaxaca.cednna.domino.FrecuenciaIndicador" %>

<g:hiddenField name="variable.id" value="${frecuenciaIndicadorInstance?.variable?.id }"/>

<div class="fieldcontain ${hasErrors(bean: frecuenciaIndicadorInstance, field: 'frecuencia', 'error')} required uk-form-row">
	<label for="frecuencia" class="uk-form-label">
		<g:message code="frecuenciaIndicador.frecuencia.label" default="Frecuencia" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select id="frecuencia" name="frecuencia.id" from="${mx.gob.redoaxaca.cednna.domino.Frecuencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${frecuenciaIndicadorInstance?.frecuencia?.id}" class="many-to-one"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: frecuenciaIndicadorInstance, field: 'fechaActualizacion', 'error')} required uk-form-row">
	<label for="fechaActualizacion" class="uk-form-label">
		<g:message code="frecuenciaIndicador.fechaActualizacion.label" default="Fecha de actualización" />
		<span class="required-indicator">*</span>
	</label>

	<div id="fechaActualizacion" class="uk-form-controls date">
			<input name="fechaActualizacion" 
				type="text" value="${frecuenciaIndicadorInstance?.fechaActualizacion.format('yyyy-MM-dd') }" required="required"
				size="16" data-date-format="YYYY-MM-DD"
				class="form-control"/>
	</div>
</div>

<g:hiddenField name="ano" value="2010"/>

<script type="text/javascript">
    $(function () {
        $('.date').datetimepicker({
        	language:'es',
            pickTime: false
        });
        $('.bootstrap-datetimepicker-widget').hide();
    });
    
</script>