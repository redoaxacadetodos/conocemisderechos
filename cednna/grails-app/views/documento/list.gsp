
<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'documento.label', default: 'Documento')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<g:javascript src="jquery.dataTables.js" />
		<g:datatablehelperjs ctrlid="table"
			context="${request.getContextPath()}" controller="documento"
			action="dataTablesList" jqueryui="true"
			lang="${resource(dir:'js', file:'langtabla.json')}"
			aoColumns="['{bVisible: false}', '{mData:mostrar }', '{mData:cambiarNivel}', '{mData:3}', '{mData:descargar, bSortable: false }']"/>
		<script type="text/javascript">
			function mostrar(source, type, val){
				return "<a href='"+ CONTEXT_ROOT+"/documento/show/"+ source[0] + "'>"+ source[1] +"</a> ";
			}

			function cambiarNivel(source, type, val){
				var nivel = "";
				if(source[2]==1){
					nivel="Internacional";
				}else if(source[2]==2){
					nivel="Federal";
				}else if(source[2]==3){
					nivel="Estatal";
				}
				return nivel 
			}

			function descargar(source, type, val){
				return "<div class='centrado'><a href='#' onclick=descargarDocumento("+source[3]+","+source[2]+",'"+source[4]+"')><i class='uk-icon-download uk-icon-small'></i></a></div>";
			}

			function descargarDocumento(tipo, nivel, documento){
				$("#tipo").val(tipo);
				$("#documento").val(documento);
				$("#nivel").val(nivel);
				$("#formDocumento").submit();
			}
		</script>
	</head>
	<body>
		<a href="#list-documento" class="skip" tabindex="-1"><g:message code="default.link.skip.label" default="Skip to content&hellip;"/></a>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="create" action="create"><g:message code="default.new.label" args="[entityName]" /></g:link></li>
			</ul>
		</div>
		<div id="list-documento" class="content scaffold-list" role="main">
			<h1><g:message code="default.list.label" args="[entityName]" /></h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			<g:datatablehelper ctrlid="table"
				cols="['Id', 'Nombre del documento', 'Nivel', 'Tipo', 'Archivo']" class="table table-striped table-bordered"></g:datatablehelper>
			<g:form name="formDocumento" action="descargarDocumento">
				<g:hiddenField name="tipo"/>
				<g:hiddenField name="documento"/>
				<g:hiddenField name="nivel"/>
			</g:form>
		</div>
	</body>
</html>
