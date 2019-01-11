package entity;

public class Ticket {
    Seat seat;
    int from;
    int to;
    int type;
    /**
     * @param seat
     * @param from
     * @param to
     * @param type
     */
    Ticket(Seat seat, int from, int to, int type) {
        this.seat = seat;
        this.from = from;
        this.to = to;
        this.type = type;
    }
}
