
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador"%>
<!DOCTYPE html>
<html>
<head>
<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
<title><g:message code="default.list.label" args="[entityName]" /></title>
<meta name="layout" content="main">
<script src="//code.jquery.com/jquery-1.10.2.js"></script>
<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
<link rel="stylesheet" href="${resource(dir: 'css', file: 'font-awesome.min.css')}" type="text/css">
<style type="text/css">
  #sortable { list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px; }
  #sortable li { margin: 5px; padding: 5px; width: 100%; color:black; background:#7dcdcd linear-gradient(to bottom, #AEE8E8, #7dcdcd) repeat-x scroll 50% 50%;}
</style>
</head>
<body>
	<a href="#list-indicador" class="skip" tabindex="-1"><g:message
			code="default.link.skip.label" default="Skip to content&hellip;" /></a>

	<nav class="uk-navbar">
		<ul class="uk-navbar-nav">
			<li><g:link class="list" action="list">Listado</g:link></li>
			<sec:ifAnyGranted roles="ROLE_ADMIN, ROLE_DEP">
				<li><g:link class="create" action="create">
						<g:message code="default.new.label" args="[entityName]" />
					</g:link></li>
				<li class="uk-active">
					<g:link action="ordenar">
						<g:message code="default.ordenar.label" default="Ordenar indicadores" />
					</g:link>
				</li>
			</sec:ifAnyGranted>
		</ul>
	</nav>
	<g:if test="${flash.message}">
		<div class="message" role="status">${flash.message}</div>
	</g:if>
	<br>
	
	<g:form action="ordenarIndicadores" name="indicador" class="uk-form uk-form-horizontal">
		<fieldset class="form">
		
			<div class="fieldcontain uk-form-row required">
				<label class="uk-form-label" for="eje"> 
					<g:message code="indicador.eje.label" default="M&oacute;dulo" /> <span class="required-indicator">*</span>
				</label>
				<div class="uk-form-controls">
					<g:select id="eje" name="eje.id"
						from="${mx.gob.redoaxaca.cednna.domino.Eje.list()}" optionKey="id" value="${params.eje!=null?params.eje:-1 }"
						optionValue="descripcion" required="" onchange="actualizarDivisiones(this.value)"/>
				</div>
			</div>
			
			<div id="divDivisiones" class="fieldcontain uk-form-row required"></div>
			
		</fieldset>
	</g:form>

	<script>
		function actualizarDivisiones(idEje){
			$( "#divDivisiones" ).html("<div align='center'><img height='80px' width='80px' alt='cargando' src='${resource(dir:'images',file:'loading.gif') }'></div>");
			$.ajax({
				  method: "POST",
				  url: "getDivisionByEje",
				  data: { id : idEje, division : ${params.division!=null?params.division:-1 } },
				  success: function(data){
					$("#divDivisiones").html(data);
				  }
			});
		}

		$(document).ready(function() {
			var idTemp = ${params.eje!=null?params.eje:-1 };
			if(idTemp==-1){
				idTemp = $("#eje").val();
			}
			actualizarDivisiones(idTemp);	
		});
	</script>
</body>
</html>