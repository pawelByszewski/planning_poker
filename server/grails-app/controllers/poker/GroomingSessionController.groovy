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

    def add() {
        render([id: params.id, name: params.name] as JSON)
    }
}
