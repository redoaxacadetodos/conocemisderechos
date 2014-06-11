
<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'formula.label', default: 'Formula')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				<g:javascript src="jquery.dataTables.js"  />
			<g:datatablehelperjs ctrlid="formulaTable" context="${request.getContextPath()}" 
				controller="formula" action="dataTablesListadoFormulas" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:muestraBoton}']"   
				/>
	
	<script type="text/javascript" >

		function muestraBoton(source, type, val) 	
		{
		return "<a href='#'  class='uk-icon-button uk-icon-edit'  onclick='editaRegistro(" + source[0] + "); '\/>"
		}

		function editaRegistro(id){
			document.location.href=CONTEXT_ROOT+"/formula/edit/"+id;
		}
	</script>
	
	</head>
	
	<body>
		<a href="#list-formula" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>

<nav class="uk-navbar">
		<ul class="uk-navbar-nav">

		
				<li class="uk-active"><g:link class="list" action="list">Lista de f&oacute;rmulas</g:link></li>
				<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_NUCLEO">
					<li><g:link class="create" action="create">Nueva f&oacute;rmula</g:link></li>
				</sec:ifAnyGranted>

		</ul>
</nav>
				

		<div id="list-formula" class="content scaffold-list" role="main">
		<h1 class="uk-article-title">Listado de f&oacute;rmulas</h1>

			 <div class="body">
				  
				  <g:datatablehelper ctrlid="formulaTable"  cols="['ID','Descripci&oacute;n','Nombre','Sentencia','Opciones']" class="table table-striped table-bordered"></g:datatablehelper>
				  </div>
			
			
		</div>
	</body>
</html>
