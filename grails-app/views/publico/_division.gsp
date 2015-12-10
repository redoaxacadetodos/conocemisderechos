<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Nivel" %>
<div>
<div class="uk-grid">

	<div class="uk-width-7-10">
	<g:each var="divi" in="${divisiones}">
		<div id="division${divi?.id}">
		<h2>${divi?.descripcion }</h2>
		</div>	
		<g:if test="${tipo==1 }">
			<g:each var="indicador" in="${
				Indicador.createCriteria().list {
					division{
						eq("id", divi.id)					
					}
					and{
						eq("publico", true)
					}
					order("orden", "asc")
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
		</g:if>	
	</g:each>	
	</div>
	<g:if test="${tipo!=null && tipo!=1 }">
		<div class="uk-width-1-1">
			<g:select name="nivelTabla" from="${niveles }" 
				optionKey="id" optionValue="nivel" 
				onchange="${remoteFunction(action: 'actualizarTablaDocumento',
                       update: 'tablaDocumento',
					   id:tipo,
                       params: '\'nivel=\' + this.value')}"/>
            <div id="tablaDocumento">
            	<g:render template="tablaDocumento" model="[nivel:niveles?.id?.get(0)]"></g:render>
            </div>
            <g:form name="formDocumento" action="descargarDocumento">
            	<g:hiddenField name="nivel"/>
				<g:hiddenField name="tipo"/>
				<g:hiddenField name="documento"/>
			</g:form>
		</div>
	</g:if>
	<g:if test="${tipo==1 }">
		<div class="uk-width-3-10">
		<br /><br />
		<div class="uk-panel uk-panel-box" id="menuflotante">
			<h3>Temas</h3>
				<g:each var="divi" in="${divisiones}" status="i">
					<a href="#division${divi?.id}">${divi?.descripcion }</a>
				</g:each>
		</div>
		</div>
	</g:if>
	
</div>

	
	
</div>