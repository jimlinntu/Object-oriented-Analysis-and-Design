package entity;
import java.time.* ;

public class TrainTime {
    String trainId;
    LocalDate date;
    LocalTime fromTime;
    LocalTime toTime;
    TrainTime(String trainId, LocalDate date, LocalTime fromTime, LocalTime toTime) {
        this.trainId = trainId;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}
