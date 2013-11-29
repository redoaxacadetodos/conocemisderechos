<g:set var="counter" value="${con.toInteger()}" />
<g:hiddenField name="tam_${idn}" value="${categorias.size}"/>
<g:each status="i" var="categoria" in="${categorias}">
<br>
<div id="div_${counter}" class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">

		<div class="uk-grid">

				<div class="uk-width-1-2">
					<label for="localidad" class="uk-form-label">
						<g:message code="indicador.localidad.label" default="Tipo de categoria" />
				
					</label>
					<input id="cat_${counter}" name="cat_${counter}"  value="${tipo.descripcion}"  readonly="readonly"/>
				</div>
				
				<div class="uk-width-1-2">
					<label for="localidad" class="uk-form-label">
						<g:message code="indicador.localidad.label" default="Categoria" />
				
					</label>
					
			
					<div id="divTipo_${counter}" class="fieldcontain">
							<input id="cat_${counter}" name="cat_${counter}"  value="${categoria.descripcion}" readonly="readonly" />
							<g:hiddenField name="categoria_${counter}" value="${categoria.id}"/>
					</div>
			
				
			     </div>
		
	     </div>
	    
	    <input type="button" value="-"  id="del_${counter}" class="uk-button" />

</div>
<script type="text/javascript">




$(function(){



				$("#del_${counter}").click(function(){

					
				$("#div_${counter}").remove();

				var num = parseInt($("#numCategorias").val()); 

				num=num-1;

				$("#numCategorias").val(num);

			});	

				
			
});
<g:set var="counter" value="${counter+1}" />



						                		
</script>					                		
</g:each>

<script type="text/javascript">


$(function(){

	var num = parseInt($("#numCategorias").val()-1); 

	var tam= parseInt($("#tam_${idn}").val()); 

	var suma=parseInt(num+tam);
	
	$("#numCategorias").val(suma);
	
});

						                		
</script>	