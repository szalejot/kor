package edu.pjwstk.jps.model;

public class Title {
	private String name;
	

	public Title(){
		super();
	}
	
	@Override
	public String toString() {
		return "Title [name=" + name + "]";
	}
	
	public void addTitle(String name){
		this.name=name;
	}
	public String getTitle(){
		return this.name;
	}

}
