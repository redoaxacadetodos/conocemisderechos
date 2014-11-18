<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="groovy.time.TimeCategory" %>
<%@ page contentType="text/html" %>
<div>
	<table>
		<thead>
			<tr><th>INDICADOR</th><th>MEDICIÓN</th><th>ÚLTIMA ACTUALIZACIÓN</th><th>ESTATUS</th><th>ENVIAR</th></tr>
		</thead>
		<tbody>
			<g:each var="indicador" in="${indicadores}">				
				<g:set var="fechaLimite" value="${use(TimeCategory) {
					    indicador?.fechaActualizacion + indicador?.frecuencia?.nmeses?.month 
					}}"></g:set>
				
				<tr><td>${indicador?.nombre}</td><td>${indicador?.frecuencia?.descripcion }</td>
				<td><g:formatDate type="date" style="LONG" date="${indicador?.fechaActualizacion }"/></td>
				<td>
				<g:if test="${fechaLimite>new Date() }">
				Estatus 1
				</g:if>
				<g:else>
				Estatus 2
				</g:else>
				</td>
				<td><g:remoteLink action="enviarCorreo" id="${indicador.id}" after="alert('Correo Enviado');">Enviar</g:remoteLink></td>
				</tr>
			</g:each>
		</tbody>
	</table>	
</div>