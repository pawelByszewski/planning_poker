package poker

class GroomingSession {

    String sessionId

    static hasMany = [participants: GroomingParticipant]

    static constraints = {
        sessionId(nullable: false, blank: false, unique: true)
    }
}
