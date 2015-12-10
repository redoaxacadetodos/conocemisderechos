<!DOCTYPE html>
<html>
	<head>
		<meta name="layout" content="main">
		<g:set var="entityName" value="${message(code: 'infografia.label', default: 'infografia')}" />
		<title><g:message code="default.list.label" args="[entityName]" /></title>
		<script src="//ajax.googleapis.com/ajax/libs/jquery/1.8.3/jquery.min.js"></script>
		<script src="//code.jquery.com/ui/1.11.4/jquery-ui.js"></script>
		<link rel="stylesheet" href="${resource(dir: 'css', file: 'font-awesome.min.css')}" type="text/css">
		<style type="text/css">
		  #sortable { list-style-type: none; margin: 0; padding: 0; margin-bottom: 10px; }
		  #sortable li { margin: 5px; padding: 5px; width: 100%; color:black; background:#7dcdcd linear-gradient(to bottom, #AEE8E8, #7dcdcd) repeat-x scroll 50% 50%;}
		</style>
	</head>
	<body>
		<div class="nav" role="navigation">
			<ul>
				<li><a class="home" href="${createLink(uri: '/')}"><g:message code="default.home.label"/></a></li>
				<li><g:link class="list" action="list">Listado de infografías</g:link></li>
			</ul>
		</div>
		<div class="content scaffold-list" role="main">
			<h1>Ordenar infografías</h1>
			<g:if test="${flash.message}">
			<div class="message" role="status">${flash.message}</div>
			</g:if>
			
			<g:form action="ordenarInfografias" name="infografia" class="uk-form uk-form-horizontal">
				<fieldset class="form">
					<div id="divinfografias" class="fieldcontain uk-form-row required">
						<ul id="sortable">
							<g:each var="infografiaInstance" in="${infografias }">		
								<li class="ui-state-default" style="cursor: pointer;">
									${infografiaInstance?.titulo }
									<g:hiddenField name="infografias" value="${infografiaInstance?.id }"/>
									<i class="fa fa-sort" style="float: right;"></i>
								</li>
							</g:each>	
						</ul>
					</div>
				</fieldset>
				<button  onclick="form.submit();" class="uk-button uk-button-primary">
					${message(code: 'default.button.save.label', default: 'Guardar')}
				</button>
			</g:form>
			
		</div>
		<script>
		  $(function() {
		    $( "#sortable" ).sortable({
		      revert: true
		    });
		    $( "ul, li" ).disableSelection();
		  });
		</script>
	</body>
</html>
