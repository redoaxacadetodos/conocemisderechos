<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
	  	<g:if test="${datosCalculo}">
		  	<table>
		  		<thead>
		  		<tr>
		  		<th>Variable</th>
		  		<g:each var="valor" in="${datosCalculo?.valores ? datosCalculo?.valores?.get(0) : null}">
		  			<th>${valor.anio}</th>
		  		</g:each>
		  		</tr>
		  		</thead>
		  		<tbody>
		  			<g:each var="datos" status="i" in="${datosCalculo }">
			  			<tr class="${(i % 2) == 0 ? 'even' : 'odd'}">
			  				<td>${datos.letra}</td>
			  				<g:each var="valor" in="${datos?.valores}">
			  					<td>${valor.indicador}</td>
			  				</g:each>
			  			</tr>
		  			</g:each>
		  		</tbody>
		  	</table>
	  	</g:if>	