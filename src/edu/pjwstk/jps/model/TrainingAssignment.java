package edu.pjwstk.jps.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class TrainingAssignment {

    private Labor labor;
    private Training training;
    private Date startDate;

    public TrainingAssignment(Labor labor, Training training, Date startDate) {
        super();
        this.labor = labor;
        this.training = training;
        this.startDate = startDate;
    }

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }

    public Training getTraining() {
        return training;
    }

    public void setTraining(Training training) {
        this.training = training;
    }

    public Date getStartDate() {
        return startDate;
    }

    public void setStartDate(Date startDate) {
        this.startDate = startDate;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "TrainingAssignment [labor=" + labor + ", training=" + training + ", startDate=" + df.format(startDate)
                + "]";
    }

}
