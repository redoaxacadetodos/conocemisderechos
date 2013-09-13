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
					
			<span class="nombre_indicador">${indicador.nombre }</span>
			
			<br /><g:link action="detalleIndicador" id="${indicador.id}" class="ver_indicador">Ver indicadores</g:link><br /><br />
			 <br>
		</g:each>	
	</g:each>	
</div>