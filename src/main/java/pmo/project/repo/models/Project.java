package pmo.project.repo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pmo.project.enums.Status;

@Entity
@Table(name = "Project")
public class Project {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
	private String _description;
	private String _title;
    private Status _status;
    private Date _startDate;
    private Date _dueDate;
    private int _progress;
	private String _reasonForStoppage;
	private Date _createdAt;
	private Date _updatedAt;
	private String _createdBy;
	private String _updatedBy;
    
    public Project(String description, String title) {
    	this._description = description;
    	this._title = title;
    }
    
    public void setDescription(String description) {
    	this._description = description;
    }
    
    public String getDescription() {
    	return this._description;
    }
    
    public void setTitle(String title) {
    	this._title = title;
    }
    
    public String getTitle() {
    	return this._title;
    }
    
    public void setStatus(Status status) {
    	this._status = status;
    }
    
    public Status getStatus() {
    	return this._status;
    }
    
    public void setStartDate(Date startDate) {
    	this._startDate = startDate;
    }
    
    public Date getStartDate() {
    	return this._startDate;
    }
    
    public void setDueDate(Date dueDate) {
    	this._dueDate = dueDate;
    }
    
    public Date getDueDate() {
    	return this._dueDate;
    }
    
	public void setProgress(int progress) {
		this._progress = progress;
	}
	
	public int getProgress() {
		return this._progress;
	}
	
	public void setReasonForStoppage(String reasonForStoppage) {
		this._reasonForStoppage = reasonForStoppage;
	}
	
	public String getReasonForStoppage() {
		return this._reasonForStoppage;
	}
	
	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
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
