<script>
	$(document).ready(function() {
		$('#calendar').fullCalendar({
	        eventRender: function(event, element) { 
				element.find('.fc-title').attr("data-uk-tooltip", "{pos:'bottom-left'}");
				element.find('.fc-title').attr("title", event.description);
				element.find('.fc-title').append(event.description);
	        }, 
			events: ${eventos }
		});
	});
</script>

<div id='calendar'></div>
