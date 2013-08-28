




<h2>Resultado General </h2>

		  			
<h2>Resultado por Region </h2>
		 <div> <label>Total por region : </label><label>  ${vregion.total}</label></div>
		 <div> <label>Mujeres por region : </label><label>  ${vregion.mujeres}</label></div>
		 <div> <label>Hombres por region : </label><label>  ${vregion.hombres}</label></div>
			
		<br>
		<br>
		 <table>	
		 	<tr>
		 		<th>Municipio</th><th>Categoria</th><th>Poblacion total</th><th>Mujeres</th><th>Hombres</th>
		 	</tr>		
			 <g:each in="${vregion.variables}" var="vari">
			 
				    <g:each in="${vari.categorias}" var="cat">
				 	 <tr>
				 	 		<td>
									${vari.municipio.descripcion}
						 	</td>
						 	<td>
									${cat.descripcion}
						 	</td>
						 	<td>
									${vari.poblacionTotal}
						 	</td>
						 	<td>
									${vari.mujeres}
						 	</td>
						 	<td>
									${vari.hombres}
						 	</td>
				 	</tr>	
				 	</g:each>
			 			
			 </g:each>
		 </table>			
<h2>Resultado por Municipio </h2>		
		  			
		 <div> <label>Total por municipio : </label><label>  ${vmunicipio.total}</label></div>
		 <div> <label>Mujeres por municipio : </label><label>  ${vmunicipio.mujeres}</label></div>
		 <div> <label>Hombres por municipio : </label><label>  ${vmunicipio.hombres}</label></div>

		 <table>	
		 
		 	<tr>
		 		<th>Localidad</th><th>Categoria</th><th>Poblacion total</th><th>Mujeres</th><th>Hombres</th>
		 	</tr>		
		 		
			 <g:each in="${vmunicipio.variables}" var="vari">
			 
				    <g:each in="${vari.categorias}" var="cat">
				 	 <tr>
						 	<td>
									${cat.descripcion}
						 	</td>
				 	</tr>	
				 	</g:each>
			 			
			 </g:each>
		 </table>			
		  			

<h2>Resultado por Localidad </h2>

		 <div> <label>Total por localidad : </label><label>    ${vlocalidad.total}</label></div>
		 <div> <label>Mujeres por localidad : </label><label>  ${vlocalidad.mujeres}</label></div>
		 <div> <label>Hombres por localidad : </label><label>  ${vlocalidad.hombres}</label></div>

		 <table>		
		 
		 		<tr>
		 		<th>Categoria</th><th>Poblacion total</th><th>Mujeres</th><th>Hombres</th>
		 		</tr>		
		 		
			 <g:each in="${vlocalidad.variables}" var="vari">
			 
				    <g:each in="${vari.categorias}" var="cat">
				 	 <tr>
						 	<td>
								   ${cat.descripcion}
						 	</td>
				 	</tr>	
				 	</g:each>
			 			
			 </g:each>
		 </table>			