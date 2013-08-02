<%@ page contentType="text/html;charset=UTF-8" %>
<html>
				<head>
				<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
			 
		
				<g:datatablehelperjs ctrlid="indicadorTable" context="${request.getContextPath()}" 
				controller="indicador" action="dataTablesListadoIndicadores" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}']"   whereadicional="i%2Edependencia%5Fid%3C%3D${dependencia}"
				/>

				</head>
				<body>
				  <div class="body">
				  
				  <g:datatablehelper ctrlid="indicadorTable"  cols="['ID','Nombre','Objetivo','Nombre Responsable','Medios de verificacion']" class="table table-striped table-bordered"></g:datatablehelper>
				  
				  </div>
				</body>


				<script type="text/javascript" defer="defer">

					$(function(){
				
				
						function muestraBoton(source, type, val) {
							return "<img border='0'  src='/ACE/img/view.png'  style='cursor:pointer;'  onclick='mostrarRegistro(" + source[0] + "); '\/>"
						}
				
				
				
					});
				
				
				</script>
</html>