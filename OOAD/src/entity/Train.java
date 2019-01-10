package entity;

import java.time.LocalDate;

public class Train {
    int trainId;
    LocalDate date;
    Train(int trainId, LocalDate date) {
        this.trainId = trainId;
        this.date = date;
    }
}
