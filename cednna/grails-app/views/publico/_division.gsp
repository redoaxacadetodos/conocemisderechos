<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>
<div class="uk-grid">

	<div class="uk-width-7-10">
<g:each var="divi" in="${divisiones}">
		<div id="division${divi.id}">
		<h2>${divi.descripcion }</h2>
		</div>	

		<g:each var="indicador" 
		in="${Indicador.createCriteria().list {
				division{
					eq("id", divi.id)					
				}
			}}">				
			<span class="nombre_indicador">${indicador.nombre }</span>
			
			<br /><g:link action="detalleIndicador" id="${indicador.id}" class="ver_indicador">Ver indicador</g:link>
			<hr class="dotted">
		</g:each>		
	</g:each>	
	</div>
	<div class="uk-width-3-10">
	<br /><br /><br /><br />
	<div class="uk-panel uk-panel-box" id="menuflotante">
		<h3>Indicadores</h3>
			<g:each var="divi" in="${divisiones}">
				<a href="#division${divi.id}">${divi.descripcion }</a>
			</g:each>
	</div>

	</div>
</div>

	
	
</div>