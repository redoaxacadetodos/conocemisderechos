<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<div>
<div class="uk-grid">

	<div class="uk-width-7-10">
<g:each var="divi" in="${divisiones}">
		<div id="division${divi?.id}">
		<h2>${divi?.descripcion }</h2>
		</div>	

		<g:each var="indicador" 
		in="${Indicador.createCriteria().list {
				division{
					eq("id", divi.id)					
				}
				and{
					eq("publico", true)
				}
			}}">				
			<span class="nombre_indicador">${indicador?.nombre }</span>
			
			<br />
			<g:if test="${!indicador?.urlExterna }">
				<g:link action="mostrarIndicador" params="['ejeInstance':ejeInstance?.id]" id="${indicador?.id}" class="ver_indicador">Ver indicador</g:link>
			</g:if>
			<g:else>
				<a href="${ indicador?.urlExterna}" class="ver_indicador" TARGET="_new">Ver indicador</a>
			</g:else>
			
			<hr class="dotted">
		</g:each>		
	</g:each>	
	</div>
	<div class="uk-width-3-10">
	<br /><br />
	<div class="uk-panel uk-panel-box" id="menuflotante">
		<h3>Indicadores</h3>
			<g:each var="divi" in="${divisiones}">
				<a href="#division${divi?.id}">${divi?.descripcion }</a>
			</g:each>
	</div>

	</div>
</div>

	
	
</div>