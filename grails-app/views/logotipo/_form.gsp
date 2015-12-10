<%@ page import="mx.gob.redoaxaca.cednna.domino.Logotipo" %>

<div class="fieldcontain ${hasErrors(bean: logotipoInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="titulo" class="uk-form-label">
		<g:message code="logotipo.titulo.label" default="Título" />
	</label>
	<div class="uk-form-controls">
		<g:textField name="titulo" value="${logotipoInstance?.titulo}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: logotipoInstance, field: 'ruta', 'error')} uk-form-row">
	<label for="ruta" class="uk-form-label">
		<g:message code="logotipo.clave.label" default="Ruta" />
	</label>
	<div class="uk-form-controls">
		<input type="file" id="ruta" name="ruta" ${logotipoInstance?.ruta==null?'required=""':'' } accept="image/*">
		<g:hiddenField name="tieneDocumento" value="${logotipoInstance?.ruta!=null }"/>
		<g:if test="${logotipoInstance?.ruta!=null }">
			<div class="uk-width-small-1-3 uk-width-medium-1-5">
				<div class="uk-panel uk-panel-box colorpanelbox">
			  		<img id="imagen" src="${createLink(controller:'logotipo', action:'renderImage', params:['ruta':logotipoInstance?.ruta])}" alt="${logotipoInstance.titulo}" title="${logotipoInstance.titulo}">
			  	</div>
		  	</div>
		</g:if>
		<g:else>
			<div class="uk-width-small-1-3 uk-width-medium-1-5">
				<div class="uk-panel uk-panel-box colorpanelbox">
			  		<img id="imagen" alt="${logotipoInstance.titulo}" title="${logotipoInstance.titulo}">
			  	</div>
		  	</div>
		</g:else>
	</div>
</div>
<script>
document.getElementById('ruta').onchange = function (evt) {
    var tgt = evt.target || window.event.srcElement,
        files = tgt.files;
    
    if (FileReader && files && files.length) {
        var fr = new FileReader();
        fr.onload = function () {
            document.getElementById("imagen").src = fr.result;
        }
        fr.readAsDataURL(files[0]);
    }
}
</script>