package pmo.project.models;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

import pmo.project.enums.Status;
import pmo.project.resource.models.abstraction.HumanResource;
import pmo.project.resource.models.abstraction.MaterialResource;

public class Task {
	private Status _status;
	private Project _assignedProject;
	private Task _parentTask;
	private String _description;    
	private String _title;
	private List<TaskDependency> _tasks;
	private List<HumanResource> _humanResources;
	private List<MaterialResource> _materialResources;
	private int _progress;
	private int _taskWeight;
	private long _timeTaken;
	private Date _startDate;
	private Date _expectedCompletionDate;
	private String _reasonForStoppage;
	private List<DocumentInfo> _documents;
	
	public Task(String description, String title, long timeTaken, Project assignedProject, Task parentTask, int taskWeight) {
		this._description = description;
		this._title = title;
		this._assignedProject = assignedProject;
		this._parentTask = parentTask;
		this._taskWeight = taskWeight;
		this._timeTaken = timeTaken;
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
		
		if(status==Status.COMPLETED) {
			this._progress = 100;
			if(this._parentTask!=null)
				this._parentTask.setProgress( this._taskWeight+ this._parentTask.getProgress());
			else if(_assignedProject!=null)
				this._assignedProject.setProgress( this._taskWeight+ this._assignedProject.getProgress());
		}
	}
	
	public Status getStatus() {
		return this._status;
	}
	
    public void setTaskDependecies(TaskDependency task) {
    	this._tasks.add(task);
    }
    
    public List<TaskDependency> getTaskDependecies() {
    	return this._tasks;
    }
    
    public void setHumanResources(HumanResource resource) {
    	this._humanResources.add(resource);
    }
    
    public List<HumanResource> getHumanResources() {
    	return this._humanResources;
    }
    
    public void setMaterialResources(MaterialResource resource) {
    	this._materialResources.add(resource);
    }
    
    public List<MaterialResource> getMaterialResources() {
    	return this._materialResources;
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
	
	public void setExpectedCompletionDate(Date expectedCompletionDate) {
		this._expectedCompletionDate = expectedCompletionDate;
	}
	
	public Date getExpectedCompletionDate() {
		return this._expectedCompletionDate;
	}
	
	public void setReasonForStoppage(String reasonForStoppage) {
		if(this._parentTask!=null)
			this._parentTask.setReasonForStoppage( this._parentTask.getReasonForStoppage().concat(reasonForStoppage));
		if(this._assignedProject!=null)
			this._assignedProject.setReasonForStoppage(this._assignedProject.getReasonForStoppage().concat(reasonForStoppage));
		
		this._reasonForStoppage = reasonForStoppage;
	}
	
	public String getReasonForStoppage() {
		return this._reasonForStoppage;
	}
	
	public void setAssignedProject(Project assignedProject) {
		this._assignedProject = assignedProject;
	}
	
	public Project getAssignedProject() {
		return this._assignedProject;
	}
	
	public Task getParentTaskProject() {
		return this._parentTask;
	}
	
    public void setTimeTaken(int timeTaken) {
    	this._timeTaken = timeTaken;
    }
    
    public long getTimeTaken() {
    	return this._timeTaken;
    }
    
    public void setDocuments(Object document) {
    	DocumentInfo doc = new DocumentInfo(UUID.randomUUID().toString() ,document);
    	
    	this._documents.add(doc);
    }
    
    public List<DocumentInfo>  getDocuments() {
    	return this._documents;
    }
}
