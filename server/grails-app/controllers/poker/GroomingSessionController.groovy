package poker

import grails.converters.JSON
import org.apache.commons.lang.RandomStringUtils

class GroomingSessionController {

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
        render([id: groomingParticipant.id] as JSON)
    }
}
