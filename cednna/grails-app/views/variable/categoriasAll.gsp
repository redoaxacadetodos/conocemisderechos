<g:set var="counter" value="${con.toInteger()}" />
<g:hiddenField name="tam_${idn}" value="${categorias.size}" />
<g:each status="i" var="categoria" in="${categorias}">
	<div id="div_${counter}" class="fieldcontain uk-form-row cat-div ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">
	<br>
		<div class="uk-grid">
			<div class="uk-width-1-2">
				<label for="localidad" class="uk-form-label"> <g:message
						code="indicador.localidad.label" default="Tipo de categoria" />
				</label> 
				<input id="cat_${counter}" name="cat_${counter}"
					value="${tipo.descripcion}" readonly="readonly" />
			</div>

			<div class="uk-width-1-2">
				<label for="localidad" class="uk-form-label"> <g:message
						code="indicador.localidad.label" default="Categoria" />
				</label>

				<div id="divTipo_${counter}" class="fieldcontain">
					<input id="cat_${counter}" name="cat_${counter}"
						value="${categoria.descripcion}" readonly="readonly" />
					<g:hiddenField name="categoria_${counter}" value="${categoria.id}" />
				</div>
			</div>
		</div>

		<button id="del_${counter}" name="del_${counter}" class="uk-button btn btn-info del-cat" type="button">
			-
		</button>

	</div>
	
	<g:set var="counter" value="${counter+1}" />
</g:each>

<script type="text/javascript">

$('body').on('click', 'button.del-cat', function () {
	var prnt = $(this).parents(".cat-div");
	prnt.remove();
});



$(function(){

	var num = parseInt($("#numCategorias").val()-1); 

	var tam= parseInt($("#tam_${idn}").val()); 

	var suma=parseInt(num+tam);
	
	$("#numCategorias").val(suma);
	
});

						                		
</script>
