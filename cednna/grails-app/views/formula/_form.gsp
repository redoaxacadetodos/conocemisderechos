<%@ page import="mx.gob.redoaxaca.cednna.domino.Formula" %>



<div class="fieldcontain uk-form-row ${hasErrors(bean: formulaInstance, field: 'nombre', 'error')} required">
	<label class="uk-form-label" for="nombre">
		<g:message code="formula.nombre.label" default="Nombre" />
		<span class="required-indicator">*</span>
	</label>
	<g:textField name="nombre" required="" value="${formulaInstance?.nombre}"/>
	
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: formulaInstance, field: 'descripcion', 'error')} required">
	<label class="uk-form-label" for="descripcion">
		<g:message code="formula.descripcion.label" default="Descripción" />
		<span class="required-indicator">*</span>
	</label>

	<g:textArea name="descripcion"  required="">${formulaInstance?.descripcion}</g:textArea>
</div>

<div class="fieldcontain uk-form-row ${hasErrors(bean: formulaInstance, field: 'sentencia', 'error')} required">
	<label class="uk-form-label" for="sentencia">
		<g:message code="formula.sentencia.label" default="Sentencia" />
	
	</label>

<br>
<g:hiddenField name="numVariables" value="${formulaInstance?.numVariables}"/>
<g:hiddenField name="variables" value="${formulaInstance?.variables}"   />


<br>
<br>



<!-- This is the modal -->
<div id="my-id" class="uk-modal">
	<div class="uk-modal-dialog">
	
		<a class="uk-modal-close uk-close">
		</a>
		
		<h2 id="myModalLabel">Crea tu variable </h2>
			 
			 
			    <div class="fieldcontain uk-form-row ${hasErrors(bean: formulaInstance, field: 'descripcion', 'error')} required">
						<label class="uk-form-label" for="descripcion">
							<g:message code="formula.descripcion.label" default="Nombre variable" />
						
						</label>
						<g:textField name="variableTxt" />
						</div>
						
			    		<input type="button" class="btn btn-primary uk-button" id="btnGuardar" value="Guardar variable" />
	
			    
				
		
		
	</div>
</div>




<div class="contenido">
    <div class="calculator">
        <div class="screen"></div>
        <input type="hidden" id="sentencia" name="sentencia" value="${formulaInstance?.sentencia}"  class="outcome" />
        <ul class="botonesCal">
            <li ><a id="btnVariable">Variable</a></li>
          
            <li><a href="/" class="val">&divide;</a></li>
            <li><a href="*" class="val">&times;</a></li>    
            <li><a href="+" class="val">+</a></li>
            <li><a href="7" class="val">7</a></li>
            <li><a href="8" class="val">8</a></li>
            <li><a href="9" class="val">9</a></li>
            <li><a href="-" class="val">-</a></li>
            <li><a href="4" class="val">4</a></li>
            <li><a href="5" class="val">5</a></li>
            <li><a href="6" class="val">6</a></li>
            <li><a href="[" class="val">[</a></li>
            <li><a href="1" class="val">1</a></li>
            <li><a href="2" class="val">2</a></li>
            <li><a href="3" class="val">3</a></li>
            <li><a href="]" class="val">]</a></li>
            <li><a href="0" class="val">0</a></li>
            <li><a href="." class="val">.</a></li>
            <li><a href="(" class="val">(</a></li>
            <li><a href=")" class="val">)</a></li>
             <li  class="clear"><a>Limpiar</a></li>
        </ul>
    </div>
</div>


</div>

<br>
  
 
<!-- Modal -->


<script type="text/javascript">
$(function(){


	

	
	$(document).ready(function() {
		$(".screen").html($(".outcome").val() );
		$('#myModal').hide();
		

						
	});
	$('#btnVariable').click(function (e) {

		var modal = new $.UIkit.modal.Modal("#my-id");

		if ( modal.isActive() ) {
			modal.hide();
		} else {
			modal.show();
		}

		
		
		$('#variableTxt').val("");
	});



	$('#btnGuardar').click(function (e) {
	
		var modal = new $.UIkit.modal.Modal("#my-id");

		if ( modal.isActive() ) {
			modal.hide();
		} else {
			modal.show();
		}

			 $(".screen").append($('#variableTxt').val());
	
			 $(".outcome").val($(".outcome").val() + $('#variableTxt').val());
		
			 $("#numVariables").val( parseInt($("#numVariables").val())+1);
			 $("#variables").val( $("#variables").val()+$('#variableTxt').val()+"|");
		});
	
    $(".val").click(function(e){
         e.preventDefault();
          var a = $(this).attr("href");
          $(".screen").append(a);
          $(".outcome").val($(".outcome").val() + a);
    });

     $(".equal").click(function(){
        //  $(".outcome").val(eval($(".outcome").val()));
         //	 $(".screen").html(eval($(".outcome").val()));
     });

     $(".clear").click(function(){
          $(".outcome").val("");
          $(".screen").html("");
          $("#variables").val("");
          $("#numVariables").val("0");
     });

     $(".min").click(function(){
         $(".cal").stop().animate({width: "0px", height: "0px", marginLeft: "700px", marginTop: "1000px"}, 500);
        setTimeout(function(){$(".cal").css("display", "none")}, 600);
     });

     $(".close").click(function(){
          $(".cal").css("display", "none");
     })
})

</script>
