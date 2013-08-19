<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>
	<g:each var="divi" in="${divisiones}">
		<h2>${divi.descripcion }</h2>	
		<br>
		<g:each var="indicador" 
		in="${Indicador.createCriteria().list {
				division{
					eq("id", divi.id)					
				}
			}}">	
					
			${indicador.nombre }
			<g:remoteLink update="division" action="detalleIndicador" id="${indicador.id}">
			Ver indicadores
			</g:remoteLink> <br>
		</g:each>	
	</g:each>	
</div>