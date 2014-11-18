<div id="cat_${num}">
<br>
<label class="uk-form-label" for="localidad">
<g:message code="indicador.localidad.label" default="Categoria" />
</label>
<g:select id="categoria_${num}" name="categoria_${num}" from="${mx.gob.redoaxaca.cednna.domino.Categoria.list()}" 
optionKey="id" 
class="chosen-select" 
value="" 
style="width:200px;" 
optionValue="descripcion"/>
							
<input id="btnMen_${num}" name="btnMen_${num}"  value="-" type="button"  class="uk-button"/>
  		
</div>
<script type="text/javascript" defer="defer">

$(function(){


	


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



	 
	
});	
</script>