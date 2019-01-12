package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Train {
    public int trainId;
    public LocalDate date;
    /**
     * @author jimlin
     * a time list record (departure time, arrival time) for a given station index
     */
    // 每站是否停下(false: 否, true: 是)
    public boolean[] stopPattern;
    // 每站出發時間
    public LocalTime[] departureTimes; 
    // 每站抵達時間
    public LocalTime[] arrivalTimes;
    /**
     * @author jimlin and yao
     * @param trainId
     * @param date
     * @param departure times 
     * @param arrival times
     * @param stop pattern(會停在哪幾站)
     */
    Train(int trainId, LocalDate date, boolean[] stopPattern, LocalTime[] departureTimes, LocalTime[] arrivalTimes) {
        this.trainId = trainId;
        this.date = date;
        this.stopPattern = stopPattern;
        this.departureTimes = departureTimes;
        this.arrivalTimes = arrivalTimes;
    }
    /**
     * @author jimlin
     * Train class provide an interface for retrieving TrainTime given 'from' and 'to'
     * @return TrainTime
     */
    public TrainTime getTrainTime(int from, int to) throws RuntimeException{
    	// 
    	if((!stopPattern[from]) || (!stopPattern[to])) {
    		throw new RuntimeException("from or to is invalid(this train does not stop at from or to)");
    	}
    	int trainId = this.trainId;
    	LocalDate date = this.date;
    	LocalTime fromTime = this.departureTimes[from];
    	LocalTime toTime = this.arrivalTimes[to];
    	
    	return new TrainTime(trainId, date, fromTime, toTime);
    }
}
