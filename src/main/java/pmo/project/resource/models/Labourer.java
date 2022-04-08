package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.HumanResource;

public class Labourer extends HumanResource {
	public String _skill;
	public int _experience;
	
	public Labourer(String skillSet, int experience, String firstName, String lastName, long id, String contactNumber, double salary) {
		_skill = skillSet;
		_experience = experience;
		
		super.setContactNumber(contactNumber);
		super.setFirstName(firstName);
		super.setLastName(lastName);
		super.setId(id);
		super.setSalary(salary);
	}
	
	public void setSkill(String skill) {
		_skill=skill;
	}
	
	public String getSkillSet() {
		return _skill;
	}
	
	public void setExperience(int experience) {
		_experience = experience;
	}
	
	public int getExperience() {
		return _experience;
	}
}
