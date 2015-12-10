4<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page contentType="text/html;charset=UTF-8" %>
<!DOCTYPE html>
<html>
	<head>		
		<meta name="layout" content="public">
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8" />

		<link href="${resource(dir: 'css', file: 'galeria.css')}" rel="stylesheet">
        
		<script src="http://code.jquery.com/jquery-1.9.1.js"></script>
		<script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
		<!--<script src="${resource(dir: 'js', file: 'blackbox.js')}"></script>-->
        <script type="text/javascript" src="http://ajax.googleapis.com/ajax/libs/jquery/1.8.2/jquery.min.js"></script>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
	</head>

<body>
	
	<!-- Galería -->
    
    <h4>Infografías</h4>
    <div class="uk-grid">
    	<g:each var="infografiaInstance" in="${infografiaList }" status="i">
    		<div class="uk-width-1-4 second_column">
                <div class="perfundo">
                    <style>#info${infografiaInstance?.id}:target:before{background-image:url(${createLink(controller:'publico', action:'renderImage', params:['ruta':infografiaInstance?.ruta])});}</style>
                    <a class="perfundo__link" href="#info${infografiaInstance?.id }">
                        <img src="${createLink(controller:'publico', action:'renderImage', params:['ruta':infografiaInstance?.ruta])}" alt="${infografiaInstance.titulo}" title="${infografiaInstance.titulo}">
                    </a>
                    <div id="info${infografiaInstance?.id }" class="perfundo__overlay"  style="z-index: 100000;">
                        <a href="#perfundo-untarget" class="perfundo__close perfundo__control">Cerrar</a>
                        <g:if test="${i>0}">
                        	<a class="perfundo__prev perfundo__control" href="#info${infografiaList[(i-1)]?.id }">Anterior</a>	
                        </g:if>
                        <g:if test="${i<(infografiaList.size()-1) }">
                        	<a class="perfundo__next perfundo__control" href="#info${infografiaList[(i+1)]?.id }">Siguiente</a>	
                        </g:if>
                    </div>
                </div>
            </div>
    	</g:each>
    </div>
    
	<!-- Fin galería -->

</body>
</html>

	  