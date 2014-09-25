<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<script>

function actualizar(tipo){
	$.ajax({type:'POST', 
        url:CONTEXT_ROOT+'/documento/actualizarNivel',
        data: {tipo:tipo},
        success:function(data,textStatus){
        	$('#divNiveles').html(data);
         },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $('#diverror').html(XMLHttpRequest.responseText);
        }});
}
$( document ).ready(function() {
	var tipoInicial = $("#tipo").val();
	window.onload = actualizar(tipoInicial);
});

</script>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'clave', 'error')} required uk-form-row">
	<label for="tipo" class="uk-form-label">
		<g:message code="documento.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select name="tipo" from="${[[k:2, v:'Marco Jurídico'], [k:3, v:'Centro de información'], [k:4, v:'Diagnósticos Municipales']] }"
			onchange="actualizar(this.value);"
			optionKey="k" optionValue="v"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'titulo', 'error')} required uk-form-row">
	<label for="titulo" class="uk-form-label">
		<g:message code="documento.titulo.label" default="Titulo" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:textField name="titulo" value="${documentoInstance?.titulo}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: sentidoInstance, field: 'url', 'error')} required uk-form-row">
	<label for="url" class="uk-form-label">
		<g:message code="documento.url.label" default="Documento" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:field name="url" type="file" value="${documentoInstance?.url}" required=""/>
	</div>
</div>

<div id="divNiveles" class="fieldcontain  required uk-form-row"></div>

