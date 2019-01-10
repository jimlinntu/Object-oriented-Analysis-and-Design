package entity;

public class Ticket {
    Seat seat;
    int from;
    int to;
    String type;
    Ticket(Seat seat, int from, int to, String type) {
        this.seat = seat;
        this.from = from;
        this.to = to;
        this.type = type;
    }
}
