package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.HumanResource;

public class Supervisor extends HumanResource {
	private int _supervisingExperience;
	private boolean _certfied;
	
	public Supervisor(int supervisingExperience, boolean certfied, String firstName, String lastName, long id, String contactNumber, double salary) {
		_supervisingExperience = supervisingExperience;
		_certfied = certfied;
	
		super.setContactNumber(contactNumber);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setId(id);
		super.setSalary(salary);
	}
	
	public void setSupervisingExperience(int supervisingExperience) {
		_supervisingExperience = supervisingExperience;
	}
	
	public int getSupervisingExperience() {
		return _supervisingExperience;
	}
	
	public void setCertfication(boolean certfied) {
		_certfied = certfied;
	}
	
	public boolean getCertfication() {
		return _certfied;
	}
}
