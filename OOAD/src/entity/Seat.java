package entity;

public class Seat {
    public Train train;
    public String seatId;
    public CarType carType;
    Seat(Train train, String seatId) {
        this.train = train;
        this.seatId = seatId;
    }
}
