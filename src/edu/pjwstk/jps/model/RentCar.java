package edu.pjwstk.jps.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class RentCar {
    private Labor labor;
    private Car car;
    private Date date;

    public RentCar(Labor labor, Car car, Date date) {
        this.labor = labor;
        this.car = car;
        this.date = date;
    }

    public Labor getLabor() {
        return labor;
    }

    public void setLabor(Labor labor) {
        this.labor = labor;
    }

    public Car getCar() {
        return car;
    }

    public void setCar(Car car) {
        this.car = car;
    }

    public Date getDate() {
        return date;
    }

    public void setDate(Date date) {
        this.date = date;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "RenCar [labor=" + labor + ", car=" + car + ", date=" + df.format(date) + "]";
    }
}
