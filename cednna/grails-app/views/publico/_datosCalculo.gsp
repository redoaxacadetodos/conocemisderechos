<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>	
	  	<g:if test="${datosCalculo}">	  		
		  		<table>
			  		<thead>
			  			<tr>	
			  				<th>Variable</th>
			  				<th>
			  				<g:if test="${tipo.equals('1')}">Estado</g:if>
			  				<g:elseif test="${tipo.equals('2') }">Regiones</g:elseif>
			  				<g:else>Municipios</g:else>			  				
			  				</th>			  				
			  				<g:each var="valor" status="i" in="${datosCalculo}">
			  					<g:if test="${(i % tamVariables) == 0 }">
					  				<th>${valor.valores.anio.get(0)}</th>
					  			</g:if>
					  		</g:each>			  								  				
			  			</tr>
			  		</thead>
		  		<tbody>
		  			<g:each var="datos" status="i" in="${datosCalculo}">
		  				<g:if test="${i<tamVariables }">
				  			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">			  			
				  				<td>${datos.descripcion}</td>
				  				<td>			  				
				  				<g:each var="valor" in="${datos?.valores}">
				  					<g:if test="${tipo.equals('1')}">Oaxaca</g:if>
					  				<g:elseif test="${tipo.equals('2') }">${ valor.region}</g:elseif>
					  				<g:else>${ valor.municipio}</g:else>
				  					<br>
				  				</g:each>
				  				</td>		
				  				<g:each var="datosAux" status="cont" in="${datosCalculo }">
				  					<g:if test="${((cont+i) % tamVariables) == 0 ? true: false}">				  								  				
					  					<td>			  				
								  			<g:each var="valores" in="${datosAux?.valores}">
								  				${valores.indicador}<br>
								 			</g:each>
								  		</td>			
							  		</g:if>			  			
				  				</g:each>				  								  					  			
				  			</tr>
			  			</g:if>
		  			</g:each>
		  			
		  		</tbody>
		  		</table>		  	
	  	</g:if>
	  	
	  	<g:else>
	  		No se puede mostrar información de las variables para el indicador
	  	</g:else>	