<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>
	<g:if test="${tipo.equals('1') }">
		<!-- Tabla indicador general -->
			  <table>
				  	<caption>${indicadorInstance?.nombre }</caption>
				  	<thead>
				  		<tr><th>Estado</th>
				  		<g:each var="resultado" in="${resultados }">
				  		<th>${resultado?.anio }</th>
				  		</g:each>
				  		</tr>
				  	</thead>
				  	<tbody>
				  		
					  	<g:each var="lista" in="${listarResultados }">
					  	<tr>
					  	<td>Oaxaca</td>
					  		<g:each var="result" in="${lista}">
					  			<td>${result?.indicador }</td>
					  		</g:each>
					  	</tr>
					  	</g:each>
				  	</tbody>
			  	</table>
		<!-- Termina tabla indicador general -->
	</g:if>	
	<g:elseif test="${tipo.equals('2') }">
		<!-- Tabla indicador general -->
			  <table>
				  	<caption>${indicadorInstance?.nombre }</caption>
				  	<thead>
				  		<tr><th>Estado</th>
				  		<g:each var="resultado" in="${resultados }">
				  		<th>${resultado?.anio }</th>
				  		</g:each>
				  		</tr>
				  	</thead>
				  	<tbody>
				  		
					  	<g:each var="lista" in="${listarResultados }">
					  	<tr>
					  	<td>Oaxaca</td>
					  		<g:each var="result" in="${lista}">
					  			<td>${result?.indicador }</td>
					  		</g:each>
					  	</tr>
					  	</g:each>
				  	</tbody>
			  	</table>
		<!-- Termina tabla indicador general -->
	</g:elseif>
</div>