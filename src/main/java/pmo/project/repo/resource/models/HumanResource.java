package pmo.project.repo.resource.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "HumanResource")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class HumanResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
    private String _firstName;
    private String _lastName;
    private String _contactNumber;
    private double _salary;
    private boolean _isUsable;
    private String _type;
	private Date _createdAt;
	private Date _updatedAt;
	private String _createdBy;
	private String _updatedBy;
    
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
	
    public void setCreatedDate(Date createdAt) {
    	this._createdAt = createdAt;
    }
    
    public Date getCreatedDate() {
    	return this._createdAt;
    }
    
    public void setUpdatedDate(Date updatedAt) {
    	this._updatedAt = updatedAt;
    }
    
    public Date getUpdatedDate() {
    	return this._updatedAt;
    }
    
    public void setCreatedBy(String createdBy) {
    	this._createdBy = createdBy;
    }
    
    public String getcreatedBy() {
    	return this._createdBy;
    }
    
    public void setUpdatedBy(String updatedBy) {
    	this._updatedBy = updatedBy;
    }
    
    public String getUpdatedBy() {
    	return this._updatedBy;
    }
}
