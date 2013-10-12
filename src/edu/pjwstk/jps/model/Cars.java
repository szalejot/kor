package edu.pjwstk.jps.model;

import java.util.Collection;
import java.util.Date;

public class Cars {
	private Integer nr;
	private String manufacturer;
	private String model;
	private String color;
	private Integer power;
	private Collection<Labors> owner;
	private Date yearOfProd;
	private Collection<Date> startRent;
	private Collection<Date> endRent;
	
	public Cars(Integer nr, String manufacturer, String model, String color, Integer power,
			 Date year) {
		super();
		this.setNr(nr);
		this.setManufacturer(manufacturer);
		this.model = model;
		this.color = color;
		this.power = power;
		this.yearOfProd = year;
	}
	
	public void rentCar(Labors owner, Date start, Date end){
		this.owner.add(owner);
		this.startRent.add(start);
		this.endRent.add(end);
	}
	
	@Override
	public String toString() {
		return "Car[model="+model+", color="+color+", power="+power+", year="+yearOfProd+"]";
	}
	
	public String rentToString(){
		String text = "-";
		while (owner.iterator().hasNext()){			
			text = "|"+owner.iterator().toString()+" |" + startRent.iterator().toString()+" |" + endRent.iterator().toString()+" |";
			owner.iterator().next();
			startRent.iterator().next();
			endRent.iterator().next();			
		}
		return text;
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
	public Collection<Labors> getOwner() {
		return owner;
	}
	public void setOwner(Collection<Labors> owner) {
		this.owner = owner;
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

	public Integer getNr() {
		return nr;
	}

	public void setNr(Integer nr) {
		this.nr = nr;
	}
	
	
}
