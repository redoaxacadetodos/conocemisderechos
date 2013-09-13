<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div class="uk-grid">
<div class="uk-width-1-1">
	<ul class="uk-tab" data-uk-tab>
	<g:each var="eje" in="${mx.gob.redoaxaca.cednna.domino.Eje.list()}">
		<li><a href="#" onclick="${remoteFunction(
			controller:'publico',
			action: 'infoIndicador',
			update: 'division',
			id: eje.id)}" value="${eje.descripcion}" id="${eje.id}">${eje.descripcion}</a>
		</li>
					
	</g:each>
	</ul>
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
					
			${indicador.nombre }
			
			<g:link action="detalleIndicador" id="${indicador.id}">Ver indicadores</g:link>
			 <br>
		</g:each>	
	</g:each>	
</div>