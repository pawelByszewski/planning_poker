package poker

class GroommingSession {

    String sessionId

    static constraints = {
        sessionId(nullable: false, blank: false, unique: true)
    }
}
