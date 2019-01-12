package entity;

public class Ticket {
    Seat seat;
    int from;
    int to;
    TicketType ticketType;
    /**
     * @param seat
     * @param from
     * @param to
     * @param type
     */
    public Ticket(Seat seat, int from, int to, TicketType type) {
        this.seat = seat;
        this.from = from;
        this.to = to;
        this.ticketType = type;
    }
}
