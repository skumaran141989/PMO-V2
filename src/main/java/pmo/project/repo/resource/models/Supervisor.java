package pmo.project.repo.resource.models;

public class Supervisor extends HumanResource {
	private int _supervisingExperience;
	private boolean _certfied;
	
	public Supervisor(int supervisingExperience, boolean certfied, String firstName, String lastName, long id, String contactNumber, double salary) {
		this._supervisingExperience = supervisingExperience;
		this._certfied = certfied;
	
		super.setContactNumber(contactNumber);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setId(id);
		super.setSalary(salary);
	}
	
	public void setSupervisingExperience(int supervisingExperience) {
		this._supervisingExperience = supervisingExperience;
	}
	
	public int getSupervisingExperience() {
		return this._supervisingExperience;
	}
	
	public void setCertfication(boolean certfied) {
		this._certfied = certfied;
	}
	
	public boolean getCertfication() {
		return this._certfied;
	}
}
