package edu.pjwstk.jps.model;

import java.text.SimpleDateFormat;
import java.util.Date;

public class Labor {

    private String fName;
    private String lName;
    private Date birthDate;
    private Character sex; // M or F
    private Double salary;
    private Date workSince;
    private Title title;
    private Date hasTitleSince;
    private Company worksAt;

    public Labor(String fName, String lName, Date birthDate, Character sex, Company worksAt, Title title,
            Double salary,Date hasTitleSince, Date workSince) {
        super();
        this.fName = fName;
        this.lName = lName;
        this.birthDate = birthDate;
        this.sex = sex;
        this.title = title;
        this.hasTitleSince=hasTitleSince;
        this.salary = salary;
        this.workSince = workSince;
        this.worksAt = worksAt;
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

    public Title getTitle() {
        return title;
    }

    public void setTitle(Title hasTitle) {
        this.title = hasTitle;
    }

    public Company getWorksAt() {
        return worksAt;
    }

    public void setWorksAt(Company worksAt) {
        this.worksAt = worksAt;
    }

    public Character getSex() {
        return sex;
    }

    public Date getWorkSince() {
        return workSince;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public Date getHasTitleSince() {
        return hasTitleSince;
    }

    public void setHasTitleSince(Date hasTitleSince) {
        this.hasTitleSince = hasTitleSince;
    }

    @Override
    public String toString() {
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
        return "Labor [fName=" + fName + ", lName=" + lName + ", birthDate=" + birthDate + ", sex=" + sex + ", salary="
                + salary + ", workSince=" + df.format(workSince) + ", hasTitle=" + title + ", worksAt=" + worksAt
                + "]";
    }

}
