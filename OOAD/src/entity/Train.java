package entity;

import java.time.LocalDate;
import java.time.LocalTime;

public class Train {
    public int trainId;
    public LocalDate date;
    /**
     * @author jimlin and yao
     * @param trainId
     * @param date
     */
    Train(int trainId, LocalDate date) {
        this.trainId = trainId;
        this.date = date;
    }
    /**
     * @author yao
     * Train class provide an interface for retrieving TrainTime given 'from' and 'to'
     * @return TrainTime
     */
    public TrainTime getTrainTime(int from, int to) throws Exception {
        return DataAccessObject.getTrainTime(this,from,to);
    }
}
