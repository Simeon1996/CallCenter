package base;

public class Call {
    private Rank rank;
    private final Caller caller;
    private Employee handler;

    public Call(Caller c) {
        rank = Rank.Responder;
        caller = c;
    }

    public void setHandler(Employee e) {
        handler = e;
    }

    public void reply(String message) {
        System.out.println(message);
    }

    public Rank getRank() {
        return rank;
    }

    public Rank incrementRank() {
        if (rank == Rank.Responder) {
            rank = Rank.Manager;
        } else if (rank == Rank.Manager) {
            rank = Rank.Director;
        }

        return rank;
    }

    public void finish() {
        reply("Thank you for calling");
    }
}
