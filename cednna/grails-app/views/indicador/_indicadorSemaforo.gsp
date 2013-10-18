<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="groovy.time.TimeCategory" %>
<%@ page contentType="text/html" %>
<div>
	<table>
		<thead>
			<tr><th>INDICADOR</th><th>MEDICI&Oacute;N</th><th>&Uacute;LTIMA ACTUALIZACI&Oacute;N</th>
			<th>ESTATUS</th>
			<g:if test="${rol=='1'}">
			<th>ENVIAR</th>
			</g:if>
			
			
			</tr>
		</thead>
		<tbody>
			<g:each var="indicador" in="${indicadores}">				
				<g:set var="fechaLimite" value="${use(TimeCategory) {
						if(indicador?.frecuencia?.mes){
							indicador?.fechaActualizacion + indicador?.frecuencia?.nmeses?.month
						}else{
							indicador?.fechaActualizacion + indicador?.frecuencia?.nmeses?.days
						}					     
					}}"></g:set>
				
				<tr><td>${indicador?.nombre}</td><td>${indicador?.frecuencia?.descripcion }</td>
				<td><g:formatDate type="date" style="LONG" date="${indicador?.fechaActualizacion }"/></td>
				<td>
				<g:if test="${fechaLimite>=new Date() }">
				<i class="icon-ok"></i>
				</g:if>
				<g:else>
				<i class="icon-remove"></i>
				</g:else>
				</td>
					<g:if test="${rol=='1'}">
				<td><g:remoteLink action="enviarCorreo" id="${indicador.id}" after="alert('Correo Enviado');">Enviar</g:remoteLink></td></g:if>
				</tr>
			</g:each>
		</tbody>
	</table>	
</div>