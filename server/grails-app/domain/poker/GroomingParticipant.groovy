package poker

class GroomingParticipant {

    String name

    static belongsTo = [groomingSession: GroomingSession]
    static constraints = {
        name(nullable: false, blank: false)
    }
}
