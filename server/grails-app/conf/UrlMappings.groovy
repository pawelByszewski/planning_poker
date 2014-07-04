class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "rest/grooming/${sessionId}/join" (controller: "groomingSession", action: "addUser")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
