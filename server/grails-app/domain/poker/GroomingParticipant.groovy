package poker

class GroomingParticipant {

    String name;
    int estimate

    static belongsTo = [groomingSession: GroomingSession]
    static constraints = {
        name(nullable: false, blank: false)
        estimate(nullable: true, blank: false)
    }
}
