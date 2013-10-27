package edu.pjwstk.jps.model;

public class Title {
    private String name;

    public Title(String name) {
        this.name = name;
    }
    
    @Override
    public String toString() {
        return "Title [name=" + name + "]";
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

}
