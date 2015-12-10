<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador"%>
<g:if test="${divisiones.size()>0 }">
	<g:hiddenField name="eje" value="${idEje }"/>
	<label class="uk-form-label" for="categoria"> 
		<g:message code="indicador.categoria.label" default="Categoría" /> <span class="required-indicator">*</span>
	</label>
	<div class="uk-form-controls">
		<g:select name="categoria" from="${divisiones }" optionKey="id" value="${params.division!=null?params.division:-1 }"
			optionValue="descripcion" required="" onchange="actualizarIndicadores(this.value)"/>
	</div>
	
	<div id="divIndicadores"></div>
	
	<script>
	function actualizarIndicadores(idDivision){
		$( "#divIndicadores" ).html("<div align='center'><img height='80px' width='80px' alt='cargando' src='${resource(dir:'images',file:'loading.gif') }'></div>");
		$.ajax({
			  method: "POST",
			  url: "getIndicadorByDivision",
			  data: { id : idDivision },
			  success: function(data){
				$("#divIndicadores").html(data);
			  }
		});
	}
	
	$(document).ready(function() {
		var idTemp = ${params.division!=null?params.division:-1 };
		if(idTemp==-1){
			idTemp = $("#categoria").val();
		}
		actualizarIndicadores(idTemp);	
	});
	
	</script>
</g:if>
<g:else>
	<div>No existen categorías disponibles para el módulo seleccionado</div>
</g:else>
