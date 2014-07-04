package poker
import grails.converters.JSON
import org.apache.commons.lang.RandomStringUtils

class GroomingSessionController {

    def index() {
        String planningSessionKey = RandomStringUtils.random(9, true, true)
        GroommingSession groommingSession = new GroommingSession(sessionId: planningSessionKey)
        groommingSession.save(true)
        [planningSessionKey: planningSessionKey]
    }

    def addUser() {
        GroomingParticipant groomingParticipant = new GroomingParticipant(name: params.name)
        GroommingSession groommingSession = GroommingSession.findBySessionId(params.sessionId)
        groommingSession.addToParticipants(groomingParticipant)
        groommingSession.save(true)
        render([id: groomingParticipant.id] as JSON)
    }
}
