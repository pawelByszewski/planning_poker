package poker

class GroomingParticipant {

    String name

    static belongsTo = [groomingSession: GroommingSession]
    static constraints = {
        name(nullable: false, blank: false)
    }
}
