package edu.pjwstk.jps.model;

public class CompanyBranch {
	private String name;

	public CompanyBranch(){
		super();
	}
	
	@Override
	public String toString() {
		return "CompanyBranch [name=" + name + "]";
	}
	public void addCompanyBranch(String name)
	{
		this.name=name;
	}
	public String getCompanyBranch(){
		return this.name;
	}
}
