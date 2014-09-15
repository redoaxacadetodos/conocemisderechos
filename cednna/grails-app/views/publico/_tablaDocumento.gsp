<g:datatablehelperjs ctrlid="table"
			context="${request.getContextPath()}" controller="publico"
			action="documentoList" jqueryui="true"
			lang="${resource(dir:'js', file:'langtabla.json')}"
			whereadicional="doc_tipo=${tipo } and doc_nivel=${nivel }"
			aoColumns="['{bVisible: false}', '{mData:1 }', '{mData:2, bVisible: false}', '{mData:3, bVisible: false}', '{mData:descargar, bSortable: false }']"/>
			
			<g:datatablehelper ctrlid="table"
				cols="['Id', 'Nombre del documento', 'Nivel', 'Tipo', 'Archivo']" class="table table-striped table-bordered"></g:datatablehelper>