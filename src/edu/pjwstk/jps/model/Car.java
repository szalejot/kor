package edu.pjwstk.jps.model;

import java.util.Date;

public class Car {
    private Integer nr;
    private String manufacturer;
    private String model;
    private String color;
    private Date yearOfProd;
    private Company owner;
    
    public Car(Integer nr, String manufacturer, String model, String color, Date year, Company owner) {
        this.nr=nr;
        this.manufacturer = manufacturer;
        this.model = model;
        this.color = color;
        this.yearOfProd = year;
        this.owner=owner;
    }

    @Override
    public String toString() {
        return "Car[model=" + model + ", color=" + color  + ", year=" + yearOfProd + "]";
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

    public Date getYear() {
        return yearOfProd;
    }

    public void setYear(Date year) {
        this.yearOfProd = year;
    }

    public Integer getNr() {
        return nr;
    }

    public void setNr(Integer nr) {
        this.nr = nr;
    }

    public Company getOwner() {
        return owner;
    }

    public void setOwner(Company owner) {
        this.owner = owner;
    }
    public String getManufacturer() {
        return manufacturer;
    }

    public void setManufacturer(String manufacturer) {
        this.manufacturer = manufacturer;
    }

}
