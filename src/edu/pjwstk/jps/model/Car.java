package edu.pjwstk.jps.model;

import java.util.Date;

public class Car {
    private Integer nr;
    private String manufacturer;
    private String type;
    private String color;
    private Date prodDate;
    private Company owner;
    
    public Car(Integer nr, String manufacturer, String type, String color, Date prodDate, Company owner) {
        this.nr=nr;
        this.manufacturer = manufacturer;
        this.type = type;
        this.color = color;
        this.prodDate = prodDate;
        this.owner=owner;
    }

    @Override
    public String toString() {
        return "Car[model=" + type + ", color=" + color  + ", year=" + prodDate + "]";
    }

    public String getType() {
        return type;
    }

    public void setType(String model) {
        this.type = model;
    }

    public String getColor() {
        return color;
    }

    public void setColor(String color) {
        this.color = color;
    }

    public Date getProdDate() {
        return prodDate;
    }

    public void setProdDate(Date prodDate) {
        this.prodDate = prodDate;
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
