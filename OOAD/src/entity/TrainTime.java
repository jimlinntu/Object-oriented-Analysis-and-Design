package entity;
import java.time.* ;
import java.time.format.DateTimeFormatter;

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
    
    public String toString() {
    	DateTimeFormatter dateFormatter = DateTimeFormatter.ofPattern("yyyy/MM/dd");
    	DateTimeFormatter timeFormatter = DateTimeFormatter.ofPattern("HH:mm");
    	long minutesBetween = Duration.between(this.fromTime, this.toTime).toMinutes();
    	
    	return String.format("車次號碼: %d, 日期: %s, 出發時間:%s, 到達時間: %s, 行車時間: %d:%d", 
    			this.trainId, this.date.format(dateFormatter), 
    			this.fromTime.format(timeFormatter), this.toTime.format(timeFormatter), 
    			minutesBetween/60, minutesBetween%60);
    }
}
