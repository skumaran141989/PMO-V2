package pmo.project.repo.models;

import java.util.Date;

import pmo.project.enums.Status;

public class Project {
	private long _id;
	private String _description;
	private String _title;
    private Status _status;
    private Date _startDate;
    private Date _dueDate;
    private int _progress;
	private String _reasonForStoppage;
    
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
}
