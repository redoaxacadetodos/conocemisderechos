<br>
<div   id="divCat_${con}_${var}" class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
<div class="uk-grid">
			<div class="uk-width-1-2">
				<label for="localidad" class="uk-form-label">
					<g:message code="indicador.localidad.label" default="Tipo de categoria" />
			
				</label>
				<g:select id="tipo_${con}_${var}" name="tipo_${con}_${var}" from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id" optionValue="descripcion" class="chosen-select" />
			</div>
			<div class="uk-width-1-2">
				<label for="localidad" class="uk-form-label">
					<g:message code="indicador.localidad.label" default="Categoria" />
			
				</label>
				<div id="divTipo_${con}_${var}">
						<g:select id="categoria_${con}_${var}" name="categoria_${con}_${var}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" optionKey="id" optionValue="descripcion" class="chosen-select"  />
				
				</div>
			</div>
	</div>	
	<input id="del_${con}_${var}" name="del_${con}_${var}"  value="-" type="button"  class="uk-button"/>
</div>



					

<script type="text/javascript">

$(function(){
	
	$("#del_${con}_${var}").click(function(){

		$("#divCat_${con}_${var}").remove();


		var num = parseInt($("#numCategorias_${var}").val()); 

		num=num-1;

		$("#numCategorias_${var}").val(num);
	});	

});
</script>

						                		
