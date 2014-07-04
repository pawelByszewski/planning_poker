class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/rest/grooming/$sessionId/join" (controller: "groomingSession", action: "addUser")
        "/rest/grooming/$sessionId/estimate" (controller: "groomingSession", action: "addEstimate")
        "/grooming/$sessionId/newTask" (controller: "groomingSession", action: "newTask")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
