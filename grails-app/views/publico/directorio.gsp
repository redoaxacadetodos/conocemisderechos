
<%@ page import="mx.gob.redoaxaca.cednna.domino.Variable" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="public">
		<title>Directorio</title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				
			<g:datatablehelperjs ctrlid="direcotrioid" context="${request.getContextPath()}" 
				controller="publico" action="datosdirectorio" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:5}','{mData:6}']"   
				/>
		<g:javascript src="jquery.dataTables.js"  />
	</head>
	<body>
		<br><br>
		<a href="#list-variable" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
				  <div class="uk-overflow-container">
					  <g:datatablehelper ctrlid="direcotrioid"  cols="['ID','Nombre','Cargo','Dependencia','Correo','Tel&eacute;fono de Oficina','P&aacute;gina WEB']" class="uk-table uk-table-hover uk-table-striped">
					  </g:datatablehelper>
				  </div>
				  <div class="uk-grid body">
				  	  <div class="uk-width-1-1">
					  	<div class="uk-panel ">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/inegi.jpg" alt="Inegi">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/contraloria.jpg" alt="Secretaría de la Contraloría y Transparencia Gubernamental">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/ciedd.jpg" alt="CIEDD">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/cednna.jpg" alt="CEDNNA">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/ddhpo.jpg" alt="DDHPO">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/dif.jpg" alt="DIF">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/ieepo.jpg" alt="IEEPO">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/digepo.jpg" alt="DIGEPO">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-3 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/pgje.jpg" alt="PGJE">
					  	</div>
					  </div>
					  <div class="uk-width-small-1-1 uk-width-medium-1-5">
					  	<div class="uk-panel uk-panel-box colorpanelbox">
					  		<img src="../img/dep/jefatura.jpg" alt="Jefatura de la Gobernatura">
					  	</div>
					  </div>
					</div>
	<script src="${resource(dir: 'js', file: 'jquery.dataTables.js')}"></script>
	<script src="${resource(dir: 'js', file: 'jsDatatables.bootstrap.js')}"></script>
</body>


</html>
