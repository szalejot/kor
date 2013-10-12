package edu.pjwstk.jps.model;

import java.util.Date;

public class Labors {
	
	private String fName;
	private String lName;
	private Date birthDate;
	private Boolean sex;
	private Double salary;
	private Date workSince;
	private Title hasTitle;
	private Company worksAt;
	
	public Labors(String fName, String lName,Date birthDate, Boolean sex,Company worksAt, Title hasTitle, Double salary, Date workSince){
		super();
		this.fName=fName;
		this.lName=lName;
		this.birthDate=birthDate;
		this.sex=sex;
		this.hasTitle=hasTitle;
		this.salary=salary;
		this.workSince=workSince;
		this.worksAt=worksAt;
	}

	public String getfName() {
		return fName;
	}

	public void setfName(String fName) {
		this.fName = fName;
	}

	public String getlName() {
		return lName;
	}

	public void setlName(String lName) {
		this.lName = lName;
	}

	public Double getSalary() {
		return salary;
	}

	public void setSalary(Double salary) {
		this.salary = salary;
	}

	public Title getHasTitle() {
		return hasTitle;
	}

	public void setHasTitle(Title hasTitle) {
		this.hasTitle = hasTitle;
	}

	public Company getWorksAt() {
		return worksAt;
	}

	public void setWorksAt(Company worksAt) {
		this.worksAt = worksAt;
	}

	public Boolean getSex() {
		return sex;
	}

	public Date getWorkSince() {
		return workSince;
	}

	public Date getBirthDate() {
		return birthDate;
	}
	

}
