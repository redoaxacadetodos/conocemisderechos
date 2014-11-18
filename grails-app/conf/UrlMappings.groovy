class UrlMappings {

	static mappings = {
		"/$controller/$action?/$id?"{
			constraints {
				// apply constraints here
			}
		}

			"/"(controller:"publico", action: "indicadores")
		"500"(view:'/error')
	}
}
