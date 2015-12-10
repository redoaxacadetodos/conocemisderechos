<%@ page import="mx.gob.redoaxaca.cednna.domino.Indicador"%>
<script>
  $(function() {
    $( "#sortable" ).sortable({
      revert: true
    });
    $( "ul, li" ).disableSelection();
  });
</script>
<br/>
<div id="division${division?.id}">
	<h2>${division?.descripcion }</h2>
</div>	
<ul id="sortable">
	<g:each var="indicador" in="${
		Indicador.createCriteria().list {
			division{
				eq("id", division.id)					
			}
			and{
				eq("publico", true)
			}
			order("orden", "asc")
		}}">		
		<li class="ui-state-default" style="cursor: pointer;">
			${indicador?.nombre }
			<g:hiddenField name="indicador" value="${indicador?.id }"/>
			<i class="fa fa-sort" style="float: right;"></i>
		</li>
	</g:each>	
</ul>

<g:hiddenField name="division" value="${division?.id }"/>

<sec:ifAnyGranted roles="ROLE_ADMIN,ROLE_DEP">
			<button  onclick="form.submit();" class="uk-button uk-button-primary">
				${message(code: 'default.button.save.label', default: 'Guardar')}
			</button>
		</sec:ifAnyGranted>