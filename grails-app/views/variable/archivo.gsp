<!doctype html>
<html>
<head>
<meta name="layout" content="main">
<title>Test</title>

<script type="text/javascript" defer="defer">
	function verificarDescarga(tipo) {
		if(tipo==1){
			if(confirm('La variable ya contiene datos de origen ¿Desea continuar?')){
				$("#formDescargar").submit();
			}
		}else if(tipo==2){
			$("#formDescargar").submit();
		}
	}

	$(function() {
		function bajarArchivo(id) {
			document.location.href = CONTEXT_ROOT + "/variable/generaXLS/" + id;
		}

		$(document).ready(function() {
			llenaCombo({
				url : CONTEXT_ROOT + '/json/anos.json',
				htmlOptions : {
					name : "anio",
					id : "anio",
					clase : "chosen-select",
					style : "width:150px"
				},
				index : 0,
				chained : false,
				anchor : "#anio",
				combo : true,
				valorDefault : false,
				valorDefaultText : " Seleccione la año ",
				delTag : false,
				load : false
			});

		});

		$("#addCat").click(function() {
			var cont = parseInt($("#numCategorias").val());
			cont = cont + 1;
			$("#numCategorias").val(cont);

			var unused = $.ajax({
				type : 'POST',
				url : CONTEXT_ROOT + '/variable/categorias',
				data : {
					con : cont,
					valida : $("#valida").val()
				},
				success : function(data, textStatus) {
					$('#divCate').append(data);
				},
				error : function(XMLHttpRequest, textStatus, errorThrown) {
					$('#diverror').html(XMLHttpRequest.responseText);
				},
				complete : function(data, textStatus) {

					var config = {
						'.chosen-select' : {},
						'.chosen-select-deselect' : {
							allow_single_deselect : true
						},
						'.chosen-select-no-single' : {
							disable_search_threshold : 10
						},
						'.chosen-select-no-results' : {
							no_results_text : 'Oops, nothing found!'
						},
						'.chosen-select-width' : {
							width : "95%"
						}
					}
					for ( var selector in config) {
						$(selector).chosen(config[selector]);
					}

					asignaEventorTipo(cont);
				}
			});
		});

	});

	function asignaEventorTipo(num) {

		llenaCombo({
			url : CONTEXT_ROOT + '/variable/getCategoriaByTipo/'
					+ $("#tipo_" + num).val(),
			htmlOptions : {
				name : "categoria_" + num,
				id : "categoria_" + num,
				clase : "chosen-select",

			},
			index : 0,
			chained : false,
			anchor : "#categoria_" + num,
			combo : true,
			valorDefault : false,
			valorDefaultText : " Seleccione la categoria ",
			delTag : true,
			tag : "#divTipo_" + num,
			load : true
		});

		$("#tipo_" + num).change(
				function() {

					llenaCombo({
						url : CONTEXT_ROOT + '/variable/getCategoriaByTipo/'
								+ $("#tipo_" + num).val(),
						htmlOptions : {
							name : "categoria_" + num,
							id : "categoria_" + num,
							clase : "chosen-select",

						},
						index : 0,
						chained : false,
						anchor : "#categoria_" + num,
						combo : true,
						valorDefault : false,
						valorDefaultText : " Seleccione la categoria ",
						delTag : true,
						tag : "#divTipo_" + num,
						load : true
					});

				});

	}
</script>
<script>
	$(document).ready(function() {
		$('#periodo_chosen').css({"width": "150px"});
		$('#periodo').attr('disabled','disabled');
		$('#divPeriodo').hide();
		$('#descargar').hide();
		
	});
	</script>	
	<script type="text/javascript">
	function mostrarProcesarArchivo(){
		if($("#fileBase").val() == ''){
			$("#Procesar").hide();
		}else{
			$("#Procesar").show();
		}
	}

	function mostrarDescargaArchivo(){
		if($("#origenDatos").val() == 'null'){
			$("#descargar").hide();
		}else{
			$("#descargar").show();
		}
	}

	function mostrarCargado(){
		$("#respuesta").html('');
		$("#barraCarga").show();
	}
	</script>
</head>
<body>
	<g:form action="generaXLS" controller="variable" method="post" name="formDescargar">

		<nav class="uk-navbar">
			<ul class="uk-navbar-nav">
				<li><g:link class="list" action="list">Datos estad&iacute;sticos</g:link></li>
				<li class="uk-active"><g:link class="create" action="create">Nuevo dato</g:link></li>
			</ul>
		</nav>

		<h1 class="uk-article-title">Procesar datos desde archivo xlsx</h1>

		<h3 class="uk-article-content">Datos de configuraci&oacute;n del archivo</h3>

		<g:if test="${dependencia}">
			<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
				<label class="uk-form-label" for="region"> <g:message
						code="variable.region.label" default="Variable" />
					<span class="required-indicator">*</span>
				</label>
				<div class="uk-form-controls">
					<g:select id="origenDatos" name="origenDatos"
						from="${mx.gob.redoaxaca.cednna.domino.CatOrigenDatos.findAllByDependencia(dependencia)}"
						optionKey="clave" optionValue="detalleCombo" class="chosen-select"
						style="width:800px;" value="${variableInstance?.clave}"
						onchange="mostrarDescargaArchivo();"
						noSelection="['null': '- Ninguna variable-']" />
				</div>
			</div>
		</g:if>
		<g:else>
			<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'region', 'error')} ">
				<label class="uk-form-label" for="region"> <g:message
						code="variable.region.label" default="Variable" />
					<span class="required-indicator">*</span>
				</label>
				<div class="uk-form-controls">
					<g:select id="origenDatos" name="origenDatos"
						from="${mx.gob.redoaxaca.cednna.domino.CatOrigenDatos.list()}"
						optionKey="clave" optionValue="detalleCombo" class="chosen-select"
						style="width:800px;" value="${variableInstance?.clave}"
						onchange="mostrarDescargaArchivo();"
						noSelection="['null': '- Ninguna variable-']" />
				</div>
			</div>

		</g:else>

		<g:hiddenField name="tipoPeriodo" value="false"/>
		<br>
		<div id="divAnio" class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
			<label class="uk-form-label" for="anio">
				<g:message code="variable.anio.label" default="Año" />
				<span class="required-indicator">*</span>
			</label>
			<div class="uk-form-controls">
			<select id="anio" name="anio" ></select>
			<input type="button" id="mostrarPeriodo" onClick="$('#divPeriodo').show();$('#divAnio').hide();$('#anio').attr('disabled','disabled');$('#periodo').removeAttr('disabled'); $('#tipoPeriodo').val('true');" value="Mostrar periodos" class="uk-button">
			</div>
		</div>
		
		<div id="divPeriodo" class="fieldcontain uk-form-row  ${hasErrors(bean: indicadorInstance, field: 'frecuencia', 'error')} ">
			<label class="uk-form-label"for="frecuencia">
				<g:message code="indicador.frecuencia.label" default="Ciclo escolar " />
		
			</label>
			<g:select id="periodo" name="periodo.id" from="${mx.gob.redoaxaca.cednna.domino.Periodo.list().sort{it.descripcion}}" optionKey="id" optionValue="descripcion" required="" value="${indicadorInstance?.periodo?.id}" class="many-to-one" style="width:350px;"/>
			<input type="button" id="ocultarPeriodo" onclick="$('#divPeriodo').hide();$('#divAnio').show();$('#periodo').attr('disabled','disabled');$('#anio').removeAttr('disabled');$('#tipoPeriodo').val('false');" value="Ocultar periodos" class="uk-button">
		</div>

		<div class="fieldcontain uk-form-row ${hasErrors(bean: variableInstance, field: 'anio', 'error')} required">
			<label class="uk-form-label">Zona geografica a procesar</label>
			<span class="required-indicator">*</span>
			<div class="uk-form-controls">
				<select id="opcionSerie" name="opcionSerie">
					<option value="1">Estatal</option>
					<option value="2">Regional</option>
					<option value="3">Municipal</option>
				</select>
			</div>
		</div>

		<div id="divCate"></div>

		<br>
		<g:hiddenField name="valida" value="1" />
		<g:hiddenField name="numCategorias" value="0" />
		<input id="addCat" name="addCat" value="Agregar Categor&iacute;a" type="button" class="uk-button" />
		
		<hr class="uk-article-divider" class="uk-button">
		
		<div id="descargar">
			
			
			<g:submitToRemote class="uk-button uk-icon-download"
				id="descargaArchivo"
				name="descargaArchivo"
				value="Descarga el formato para carga de datos"
				url="[action: 'tieneDatosOrigen']" update="respuesta"/>
				
			<hr class="uk-article-divider">
		</div>
	</g:form>
	
	<g:form name="formArchivo" action="subirArchivo" controller="variable" method="post" enctype="multipart/form-data">
		<fieldset class="uk-form uk-form-horizontal">
			<div class="fieldcontain">
				<input type="file" id="fileBase" name="fileBase" onchange="mostrarProcesarArchivo();" accept="application/vnd.ms-excel, application/vnd.openxmlformats-officedocument.spreadsheetml.sheet"
					class="uk-form"></input>
			</div>
			<br /> <input type="submit" id="Procesar" name="Procesar" class="uk-button"
				value="Procesar archivo" onclick="mostrarCargado();" style="display:none;">
		</fieldset>
	</g:form>
	
	<div id="respuesta"></div>
	
	<div id="barraCarga" style="display:none;">
		<div id="noTrespassingOuterBarG" class="centrado">
			<div id="noTrespassingFrontBarG" class="noTrespassingAnimationG">
				<div class="noTrespassingBarLineG"></div>
				<div class="noTrespassingBarLineG"></div>
				<div class="noTrespassingBarLineG"></div>
				<div class="noTrespassingBarLineG"></div>
				<div class="noTrespassingBarLineG"></div>
				<div class="noTrespassingBarLineG"></div>
			</div>
		</div>	
	</div>
	
	<script type="text/javascript">
		$(document).ready(function (e) {
		    $('#formArchivo').on('submit',(function(e) {
		        e.preventDefault();
		        var formData = new FormData(this);
	
		        $.ajax({
		            type:'POST',
		            url: $(this).attr('action'),
		            data:formData,
		            cache:false,
		            contentType: false,
		            processData: false,
		            success:function(data){
		            	$("#barraCarga").hide();
		            	$("#respuesta").html(data);
		            },
		            error: function(data){
			            alert("Error al procesar el archivo");
		            }
		        });
		    }));
		});
	</script>
	
</body>

</html>

