package entity;

public class Seat {
    public Train train;
    public String seatId;
    public CarType carType;
    Seat(Train train, String seatId, CarType carType) {
        this.train = train;
        this.seatId = seatId;
        this.carType = carType;
    }
}
