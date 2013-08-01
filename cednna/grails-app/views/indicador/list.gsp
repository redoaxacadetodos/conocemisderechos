
<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'indicador.label', default: 'Indicador')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		
		<g:datatablehelperjs ctrlid="indicadorTable" context="${request.getContextPath()}" 
	controller="vuelo" action="dataTablesListadoIndicadores" bootstrap="true" jqueryui="false" lang="${request.getContextPath()}/js/langtabla.json"    
	aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:muestraBoton}']"
	/>
		
	</head>
	<body>
		<a href="#list-indicador" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

		<ul class="uk-subnav uk-subnav-pill">
		<li class="uk-active"><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
		<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
		</ul>





 <g:datatablehelper ctrlid="indicadorTable"  cols="['ID','Nombre','Objetivo','Nombre Responsable','Medios de verificacion','Opciones']" class="table table-striped table-bordered"></g:datatablehelper>
	

	
	</body>
	
	

</html>
<g:javascript>


function muestraBoton(source, type, val) {
	return "<img border='0'  src='/ACE/img/view.png'  style='cursor:pointer;'  onclick='mostrarRegistro(" + source[0] + "); '\/>"
}


</g:javascript>