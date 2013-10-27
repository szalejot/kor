package edu.pjwstk.jps.model;

public class CompanyBranch {
    private String name;
    
    public CompanyBranch(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "CompanyBranch [name=" + name + "]";
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getName() {
        return this.name;
    }
}
