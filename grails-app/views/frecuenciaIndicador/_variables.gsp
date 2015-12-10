<%@ page import="mx.gob.redoaxaca.cednna.domino.CatOrigenDatos" %>

<g:each var="v" in="${variables }" >
	<g:set var="catOrigenDatosInstance" value="${CatOrigenDatos.findByClave(v?.claveVar) }" />
	
	<g:if test="${catOrigenDatosInstance }">
	
		<g:if test="${admin || catOrigenDatosInstance.dependencia == dependencia }">
			<g:hiddenField name="idVariable" value="${v.id }"/>
		</g:if>
		
		<div class="fieldcontain ${hasErrors(bean: frecuenciaIndicadorInstance, field: 'frecuencia', 'error')} required uk-form-row">
			<label for="frecuencia" class="uk-form-label">
				<g:message code="frecuenciaIndicador.frecuencia.label" default="Variable" />
				<span class="required-indicator">*</span>
			</label>
			<div class="uk-form-controls">
				${catOrigenDatosInstance?.descripcion }
			</div>
		</div>
		
		<div class="fieldcontain ${hasErrors(bean: frecuenciaIndicadorInstance, field: 'frecuencia', 'error')} required uk-form-row">
			<label for="frecuencia" class="uk-form-label">
				<g:message code="frecuenciaIndicador.frecuencia.label" default="Frecuencia" />
				<span class="required-indicator">*</span>
			</label>
			<div class="uk-form-controls">
				<g:if test="${admin || catOrigenDatosInstance.dependencia == dependencia }">
					<g:select id="frecuencia" name="frecuencia" from="${mx.gob.redoaxaca.cednna.domino.Frecuencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${frecuenciaIndicadorInstance?.frecuencia?.id}" class="many-to-one"/>
				</g:if>
				<g:else>
					${frecuenciaIndicadorInstance?.frecuencia?.descripcion}
				</g:else>
				
			</div>
		</div>
	
		<div class="fieldcontain ${hasErrors(bean: frecuenciaIndicadorInstance, field: 'fechaActualizacion', 'error')} required uk-form-row">
			<label for="fechaActualizacion" class="uk-form-label">
				<g:message code="frecuenciaIndicador.fechaActualizacion.label" default="Fecha de actualización" />
				<span class="required-indicator">*</span>
			</label>
			<div id="fechaActualizacion" class="uk-form-controls ${admin || catOrigenDatosInstance.dependencia == dependencia?'date':'' }">
				
				<g:if test="${admin || catOrigenDatosInstance.dependencia == dependencia }">
					<input name="fechaActualizacion" 
					type="text" value="${frecuenciaIndicadorInstance?.fechaActualizacion }" required="required"
					size="16" data-date-format="YYYY-MM-DD"
					class="form-control"/>
				</g:if>
				<g:else>
					<g:formatDate type="date" style="LONG" date="${frecuenciaIndicadorInstance?.fechaActualizacion }"/>
				</g:else>
				
			</div>
		</div>
		
<%--	<div class="fieldcontain ${hasErrors(bean: frecuenciaIndicadorInstance, field: 'ano', 'error')} required uk-form-row">--%>
<%--		<label for="ano" class="uk-form-label">--%>
<%--			<g:message code="frecuenciaIndicador.ano.label" default="Ano" />--%>
<%--			<span class="required-indicator">*</span>--%>
<%--		</label>--%>
<%--		<div class="uk-form-controls">--%>
<%--			<g:if test="${admin || catOrigenDatosInstance.dependencia == dependencia }">--%>
<%--				<g:select name="ano" from="${(2010..2013).toArray() }" value="${frecuenciaIndicadorInstance?.ano}" required=""/>--%>
<%--			</g:if>--%>
<%--			<g:else>--%>
<%--				${frecuenciaIndicadorInstance?.ano}--%>
<%--			</g:else>--%>
<%--		</div>--%>
<%--	</div>--%>
		<g:if test="${admin || catOrigenDatosInstance.dependencia == dependencia }">
			<g:hiddenField name="ano" value="2010"/>
		</g:if>
		<br>
	</g:if>
</g:each>

<script type="text/javascript">
    $(function () {
        $('.date').datetimepicker({
            pickTime: false, 
            language:'es'
        });
    });
    $('.bootstrap-datetimepicker-widget').hide();
</script>


<button type="submit" class="uk-button uk-button-primary">
	<i class="uk-icon-small uk-icon-edit"></i> ${message(code: 'default.button.create.label', default: 'Create')}
</button>