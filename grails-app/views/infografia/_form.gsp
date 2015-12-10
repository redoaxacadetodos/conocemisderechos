<%@ page import="mx.gob.redoaxaca.cednna.domino.Infografia" %>

<div class="fieldcontain ${hasErrors(bean: infografiaInstance, field: 'descripcion', 'error')} uk-form-row">
	<label for="titulo" class="uk-form-label">
		<g:message code="infografia.titulo.label" default="Título" />
	</label>
	<div class="uk-form-controls">
		<g:textField name="titulo" value="${infografiaInstance?.titulo}" required=""/>
	</div>
</div>

<div class="fieldcontain ${hasErrors(bean: infografiaInstance, field: 'ruta', 'error')} uk-form-row">
	<label for="ruta" class="uk-form-label">
		<g:message code="infografia.clave.label" default="Ruta" />
	</label>
	<div class="uk-form-controls">
		<input type="file" id="ruta" name="ruta" ${infografiaInstance?.ruta==null?'required=""':'' } accept="image/*">
		<g:hiddenField name="tieneDocumento" value="${infografiaInstance?.ruta!=null }"/>
		<g:if test="${infografiaInstance?.ruta!=null }">
			<div class="uk-width-small-1-3 uk-width-medium-1-5">
				<div class="uk-panel uk-panel-box colorpanelbox">
			  		<img id="imagen" src="${createLink(controller:'infografia', action:'renderImage', params:['ruta':infografiaInstance?.ruta])}" alt="${infografiaInstance.titulo}" title="${infografiaInstance.titulo}">
			  	</div>
		  	</div>
		</g:if>
		<g:else>
			<div class="uk-width-small-1-3 uk-width-medium-1-5">
				<div class="uk-panel uk-panel-box colorpanelbox">
			  		<img id="imagen" alt="${infografiaInstance.titulo}" title="${infografiaInstance.titulo}">
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