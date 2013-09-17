<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>				
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
				  		<g:set var="resultadoaux" value="${resultadosIndicador.get(0) }"></g:set>
				  		
				  		<g:each var="resultado" in="${resultadoaux.resultados }">
				  		<th>${resultado?.anio }</th>
				  		</g:each>
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
						  			<td>${result?.indicador }</td>
						  		</g:each>					  			
					  		</g:each>
					  	</tr>
					  	</g:each>
				  	</tbody>
			  	</table>
		<!-- Termina tabla indicador general -->
	
</div>