class UrlMappings {

	static mappings = {
        "/$controller/$action?/$id?(.$format)?"{
            constraints {
                // apply constraints here
            }
        }

        "/grooming/$sessionId/join" (controller: "groomingSession", action: "addUser")
        "/grooming/$sessionId/estimate" (controller: "groomingSession", action: "addEstimate")
        "/grooming/$sessionId/newTask" (controller: "groomingSession", action: "newTask")
        "/"(view:"/index")
        "500"(view:'/error')
	}
}
