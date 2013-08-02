<%@ page contentType="text/html;charset=UTF-8" %>
<html>
				<head>
				<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
			 
		
				<g:datatablehelperjs ctrlid="indicadorTable" context="${request.getContextPath()}" 
				controller="indicador" action="dataTablesListadoIndicadores" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{bVisible: false }','{mData:1 } ','{mData:2}','{mData:3}','{mData:4}','{mData:muestraBoton}']"   whereadicional="i%2Eidn%5Fcdp%5Fid%3C%3D${dependencia}"
				/>

				</head>
				<body>
				  <div class="body">
				  
				  <g:datatablehelper ctrlid="indicadorTable"  cols="['ID','Nombre','Objetivo','Nombre Responsable','Medios de verificacion','Opciones']" class="table table-striped table-bordered"></g:datatablehelper>
				  
				  </div>
				</body>


			
</html>