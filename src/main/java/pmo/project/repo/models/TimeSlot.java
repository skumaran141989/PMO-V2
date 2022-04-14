package pmo.project.repo.models;

import java.util.Date;

public class TimeSlot {
	private long _id;
	private Date _startDate;
	private Date _endDate;
	private long _taskId;
	private long _resourceId;
	
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
	

}
