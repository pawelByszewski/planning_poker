package poker
import grails.converters.JSON
import org.apache.commons.lang.RandomStringUtils
import org.springframework.web.multipart.commons.CommonsMultipartFile
import poker.task.reader.Task

class GroomingSessionController {

    def brokerMessagingTemplate
    def xmlTaskReaderService

    def index() {
        CommonsMultipartFile xmlTaskFile = request.getFile('taskFile') as CommonsMultipartFile
        List<Task> tasks = xmlTaskReaderService.readTasks(xmlTaskFile.inputStream)
        String planningSessionKey = RandomStringUtils.random(9, true, true)
        GroomingSession groommingSession = new GroomingSession(sessionId: planningSessionKey)
        groommingSession.save(true)
        [planningSessionKey: planningSessionKey, tasks: tasks]
    }

    def addUser() {
        GroomingParticipant groomingParticipant = new GroomingParticipant(name: params.name)
        GroomingSession groommingSession = GroomingSession.findBySessionId(params.sessionId)
        groommingSession.addToParticipants(groomingParticipant)
        groommingSession.save(true)
        brokerMessagingTemplate.convertAndSend "/topic/adduser", params.name
        render([id: groomingParticipant.id] as JSON)
    }

    def addEstimate() {
        GroomingParticipant groomingParticipant = GroomingParticipant.findById(params.userId)
        groomingParticipant.estimate = Integer.parseInt(params.estimate)
        groomingParticipant.save(flush: true)

        GroomingSession groommingSession = GroomingSession.findBySessionId(params.sessionId)
        List<GroomingParticipant> unreadyParticipants = GroomingParticipant.findAllByGroomingSessionAndEstimateLessThan(groommingSession, 0)
        if(unreadyParticipants.size() == 0) {
            Set<GroomingParticipant> participants = groommingSession.getParticipants()
            float average = ((participants*.estimate.sum() as Integer)/participants.size()) as Float
            brokerMessagingTemplate.convertAndSend("/topic/finalEstimate", average)
        }
    }

    def newTask() {
        GroomingSession groommingSession = GroomingSession.findBySessionId(params.sessionId)
        groommingSession.participants.each{
            it.estimate = -1
            it.save()
        }
        render [:]
    }

    def readTaskFile() {
        CommonsMultipartFile xmlTaskFile = request.getFile('taskFile') as CommonsMultipartFile
        List<Task> tasks = xmlTaskReaderService.readTasks(xmlTaskFile.inputStream)
        render (view: 'taskTable', model: [tasks: tasks])
    }
    
}
