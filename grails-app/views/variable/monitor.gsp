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
				aoColumns="['{bVisible: false}','{bVisible: false}','{bVisible: false}','{mData:3}','{mData:4}','{mData:5}','{mData:6}','{mData:7}','{mData:8}','{mData:9}','{mData:10}','{mData:muestraBoton}']"   
				/>
 <script type="text/javascript" >


		function muestraBoton(source, type, val) 	
		{
		return "<a href='#'  class='uk-icon-button uk-icon-search'  onclick='mostrarRegistro(\"" + source[3] + "\",\"" + source[4] + "\",\"" + source[0] + "\",\"" + source[1] + "\",\"" + source[2] +  "\",\"" + source[8] + "\",\"" + source[9] + "\",\"" + source[10] + "\"); '\/>"
		}

		
		function mostrarRegistro(id,desc,reg,mun,loc,hombres,mujeres,total){
			
			document.location.href=CONTEXT_ROOT+"/variable/panel?id="+id+"&desc="+desc+"&region="+reg+"&municipio="+mun+"&localidad="+loc+"&mujeres="+mujeres+"&hombres="+hombres+"&total="+total;

		}
		 </script> 
 		
</head>

</head>
<body>

  
  <nav class="uk-navbar">
		<ul class="uk-navbar-nav">

				<li class="uk-active"><g:link class="monitor" action="monitor">Monitor de datos </g:link></li>	
				<li><g:link class="create" action="create">Agregar origen de datos</g:link></li>
				<li><g:link class="create" action="archivo">Subir desde archivo</g:link></li>
				<li ><g:link class="list" action="list">Origen de datos </g:link></li>
				
		</ul>
</nav>

					<br>
					<br>
				  <div class="body">
				  <g:datatablehelper ctrlid="variableTable"  cols="['ID_REGION','ID_MUNICIPIO','ID_LOCALIDAD','Clave','Descripci&oacute;n','Regi&oacute;n','Municipio','Localidad','Hombres','Mujeres','Poblaci&oacute;n Total','Detalle']" class="table table-striped table-bordered"></g:datatablehelper>
				  </div>
</body>
  
 
 


</body>
</html>