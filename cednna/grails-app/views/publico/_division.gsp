<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div class="uk-grid">
<div class="uk-width-1-1">
<div id="menubloque">
	<g:each var="eje" in="${mx.gob.redoaxaca.cednna.domino.Eje.list()}">
		<a href="#" onclick="${remoteFunction(
			controller:'publico',
			action: 'infoIndicador',
			update: 'division',
			id: eje.id)}" value="${eje.descripcion}" id="${eje.id}" class="men${eje.id} botmenubloque"></a>			
	</g:each>
	</div>	
	
</div>
</div>
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