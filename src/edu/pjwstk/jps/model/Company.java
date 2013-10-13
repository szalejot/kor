package edu.pjwstk.jps.model;

public class Company {

    private String name;
    private Company partOf;
    private CompanyBranch type;
    private Labor director;

    public Company(String name, Company partOf, CompanyBranch type) {
        super();
        this.setName(name);
        this.setPartOf(partOf);
        this.setType(type);
    }

    @Override
    public String toString() {
        return "Company [name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Company getPartOf() {
        return partOf;
    }

    public void setPartOf(Company partOf) {
        this.partOf = partOf;
    }

    public CompanyBranch getType() {
        return type;
    }

    public void setType(CompanyBranch type) {
        this.type = type;
    }

    public Labor getDirector() {
        return director;
    }

    public void setDirector(Labor director) {
        this.director = director;
    }

}
