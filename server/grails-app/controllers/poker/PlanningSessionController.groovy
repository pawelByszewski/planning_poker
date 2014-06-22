package poker

import grails.converters.JSON
import grails.validation.ValidationException
import org.apache.commons.lang.RandomStringUtils

class PlanningSessionController {

    def index() {
        String planningSessionKey = RandomStringUtils.random(9, true, true)
        GroommingSession groommingSession = new GroommingSession(sessionId: planningSessionKey)
        groommingSession.save(true)
        GroommingSession groommingSession2 = new GroommingSession(sessionId: planningSessionKey)
        try {
            groommingSession2.save(true)
        } catch (ValidationException validationException) {
            log.debug("sdf")
//            validationException.errors.each { it.getFieldError()}
        }
            [planningSessionKey: planningSessionKey]
    }

    def add() {
        render([id: params.id, name: params.name] as JSON)
    }
}
