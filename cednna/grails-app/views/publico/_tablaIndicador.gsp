<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>
		<!-- Tabla indicador general -->
			  <table>
				  	<caption>${indicadorInstance?.nombre }</caption>
				  	<thead>
				  		<tr><th>Nombre</th>
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
</div>