<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>
	<div id="menuflotante">
		<h3>Indicadores</h3>
		<g:each var="divi" in="${divisiones}">
			<a href="#division${divi.id}">${divi.descripcion }</a><br>
		</g:each>
	</div>
	<g:each var="divi" in="${divisiones}">
		<div id="division${divi.id}">
		<h2>${divi.descripcion }</h2>
		</div>	
		<br>
		<g:each var="indicador" 
		in="${Indicador.createCriteria().list {
				division{
					eq("id", divi.id)					
				}
			}}">	
					
			<span class="nombre_indicador">${indicador.nombre }</span>
			
			<br /><g:link action="detalleIndicador" id="${indicador.id}" class="ver_indicador">Ver indicadores</g:link><br />
			 
		</g:each>	
	</g:each>	
	
</div>