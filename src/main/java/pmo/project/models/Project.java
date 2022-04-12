package pmo.project.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import pmo.project.enums.Status;
import pmo.project.handlers.request.TaskCreationRequest;

public class Project {
	private String _description;
	private String _title;
    private List<TaskDependency> _tasks;
    private Map<TaskCreationRequest,Integer> _requirements;
    private Status _status;
    private Date _startDate; 
    private Date _dueDate;
    private int _progress;
	private String _reasonForStoppage;
	private List<DocumentInfo> _documents;
    
    public Project(String description, String title, Map<TaskCreationRequest,Integer> requirements) {
    	this._description = description;
    	this._title = title;
    	this._requirements = requirements;
    	this._documents = new ArrayList<DocumentInfo>();
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
    
    public void setTaskDependecies(TaskDependency task) {
    	this._tasks.add(task);
    }
    
    public List<TaskDependency> getTaskDependecies() {
    	return this._tasks;
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
	
	public Map<TaskCreationRequest,Integer> getRequirements() {
		return this._requirements;
	}
	
    public void setDocuments(Object document) {
    	DocumentInfo doc = new DocumentInfo(UUID.randomUUID().toString(), document);
    	
    	this._documents.add(doc);
    }
    
    public List<DocumentInfo>  getDocuments() {
    	return this._documents;
    }
}
