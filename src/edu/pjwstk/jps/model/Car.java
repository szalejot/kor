package edu.pjwstk.jps.model;

import java.util.Date;

public class Car {
    private String manufacturer;
    private String model;
    private String color;
    private Integer power;
    private Date yearOfProd;

    public Car(String manufacturer, String model, String color, Integer power, Date year) {
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.power = power;
        this.yearOfProd = year;
    }

    @Override
    public String toString() {
        return "Car[model=" + model + ", color=" + color + ", power=" + power + ", year=" + yearOfProd + "]";
    }

    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Integer getPower() {
        return power;
    }

    public void setPower(Integer power) {
        this.power = power;
    }

    public Date getYear() {
        return yearOfProd;
    }

    public void setYear(Date year) {
        this.yearOfProd = year;
    }

    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
