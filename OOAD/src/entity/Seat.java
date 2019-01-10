package entity;

public class Seat {
    Train train;
    String seatId;
    Seat(Train train, String seatId) {
        this.train = train;
        this.seatId = seatId;
    }
}
