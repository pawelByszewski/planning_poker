package poker
import grails.converters.JSON
import org.apache.commons.lang.RandomStringUtils

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
        brokerMessagingTemplate.convertAndSend "/add/user", params.name
        render([id: groomingParticipant.id] as JSON)
    }

    def addEstimate() {
        GroomingParticipant groomingParticipant = GroomingParticipant.findById(params.userId)
        groomingParticipant.estimate = params.estimate
        groomingParticipant.save()

        GroomingSession groommingSession = GroomingSession.findBySessionId(params.sessionId)
        List<GroomingParticipant> unreadyParticipants = GroomingParticipant.findAllByGroomingSessionAndEstimateLessThan(groomingParticipant, 0)
        if(unreadyParticipants.size() == 0) {
            List<GroomingParticipant> participants = groommingSession.getParticipants()
            float average = ((participants*.estimate.sum() as Integer)/participants.size()) as Float
            brokerMessagingTemplate.convertAndSend("/finalEstimate", params.name)
        }
    }
}
