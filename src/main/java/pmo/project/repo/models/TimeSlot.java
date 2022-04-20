package pmo.project.repo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TimeSlot")
public class TimeSlot {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
	private Date _startDate;
	private Date _endDate;
	private long _taskId;
	private long _resourceId;
	private Date _createdAt;
	private Date _updatedAt;
	private String _createdBy;
	private String _updatedBy;
	
	public TimeSlot(Date startDate, Date endDate, long resourceId, long taskId) {
		this._startDate = startDate;
		this._endDate = endDate;
		this._taskId = taskId;
		this._resourceId = resourceId;
	}
	
	public Date getStartDate() {
		return this._startDate;
	}
	
	public void setStartDate(Date startDate) {
		this._startDate = startDate;
	}
	
	public Date getEndDate() {
		return this._endDate;
	}
	
	public void setEndDate(Date endDate) {
		this._endDate = endDate;
	}
	
	public long getTaskId() {
		return this._taskId;
	}
	
	public void setTaskId(long taskId) {
		this._taskId = taskId;
	}
	
	public long getResourceId() {
		return this._resourceId;
	}
	
	public void setResourceId(long resourceId) {
		this._resourceId = resourceId;
	}
	
	public long getId() {
		return this._id;
	}
	
	public void setId(long id) {
		this._id = id;
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
