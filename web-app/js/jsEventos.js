
$(document).ready(
			
	function() {
		
					alert("valonsos");
			
	

					$('#fechaEmisionRespuesta').datepicker().on('changeDate', function(ev) {
	
						$('#fechaEmisionRespuestaE').val($('#fechaEmisionRespuesta').data('date'));
						$('fechaEmisionRespuesta').datepicker('hide');

					});
					
					
					$('#date-start')
					.datepicker()
					.on(
							'changeDate',
							function(ev) {
								if (ev.date.valueOf() > endDate
										.valueOf()) {
									// $('#botonVisible').hide();
									bandera1 = false;
									$('#alert')
											.show()
											.find('strong')
											.text(
													'La fecha de inicio no puede ser mayor a la fecha final.');
								} else {
									$('#alert').hide();
									bandera1 = true;
									startDate = new Date(ev.date);
									$('#datestart').val($('#date-start').data('date'));
								}

								$('#date-start').datepicker('hide');
							});

					$('#date-end')
					.datepicker()
					.on('changeDate',
							function(ev) {
								if (ev.date.valueOf() < startDate
										.valueOf()) {
									// $('#botonVisible').hide();
									bandera2 = false;
									$('#alert')
											.show()
											.find('strong')
											.text(
													'La fecha final debe ser mayor a la fecha inicial.');
								} else {
									$('#alert').hide();
									bandera2 = true;
									endDate = new Date(ev.date);

									$('#dateend')
											.val(
													$('#date-end').data('date'));

								}

								$('#date-end').datepicker('hide');
							});


    });