package pmo.project.repo.models;

import pmo.project.enums.TaskExecutionStatus;
import pmo.project.enums.TaskExecutionType;

public class TaskExecution {
	private long _id;
	private long _taskId;
	private TaskExecutionType _taskExecutiontype;
	private TaskExecutionStatus _taskExecutionStatus;
	private int _priority;
	
	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
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
}
