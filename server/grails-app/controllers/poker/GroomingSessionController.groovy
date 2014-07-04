package poker
import grails.converters.JSON
import org.apache.commons.lang.RandomStringUtils
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo

class GroomingSessionController {

    def brokerMessagingTemplate

    def index() {
        String planningSessionKey = RandomStringUtils.random(9, true, true)
        GroomingSession groommingSession = new GroomingSession(sessionId: planningSessionKey)
        groommingSession.save(true)
        [planningSessionKey: planningSessionKey]
    }



    def addUser() {
        GroomingParticipant groomingParticipant = new GroomingParticipant(name: params.name)
        GroomingSession groommingSession = GroomingSession.findBySessionId(params.sessionId)
        groommingSession.addToParticipants(groomingParticipant)
        groommingSession.save(true)
        brokerMessagingTemplate.convertAndSend "/topic/adduser", params.name
        render([id: groomingParticipant.id] as JSON)
    }

    @MessageMapping("/adduser")
    @SendTo("/topic/adduser")
    def example() {
        return "hello from controller!"
    }

}
