

function alertCarga(){	
	$("#message-drawer").html('<div id="vic-err" class="message error"><div class="message-inside"><img src="images/loader.gif"> <span class="mes_txt">Cargando ...</span></div></div></div>');			
}



function dismissAlertCarga(){	
	$("#message-drawer").html('');			
}

function isEmpty( inputStr ) { if ( null == inputStr || "" == inputStr ) { return true; } return false; }


function alertMensaje(mensaje,opacidad){

		if(!isEmpty(mensaje)){
	
			var data = mensaje.split("#");
			if(!isEmpty(data[0])){
				
			var image="correct.png"; ;
				switch (data[0]) {
				case 1:image="correct.png";
					   break;
				case 2:image="alert.png";
					   break;
				case 3:image="error.png";
					   break;
				default:
					break;
				}
	
			}
				$("#message-drawer").html('<div id="vic-err" class="message error"><div class="message-inside"><img src="../../images/'+image+'"> <span class="mes_txt">'+data[1]+ (opacidad ?'':'<a id="anchorMsgClose" href=#>&nbsp;Cerrar&nbsp;</a>')+'</span></div></div></div>');
	
				if(opacidad)
				{
					$("#vic-err").animate({ opacity: 0 }, 20000 );
				}else {
					$("#anchorMsgClose").click(function (){
						$("#vic-err").animate({ opacity: 0 }, 200 ); 	
						
					});
				}
	
		}
}

function alertMensajeTipo(mensaje,opacidad,tipo){

	if(!isEmpty(mensaje)){

	
	
			
		var image="correct.png"; ;
			switch (tipo) {
			case 1:image="correct.png";
				   break;
			case 2:image="alert.png";
				   break;
			case 3:image="error.png";
				   break;
			default:
				break;
			}

			$("#message-drawer").html('<div id="vic-err" class="message error"><div class="message-inside"><img src="../../images/'+image+'"> <span class="mes_txt">'+mensaje+ (opacidad ?'':'<a id="anchorMsgClose" href=#>&nbsp;Cerrar&nbsp;</a>')+'</span></div></div></div>');

			if(opacidad)
			{
				$("#vic-err").animate({ opacity: 0 }, 20000 );
			}else {
				$("#anchorMsgClose").click(function (){
					$("#vic-err").animate({ opacity: 0 }, 200 ); 	
					
				});
			}

	}
}



function iniAjax(){
	
	
	$.ajaxSetup({
		error:function(x,e){
			if(x.status==0){
			// alert('You are offline!!\n Please Check Your Network.');
			}else if(x.status==404){
				alertMensaje('No Existe elemento asociado con la busqueda',true,3);
			}else if(x.status==403){
				window.location.href='logOut.jsp';
			}else if(x.status==500){
				alertMensaje('Ocurrio un error inesperado',true,3);
			}else if(e=='parsererror'){
			alertMensaje('Error.\nParsing JSON Request failed.',true,3);
			}else if(e=='timeout'){
			alertMensaje('Request Time out.',true,3);
			}else {
			alertMensaje('Unknow Error.\n'+x.responseText,true,3);
			}
		
			
		}
	});

	
}

function cerrarSesion(){
	
	 $.getJSON('rest/usuario/logout', function(data) {
			
			
		       	}).success(function() { 
		       		window.location.href='index.jsp';
		       		})
		       	.error(function() {   })
		       	.complete(function() { 
		       	});

	
}

function updateTips(t) {
	tips.text(t).addClass("ui-state-highlight");
	setTimeout(function() {
		tips.removeClass("ui-state-highlight", 1500);
	}, 500);
}


function cambiaFechaDialogo(fecha){
	
	if(!isEmpty(fecha)){
	var arryFec = fecha.split("T");
	var temFec= arryFec[0];
	arryFec= temFec.split("-");
	
	
	return  arryFec[2]+"/"+arryFec[1]+"/"+arryFec[0];
	}else{
		return "";
	}
}

function checkLength(o, n, min, max, tips) {
	if (o.val().length > max || o.val().length < min) {
		o.addClass("ui-state-error");
		updateTips("Longitud de " + n + " debe ser entre " + min + " y "
				+ max + ".", tips);
		 alertMensajeTipo("Longitud de " + n + " debe ser entre " + min + " y "
					+ max + ".",true,2);
		return false;
	} else {
		return true;
	}
}

function checkRegexp(o, regexp, n, tips) {
	if (!(regexp.test(o.val()))) {
		o.addClass("ui-state-error");
		updateTips(n, tips);
		alertMensajeTipo(n,true,2);
		return false;
	} else {
		return true;
	}
}

function checkNumeric(o, tips) {
	if (!isNumber(o.val())) {
		o.addClass("ui-state-error");
		updateTips("El dato debe ser numerico.", tips);
		alertMensajeTipo("El dato debe ser numerico.",true,2);
		return false;
	} else {
		return true;
	}
}


function validaFloat(o,name)
{
  if (!/^([0-9])*[.]?[0-9]*$/.test(o.val()))
  {
	  o.addClass( "ui-state-error" );
	 
		updateTips("El valor de  : "+ name + "no es un valor decimal");
		alertMensajeTipo("El valor de  : "+ name + "no es un valor decimal",true,2);
	  return false;
   }
  else{
	 return true;
  }
}




function isNumber(n) {
	  return !isNaN(parseFloat(n)) && isFinite(n);
	}

/* Metodos de llenado de Combos */	


function llenaComboVar(opciones){
	   var options = '';
	   $.getJSON(opciones.valor==null? opciones.url:opciones.url+"/"+opciones.valor, function(data) {
			
		   if(opciones.combo){
			options = options + "<select id='"+opciones.htmlOptions.id+"' name='"+opciones.htmlOptions.name+"' class='"+opciones.htmlOptions.clase;
		  
			options += "'>";
			
			if(opciones.valorDefault){
				
				options += '<option value="null" selected="selected">-'+opciones.valorDefaultText+'-</option>';
			}
			
			  for(x=0;x<data.length;x++){
				if(opciones.index==data[x].clave)
				options += '<option value="' + data[x].clave + '" selected="selected">' + data[x].descripcion + '</option>';
				else
				options += '<option value="' + data[x].clave + '" >' + data[x].descripcion + '</option>'; 
			  }
			  options +="</select>"; 
			  

				if(opciones.delTag){
					
					$(opciones.tag).html("");
					 $(opciones.tag).html("<select id='"+opciones.htmlOptions.id+"'></select> ");
					 
				
					
				}
			  
			  
			  $(opciones.anchor).replaceWith(options);
			  
			  
			  
			  if (opciones.chained){
				 
				  
			  $(opciones.anchor).change(function() {
				 opciones.chain.valor=$(opciones.anchor).val();
				 llenaCombo(opciones.chain);
				 
			  });
			  $(opciones.anchor).change();
			  }
			  
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
			  
			  
		   }else{
			   
			  options +="<input id='"+opciones.htmlOptions.id+"' name='"+opciones.htmlOptions.name+"' readonly='readonly' class='"+opciones.htmlOptions.clase+"'  ";
			   //
			   for(x=0;x<data.length;x++){
					if(opciones.index==data[x].clave)
					options += 'value="' + data[x].descripcion + '"  >';
					
				  }
			   
			   

			   $(opciones.anchor).replaceWith(options);
		   }
			 // alert(options);
	       	}).success(function() {})
			       	.error(function() {   })
			       	.complete(function() { // alert("complete");
			       		if(!isEmpty(opciones.htmlOptions.valor))
			       			$("select#"+opciones.htmlOptions.id).val(opciones.htmlOptions.valor); 		    
			       		
			       		asignaEventorMunicipio();
			       		
			       		
			       		
			       	});
	       		
	  
	}



function llenaCombo(opciones){
	   var options = '';
	   $.getJSON(opciones.valor==null? opciones.url:opciones.url+"/"+opciones.valor, function(data) {
			
		   if(opciones.combo){
			options = options + "<select id='"+opciones.htmlOptions.id+"' name='"+opciones.htmlOptions.name+"' class='"+opciones.htmlOptions.clase;
		  
			options += "'>";
			
			if(opciones.valorDefault){
				
				options += '<option value="null" selected="selected">-'+opciones.valorDefaultText+'-</option>';
			}
			
			  for(x=0;x<data.length;x++){
				if(opciones.index==data[x].id)
				options += '<option value="' + data[x].id + '" selected="selected">' + data[x].descripcion + '</option>';
				else
				options += '<option value="' + data[x].id + '" >' + data[x].descripcion + '</option>'; 
			  }
			  options +="</select>"; 
			  

				if(opciones.delTag){
					
					$(opciones.tag).html("");
					 $(opciones.tag).html("<select id='"+opciones.htmlOptions.id+"'></select> ");
					 
				
					
				}
			  
			  
			  $(opciones.anchor).replaceWith(options);
			  
			  
			  
			  if (opciones.chained){
				 
				  
			  $(opciones.anchor).change(function() {
				 opciones.chain.valor=$(opciones.anchor).val();
				 llenaCombo(opciones.chain);
				 
			  });
			  $(opciones.anchor).change();
			  }
			  
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
			  
			  
		   }else{
			   
			  options +="<input id='"+opciones.htmlOptions.id+"' name='"+opciones.htmlOptions.name+"' readonly='readonly' class='"+opciones.htmlOptions.clase+"'  ";
			   //
			   for(x=0;x<data.length;x++){
					if(opciones.index==data[x].id)
					options += 'value="' + data[x].descripcion + '"  >';
					
				  }
			   
			   

			   $(opciones.anchor).replaceWith(options);
		   }
			 // alert(options);
	       	}).success(function() {})
			       	.error(function() {   })
			       	.complete(function() { // alert("complete");
			       		if(!isEmpty(opciones.htmlOptions.valor))
			       			$("select#"+opciones.htmlOptions.id).val(opciones.htmlOptions.valor); 		    
			       		
			       	//	asignaEventorMunicipio();
			       		
			       		
			       		
			       	});
	       		
	  
	}


function llenaFacultad(id){
	llenaCombo({
        url:CONTEXT_ROOT + '/solicitud/getFacultadesByInstitucion/' + id,
        htmlOptions:{
        	name:"facultad.id",
            id:"facultad",
            valor:id,
            clase:""
        },
        index:0,
        chained:false,
        anchor:"#institucionAcademica",
        combo:true
    });
}

function llenaComboFacultad(opciones)
{
	  
	   var options = '';
	   	
	   $.getJSON(opciones.valor==null? opciones.url:opciones.url+"/"+opciones.valor, function(data) {
		   
		   if(opciones.combo){
			   
			options = options + "<select id='"+opciones.htmlOptions.id+"' name='"+opciones.htmlOptions.name+"' class='"+opciones.htmlOptions.clase;
		  
			options += "'>";
			  for(x=0;x<data.length;x++){
				if(opciones.index==data[x].id)
				options += '<option value="' + data[x].id + '" selected="selected">' + data[x].descripcion + '</option>';
				else
				options += '<option value="' + data[x].id + '" >' + data[x].descripcion + '</option>'; 
			  }
			  options +="</select>"; 
			  $(opciones.anchor).replaceWith(options);
			  if (opciones.chained){
				 
				  
			  $(opciones.anchor).change(function() {
				 opciones.chain.valor=$(opciones.anchor).val();
				 llenaCombo(opciones.chain);
				 
			  });
			  $(opciones.anchor).change();
			  }
		   }else{
			   
			  options +="<input id='"+opciones.htmlOptions.id+"' name='"+opciones.htmlOptions.name+"' readonly='readonly' class='"+opciones.htmlOptions.clase+"'  ";
			   //
			   for(x=0;x<data.length;x++){
					if(opciones.index==data[x].id)
					options += 'value="' + data[x].descripcion + '"  >';
					
				  }

			   $(opciones.anchor).replaceWith(options);
		   }
			 // alert(options);
	       	}).success(function() {})
			       	.error(function() {   })
			       	.complete(function() { // alert("complete");
			       		if(!isEmpty(opciones.htmlOptions.valor))
			       			$("select#"+opciones.htmlOptions.id).val(opciones.htmlOptions.valor); 
			       	
			       		$("#facultad").change(function () {
							
					        var fac = $("#facultad").val();
					        llenaCombo({
					            url:CONTEXT_ROOT + '/solicitud/getCarrerasByFacultad/' + fac,
					            htmlOptions:{
					                name:"carrera.id",
					                id:"carrera",
					                clase:""
					            },
					            index:0,
					            chained:false,
					            anchor:"#carrera",
					            combo:true
					        });
					

		  				  });
		    
			       		
			       	 var fac = $("#facultad").val();
				        llenaCombo({
				            url:CONTEXT_ROOT + '/solicitud/getCarrerasByFacultad/' + fac,
				            htmlOptions:{
				                name:"carrera.id",
				                id:"carrera",
				                valor:$("#carre").val(),
				                clase:""
				            },
				            index:0,
				            chained:false,
				            anchor:"#carrera",
				            combo:true
				        });
			       	});
	       		
	  
	}





	function callRest(url,tipo,obJSON){
		
		
		$.ajax({
			url: url,
			type: tipo,
			contentType: 'application/json',
			data: JSON.stringify(obJSON),
			dataType: 'json',
			success: function(data){
				

				if (data.bandera == true) {
					alertMensaje("La operacion se realizo con ::"
							+ data.mensaje, true, 1);
					
				} else {
					alertMensaje("Comunicar al Departamento de Sistemas. Error:"
							+ data.mensaje, true, 3);
					
				}
				
			}
			}).error(function() {})
	       	.complete(function() { 
	       		
	       	
	       		
	       	});
		

		
	}

	function convertFecha(fecha) 
	{ 
		
		return fecha.getDate() + "."+(fecha.getMonth()+1)+"."+fecha.getFullYear();
	
	}
	
	function getLastDOM() 
	{ 
		  var lastDOM = new Date(); 
		  lastDOM.setTime(lastDOM.getTime() + ((32 - lastDOM.getDate()) * 86400000) ); 
		  lastDOM.setTime(lastDOM.getTime() - (lastDOM.getDate() * 86400000) ); 
		  return  lastDOM;
	} 	
	
	
	
	function getFirstDOM() 
	{ 
		  var firstDOM = new Date(); 
		 firstDOM.setDate(1);
		  
		 return firstDOM;
		
	}
	
function callRestConFn(url,tipo,obJSON, exito, error){
		
		
		$.ajax({
			url: url,
			type: tipo,
			contentType: 'application/json',
			data: JSON.stringify(obJSON),
			dataType: 'json',
			success: exito
		}).error(error)
       	.complete(function() { 
       		
       	
       	
		}
			);
		

		
	}


function callRestConFnComp(url,tipo,obJSON, exito, error){
	
	
	$.ajax({
		url: url,
		type: tipo,
		contentType: 'application/json',
		data: JSON.stringify(obJSON),
		dataType: 'json',
		success: exito
	}).error(error)
   	.complete(function() { 
   		
   	
   	
	}
		);
	

	
}

function cambiaFecha(fecha){
	
	
	fe = fecha.split("-");
	fechaNueva = fe[2]+"-"+fe[1]+"-"+fe[0];
	
	
	return fechaNueva;
}


function cambiaFecha1(fecha){
	
	
	fe = fecha.split("-");
	fechaNueva = fe[2]+"-"+fe[0]+"-"+fe[1];
	
	
	return fechaNueva;
}


function esFechaValida(fecha,name){
	  if (fecha != undefined && fecha.val() != "" ){
	    if (!/^\d{2}\/\d{2}\/\d{4}$/.test(fecha.val())){
	     
				fecha.addClass("ui-state-error");
				updateTips("Formato de fecha no valido (dd/mm/aaaa) para : "+name, tips);
				 alertMensaje("Formato de fecha no valido (dd/mm/aaaa) para : "+name,true,2);
	
	      return false;
	    }
	    var dia = parseInt(fecha.val().substring(0,2),10);
	    var mes = parseInt(fecha.val().substring(3,5),10);
	    var anio = parseInt(fecha.val().substring(6),10);
	
	  switch(mes){
	    case 1:
	    case 3:
	    case 5:
	    case 7:
	    case 8:
	    case 10:
	    case 12:
	     numDias=31;
	     break;
	    case 4: case 6: case 9: case 11:
	      numDias=30;
	      break;
	    case 2:
	      if (comprobarSiBisisesto(anio)){ numDias=29 }else{ numDias=28};
	      break;
	    default:
	    	fecha.addClass("ui-state-error");
				updateTips("Fecha introducida erronea en  : "+name, tips);
				 alertMensaje("Fecha introducida erronea en  : "+name,true,2);
	      return false;
	  }
	
	    if (dia>numDias || dia==0){
	    	fecha.addClass("ui-state-error");
				updateTips("Fecha introducida erronea en  : "+name, tips);
				 alertMensaje("Fecha introducida erronea en  : "+name,true,2);
	      return false;
	    }
	    return true;
	  }
	}

function comprobarSiBisisesto(anio){
	if ( ( anio % 100 != 0) && ((anio % 4 == 0) || (anio % 400 == 0))) {
	  return true;
	  }
	else {
	  return false;
	  }
	}

