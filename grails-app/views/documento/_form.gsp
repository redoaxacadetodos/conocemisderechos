<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.TipoEje" %>
<script>

function actualizar(tipo){
	$.ajax({type:'POST', 
        url:CONTEXT_ROOT+'/documento/actualizarNivel',
        data: {tipo:tipo, nivel:${documentoInstance?.nivel!=null?documentoInstance?.nivel?.id:'null'}},
        success:function(data,textStatus){
        	$('#divNiveles').html(data);
         },
        error:function(XMLHttpRequest,textStatus,errorThrown){
            $('#diverror').html(XMLHttpRequest.responseText);
        }});
}
$( document ).ready(function() {
	var tipoInicial = $("#tipoDocumento").val();
	window.onload = actualizar(tipoInicial);
});

</script>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'clave', 'error')} required uk-form-row">
	<label for="tipo" class="uk-form-label">
		<g:message code="documento.tipo.label" default="Tipo" />
		<span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select id="tipoDocumento" name="tipoDocumento.id" from="${TipoEje.list() }"
			value="${ documentoInstance?.tipoDocumento?.id}"
			onchange="actualizar(this.value);"
			optionKey="id" optionValue="tipo"/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: documentoInstance, field: 'titulo', 'error')} required uk-form-row">
	<label for="titulo" class="uk-form-label">
		<g:message code="documento.titulo.label" default="Título" />
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
		<g:hiddenField name="tieneDocumento" value="${documentoInstance?.url!=null }"/>
		<input id="url" name="url" type="file" value="${documentoInstance?.url}" ${documentoInstance?.url==null?'required=""':'' } >
	</div>
</div>

<div id="divNiveles" class="fieldcontain  required uk-form-row"></div>

