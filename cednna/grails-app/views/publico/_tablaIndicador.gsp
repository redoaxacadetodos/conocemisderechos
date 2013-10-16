<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>				
	<g:if test="${resultadosIndicador }">
		<br>
		<!-- Tabla indicador general -->
			  <table>
				  	<caption>${indicadorInstance?.nombre }</caption>
				  	<thead>
				  		<tr>
				  		<g:if test="${tipo?.equals('1')}">
				  			<th>Estado</th>
				  		</g:if>
				  		<g:if test="${tipo?.equals('2') || tipo?.equals('3') || tipo?.equals('4') }">
				  			<th>Región</th>
				  		</g:if>				  		
				  		<g:if test="${tipo?.equals('3') || tipo?.equals('4') }">
				  			<th>Municipio</th>
				  		</g:if>
				  		<g:if test="${tipo?.equals('4') }">
				  			<th>Localidad</th>
				  		</g:if>
				  		<g:if test="${resultadosIndicador }">
				  			<g:set var="resultadoaux" value="${resultadosIndicador?.get(0) }"></g:set>
				  		
				  		<g:each var="resultado" in="${resultadoaux?.resultados }">				  		
				  			<th>${resultado?.anio }</th>				  		
				  		</g:each>
				  		</g:if>
				  		</tr>
				  	</thead>
				  	<tbody>
				  		
					  	<g:each var="lista" in="${resultadosIndicador }">
					  	<tr>
					  		<g:if test="${tipo?.equals('1')}">
					  			<td>Oaxaca</td>	
					  		</g:if>					  					  						  
					  		<g:each var="resultadoIndicador" in="${lista}">					  			
					  			<g:if test="${tipo.equals('2') || tipo.equals('3') || tipo.equals('4') }">
						  			<td>${resultadoIndicador?.region }</td>
						  		</g:if>				  		
						  		<g:if test="${tipo.equals('3') || tipo.equals('4') }">
						  			<td>${resultadoIndicador?.municipio}</td>
						  		</g:if>
						  		<g:if test="${tipo.equals('4') }">
						  			<td>${resultadoIndicador?.localidad }</td>
						  		</g:if>
						  		<g:each var="result" in="${resultadoIndicador?.resultados }">						  									  		
						  			<td>${result?.indicador==null ? '-':result?.indicador}</td>						  			
						  		</g:each>					  			
					  		</g:each>
					  	</tr>
					  	</g:each>
				  	</tbody>
			  	</table>
		<!-- Termina tabla indicador general -->	
	</g:if>
	<g:elseif test="${resultadosIndicador==null }">
		<div>
		<br>
		No se puede realizar la operación debido a la división entre 0
		<br><br>
		</div>
	</g:elseif>
	<g:else>		
		<div>
		<br>
		No existen valores para el indicador.
		<br><br>
		</div>
	</g:else>
</div>