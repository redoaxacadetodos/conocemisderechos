
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<meta name="layout" content="main">
		
				<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
				
				<g:javascript src="jquery.dataTables.js" />
				<script type="text/javascript">

					$(function(){
				
				
						function muestraBoton(source, type, val) {
							return "<img border='0'  src='/ACE/img/view.png'  style='cursor:pointer;'  onclick='mostrarRegistro(" + source[0] + "); '\/>"
						}
				
				
				
					});
				
				
				</script>
		
				<g:datatablehelperjs ctrlid="indicadorTable" context="${request.getContextPath()}" 
				controller="indicador" action="dataTablesListadoIndicadores" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}']"
				/>
	
		
	</head>
	<body>
		<a href="#list-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<ul class="uk-subnav uk-subnav-pill">
		<li class="uk-active"><g:link class="list" action="list"><g:message code="default.list.label" args="[entityName]" /></g:link></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>


	
		<div class="${hasErrors(bean: indicadorInstance, field: 'dependencia', 'error')} required">
			<label for="dependencia">
				<g:message code="indicador.dependencia.label" default="Dependencia" />
		
			</label>
			<g:select id="dependencia" name="dependencia.id" from="${mx.gob.redoaxaca.cednna.domino.Dependencia.list()}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.dependencia?.id}" class="many-to-one"/>
		</div>
	
		<br>
 		<g:datatablehelper ctrlid="indicadorTable"  cols="['ID','Nombre','Objetivo','Nombre Responsable','Medios de verificacion']" class="table table-striped table-bordered"></g:datatablehelper>
	

	
	</body>
	
	

</html>


