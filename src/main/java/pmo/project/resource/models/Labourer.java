package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.HumanResource;

public class Labourer extends HumanResource {
	public String _skill;
	public int _experience;
	
	public Labourer(String skillSet, int experience, String firstName, String lastName, long id, String contactNumber, double salary) {
		this._skill = skillSet;
		this._experience = experience;
		
		super.setContactNumber(contactNumber);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setId(id);
		super.setSalary(salary);
	}
	
	public void setSkill(String skill) {
		this._skill=skill;
	}
	
	public String getSkillSet() {
		return this._skill;
	}
	
	public void setExperience(int experience) {
		this._experience = experience;
	}
	
	public int getExperience() {
		return this._experience;
	}
}
