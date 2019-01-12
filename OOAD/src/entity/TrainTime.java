package entity;
import java.time.* ;

public class TrainTime {
    public int trainId;
    public LocalDate date;
    public LocalTime fromTime;
    public LocalTime toTime;
    public TrainTime(int trainId, LocalDate date, LocalTime fromTime, LocalTime toTime) {
        this.trainId = trainId;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}
