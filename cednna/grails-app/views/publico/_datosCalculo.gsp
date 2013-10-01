<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
	  	<g:if test="${datosCalculo}">
	  		<g:if test="${tipo.equals('1')}">
		  	<table>
		  		<thead>
		  		<tr>
		  		<th>Variable</th>
		  		<th>Valores</th>
		  		<th>Año</th>		  		
		  		<!-- 
		  		<g:each var="valor" in="${datosCalculo?.valores ? datosCalculo?.valores?.get(0) : null}">
		  			<th>${valor.anio}</th>
		  		</g:each>
		  		 -->
		  		</tr>
		  		</thead>
		  		<tbody>
		  			<g:each var="datos" status="i" in="${datosCalculo }">
			  			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			  				<td>${datos.letra}</td>
			  				<g:each var="valor" in="${datos?.valores}">
			  					<td>${valor.indicador}</td>
			  					<td>${valor.anio}</td>
			  				</g:each>
			  			</tr>
		  			</g:each>
		  		</tbody>
		  	</table>
		  	</g:if>
		  	<g:else>
		  		<table>
			  		<thead>
			  			<tr>	
			  				<th>Variable</th>
			  				<th>${tipo.equals('2') ? 'Regiones' : 'Municipios'}</th>
			  				<th>Valores</th>
			  				<th>Año</th>
			  				<!-- 
			  				<g:each var="valor" status="i" in="${datosCalculo?.valores ? datosCalculo?.valores?.get(0) : null}">
			  					<g:if test="${(i % 8) == 0 }">
					  			<th>${valor.anio}</th>
					  			</g:if>
					  		</g:each>
					  		 -->
			  			</tr>
			  		</thead>
		  		<tbody>
		  			<g:each var="datos" status="i" in="${datosCalculo }">
			  			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			  				<td>${datos.letra}</td>
			  				<td>			  				
			  				<g:each var="valor" in="${datos?.valores}">
			  					${tipo.equals('2') ? valor.region : valor.municipio}<br>
			  				</g:each>
			  				</td>		
			  				
			  				<td>			  				
				  			<g:each var="valores" in="${datos?.valores}">
				  				${valores.indicador}<br>
				  			</g:each>
				  			</td>
			  				<td>			  				
				  			<g:each var="valores" in="${datos?.valores}">
				  				${valores.anio}<br>
				  			</g:each>
				  			</td>		  				
			  			</tr>
		  			</g:each>
		  			
		  		</tbody>
		  		</table>
		  	</g:else>
	  	</g:if>
	  	<g:else>
	  		No se puede mostrar información de las variables para el indicador
	  	</g:else>	