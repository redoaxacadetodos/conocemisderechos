<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador" %>
<%@ page import="mx.gob.redoaxaca.cednna.domino.Documento" %>
<div>
<div class="uk-grid">

	<div class="uk-width-7-10">
<g:each var="divi" in="${divisiones}">
		<div id="division${divi?.id}">
		<h2>${divi?.descripcion }</h2>
		</div>	
		<g:if test="${tipo==1 }">
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
		</g:if>	
	</g:each>	
		<g:if test="${tipo==2 }">
			<g:select name="nivelTabla" from="${[[k:1, v:'Internacional'], [k:2, v:'Federal'], [k:3, v:'Estatal']] }" 
				optionKey="k" optionValue="v" 
				onchange="${remoteFunction(action: 'actualizarTablaDocumento',
                       update: 'tablaDocumento',
					   id:tipo,
                       params: '\'nivel=\' + this.value')}"/>
            <div id="tablaDocumento">
            	<g:render template="tablaDocumento" model="[nivel:1]"></g:render>
            </div>
            <g:form name="formDocumento" action="descargarDocumento">
            	<g:hiddenField name="nivel"/>
				<g:hiddenField name="tipo"/>
				<g:hiddenField name="documento"/>
			</g:form>
		</g:if>
		<g:elseif test="${tipo==3 }">
			<g:select name="nivelTabla" from="${[[k:4, v:'Diagnosticos'], [k:5, v:'Publicaciones']] }" 
				optionKey="k" optionValue="v" 
				onchange="${remoteFunction(action: 'actualizarTablaDocumento',
                       update: 'tablaDocumento',
					   id:tipo,
                       params: '\'nivel=\' + this.value')}"/>
             <div id="tablaDocumento">
            	<g:render template="tablaDocumento" model="[nivel:4]"></g:render>
            </div>
            <g:form name="formDocumento" action="descargarDocumento">
            	<g:hiddenField name="nivel"/>
				<g:hiddenField name="tipo"/>
				<g:hiddenField name="documento"/>
			</g:form>
		</g:elseif>
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