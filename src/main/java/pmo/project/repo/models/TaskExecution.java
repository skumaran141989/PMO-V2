package pmo.project.repo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

import pmo.project.enums.TaskExecutionStatus;
import pmo.project.enums.TaskExecutionType;

@Entity
@Table(name = "TaskExecution")
public class TaskExecution {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
	private long _executionRequestId;
	private long _taskId;
	private TaskExecutionType _taskExecutiontype;
	private TaskExecutionStatus _taskExecutionStatus;
	private int _priority;
	private Date _startDate;
	private Date _completionDate;
	private Date _createdAt;
	private Date _updatedAt;
	private String _createdBy;
	private String _updatedBy;
	
	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
	}
	
	public void setExecutionRequestId(long executionRequestId) {
		this._executionRequestId = executionRequestId;
	}
	
	public long getExecutionRequestId() {
		return this._executionRequestId;
	}
	
	public void setTaskId(long id) {
		this._taskId = id;
	}
	
	public long getTaskId() {
		return this._taskId;
	}
	
	public void setTaskExecutiontype(TaskExecutionType taskExecutiontype) {
		this._taskExecutiontype = taskExecutiontype;
	}
	
	public TaskExecutionType getaskExecutiontype() {
		return this._taskExecutiontype;
	}
	
	public void setTaskExecutiontype(TaskExecutionStatus taskExecutionStatus) {
		this._taskExecutionStatus = taskExecutionStatus;
	}
	
	public TaskExecutionStatus geetTaskExecutiontype() {
		return this._taskExecutionStatus;
	}
	
	public void setPriority(int priority) {
		this._priority = priority;
	}
	
	public int getPriority() {
		return this._priority;
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
    
    public Date getStartDate() {
    	return this._startDate;
    }
    
    public void setStartDate(Date startDate) {
    	this._startDate = startDate;
    }
    
    public Date getDueDate() {
    	return this._completionDate;
    }
    
    public void setDueDate(Date completionDate) {
    	this._completionDate = completionDate;
    }
}
