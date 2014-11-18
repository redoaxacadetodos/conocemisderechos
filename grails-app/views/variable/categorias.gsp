<br>
<div id="div_${con}"
	class="fieldcontain uk-form-row ${hasErrors(bean: indicadorInstance, field: 'localidad', 'error')} required">

	<div class="uk-grid">

		<div class="uk-width-1-2">
			<label for="localidad" class="uk-form-label"> <g:message
					code="indicador.localidad.label" default="Tipo de categoria" />

			</label>
			<g:select id="tipo_${con}" name="tipo_${con}"
				from="${mx.gob.redoaxaca.cednna.domino.Tipo.list()}" optionKey="id"
				optionValue="descripcion" class="chosen-select sel-var" />
		</div>

		<div class="uk-width-1-2">
			<label for="localidad" class="uk-form-label"> <g:message
					code="indicador.localidad.label" default="Categoria" />

			</label>
			<g:if test="${valida=='1'}">
				<div id="divBtn_${con}">
					<g:checkBox name="allCat_${con}" />
					<label>Todas las categorias</label>
				</div>
			</g:if>
			<div id="divTipo_${con}" class="fieldcontain">
				<g:select id="categoria_${con}" name="categoria_${con}"
					from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}"
					optionKey="id" optionValue="descripcion" class="chosen-select" />

			</div>

		</div>

	</div>
	<input type="button" value="-" id="del_${con}" class="uk-button" />

</div>

<script type="text/javascript">
	$(function(){
			$("#del_${con}").click(function(){
				$("#div_${con}").remove();

				var num = parseInt($("#numCategorias").val()); 

				num=num-1;

				$("#numCategorias").val(num);

			});	

			
			$("#allCat_${con}").click(function(){
					
					var cont=	parseInt($("#numCategorias").val());
				
					var unused = $.ajax({type:'POST', 
				    url:CONTEXT_ROOT+'/variable/categoriasAll',
				      data: {con:cont,tipoId:$("#tipo_${con}").val()},
				      success:function(data,textStatus){
				      		$('#divCate').append(data);
				      },
		   			   error:function(XMLHttpRequest,textStatus,errorThrown){		
			   			   $('#diverror').html(XMLHttpRequest.responseText);
			   			}
				      ,
				      complete:function(data,textStatus){
				    	  var config = {
							      '.chosen-select'           : {},
							      '.chosen-select-deselect'  : {allow_single_deselect:true},
							      '.chosen-select-no-single' : {disable_search_threshold:10},
							      '.chosen-select-no-results': {no_results_text:'Oops, nothing found!'},
							      '.chosen-select-width'     : {width:"95%"}
							    }
							    for (var selector in config) {
							      $(selector).chosen(config[selector]);
							    }
				    		$("#div_${con}").html("");
				      }
					});
				});
});






						                		
</script>
