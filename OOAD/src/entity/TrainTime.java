package entity;
import java.time.* ;

public class TrainTime {
    int trainId;
    LocalDate date;
    LocalTime fromTime;
    LocalTime toTime;
    TrainTime(int trainId, LocalDate date, LocalTime fromTime, LocalTime toTime) {
        this.trainId = trainId;
        this.date = date;
        this.fromTime = fromTime;
        this.toTime = toTime;
    }
}
