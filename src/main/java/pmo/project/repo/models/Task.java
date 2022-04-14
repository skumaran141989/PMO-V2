package pmo.project.repo.models;

import java.util.Date;

import pmo.project.enums.Status;

public class Task {
	private long _id;
	private Status _status;
	private long _projectId;
	private long _blockingTaskId;
	private String _description;    
	private String _title;
	private int _progress;
	private int _daysTaken;
	private Date _startDate;
	private Date _completionDate;
	private String _reasonForStoppage;
	
	public Task(String description, String title, int daysTaken, long projectId, long blockingTaskId) {
		this._description = description;
		this._title = title;
		this._projectId = projectId;
		this._blockingTaskId = blockingTaskId;
		this._daysTaken = daysTaken;
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
    
	public void setProgress(int progress) {
		this._progress = progress;
	}
	
	public int getProgress() {
		return this._progress;
	}
	
	public void setStartDate(Date startDate) {
		this._startDate = startDate;
	}
	
	public Date getStartDate() {
		return this._startDate;
	}
	
	public void setCompletionDate(Date completionDate) {
		this._completionDate = completionDate;
	}
	
	public Date getCompletionDate() {
		return this._completionDate;
	}
	
	public void setReasonForStoppage(String reasonForStoppage) {
		this._reasonForStoppage = reasonForStoppage;
	}
	
	public String getReasonForStoppage() {
		return this._reasonForStoppage;
	}
	
	public void setProjectId(long projectId) {
		this._projectId = projectId;
	}
	
	public long getProjectId() {
		return this._projectId;
	}
	
	public void setBlockingTaskId(long blockingTaskId) {
		this._blockingTaskId = blockingTaskId;
	}
	
	public long getBlockingTaskId() {
		return this._blockingTaskId;
	}

	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
	}
	
	public void setDaysTaken(int daysTaken) {
		this._daysTaken = daysTaken;
	}
	
	public int getDaysTaken() {
		return this._daysTaken;
	}
}
