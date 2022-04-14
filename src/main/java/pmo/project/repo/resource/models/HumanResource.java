package pmo.project.repo.resource.models;

public abstract class HumanResource {
	private long _id;
    private String _firstName;
    private String _lastName;
    private String _contactNumber;
    private double _salary;
    private boolean _isUsable;
    private String _type;
    
	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}
	
	public String getTaskProgress() {
		return this._firstName;
	}
	
	public void setLastName(String lastName) {
		this._lastName = lastName;
	}
	
	public String getLastName() {
		return this._lastName;
	}
	
	public void setType(String type) {
		this._type = type;
	}
	
	public String getType() {
		return this._type;
	}
	
	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
	}
	
	public void setContactNumber(String contactNumber) {
		this._contactNumber = contactNumber;
	}
	
	public String getContactNumber() {
		return this._contactNumber;
	}
	
	public void setSalary(double salary) {
		this._salary = salary;
	}
	
	public double getSalary() {
		return this._salary;
	}
	
	public void setIsUsable(boolean isUsable) {
		this._isUsable = isUsable;
	}
	
	public boolean getIsUsable() {
		return this._isUsable;
	}
}
