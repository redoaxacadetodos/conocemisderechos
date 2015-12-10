<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="groovy.time.TimeCategory" %>
<%@ page contentType="text/html" %>
<div>
	<table id="tabla" class="table nuevocolortabla">
		<thead>
			<tr>
				<th>INDICADOR</th>
<%--				<th>MEDICI&Oacute;N</th>--%>
				<th>&Uacute;LTIMA ACTUALIZACI&Oacute;N</th><th>ESTATUS</th>
				<g:if test="${rol=='1'}">
					<th>ENVIAR</th>
				</g:if>
			</tr>
		</thead>
		<tbody>
			<g:each var="indicador" in="${indicadores}" status="i">				
				<tr>
					<td><g:link action="semaforoVariable" id="${indicador.id}">${indicador?.nombre}</g:link></td>
<%--					<td>${indicador?.frecuencia?.descripcion }</td>--%>
					<td><g:formatDate type="date" style="LONG" date="${indicador?.fechaActualizacion }"/></td>
					<td>
						<g:if test="${filas[i]==true }">
							<img src="${resource(dir: 'img', file: 'star.png')}" width="20px" height="20px">
						</g:if>
						<g:else>
							<img src="${resource(dir: 'img', file: 'edit.png')}" width="20px" height="20px">
						</g:else>
					</td>
					<g:if test="${rol=='1'}">
						<td><g:remoteLink action="enviarCorreo" id="${indicador.id}" after="alert('Correo Enviado');"><img src="${resource(dir: 'img', file: 'mail.png')}" width="20px" height="20px"></g:remoteLink></td>
					</g:if>
				</tr>
			</g:each>
		</tbody>
	</table>
	<script type="text/javascript">
		$(document).ready(function(){
			$('#tabla').dataTable({
				 "oLanguage": {
			    	  "sUrl": "../datatables/language/spanish.txt"
			    	}
			});
		});
	</script>	
</div>
