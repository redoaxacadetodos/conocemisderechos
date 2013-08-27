<%@ page contentType="text/html;charset=UTF-8" %>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1"/>
<meta name="layout" content="main"/>
<title>Insert title here</title>

<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js" ></script>
				<g:javascript src="jquery.dataTables.js"  />
			<g:datatablehelperjs ctrlid="variableTable" context="${request.getContextPath()}" 
				controller="variable" action="dataTablesListadoPanel" jqueryui="true" lang="${request.getContextPath()}/js/langtabla.json"    
				aoColumns="['{mData:0} ','{mData:1}','{mData:2}','{mData:3}','{mData:4}','{mData:muestraBoton}']"   
				/>
 <script type="text/javascript" >


		function muestraBoton(source, type, val) 	
		{
		return "<a href='#'  class='uk-icon-button uk-icon-search'  onclick='mostrarRegistro(" + source[0] + "); '\/>"
		}

		


		function mostrarRegistro(id){
			
			document.location.href=CONTEXT_ROOT+"/variable/panel/"+id;

		}
		 </script>
 		
</head>

</head>
<body>

  
  <nav class="uk-navbar">
		<ul class="uk-navbar-nav">

			<li class="uk-active"><g:link class="list" action="list">Origen de datos </g:link></li>

			
				<li><g:link class="create" action="create">Agregar origen de datos</g:link></li>
				<li><g:link class="create" action="archivo">Subir desde archivo</g:link></li>

</nav>

					<br>
					<br>
				  <div class="body">
				  
				  <g:datatablehelper ctrlid="variableTable"  cols="['Clave','Descripcion','Hombres','Mujeres','Poblacion Total','Opciones']" class="table table-striped table-bordered"></g:datatablehelper>
				  </div>
</body>
  
 
 


</body>
</html>