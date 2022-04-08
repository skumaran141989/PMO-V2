package pmo.project.models;

import java.util.Date;
import java.util.List;

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
	
	public Task(String description, String title, long timeTaken, Project assignedProject, Task parentTask, int taskWeight) {
		_description = description;
		_title = title;
		_assignedProject = assignedProject;
		_parentTask = parentTask;
		_taskWeight = taskWeight;
		_timeTaken = timeTaken;
	}
	
    public void setDescription(String description) {
    	_description = description;
    }
    
    public String getDescription() {
    	return _description;
    }
    
    public void setTitle(String title) {
    	_title = title;
    }
    
    public String getTitle() {
    	return _title;
    }
	
	public void setStatus(Status status) {
		_status = status;
		
		if(status==Status.COMPLETED) {
			_progress = 100;
			if(_parentTask!=null)
				_parentTask.setProgress( _taskWeight+ _parentTask.getProgress());
			else if(_assignedProject!=null)
				_assignedProject.setProgress( _taskWeight+ _assignedProject.getProgress());
		}
	}
	
	public Status getStatus() {
		return _status;
	}
	
    public void setTaskDependecies(TaskDependency task) {
    	_tasks.add(task);
    }
    
    public List<TaskDependency> getTaskDependecies() {
    	return _tasks;
    }
    
    public void setHumanResources(HumanResource resource) {
    	_humanResources.add(resource);
    }
    
    public List<HumanResource> getHumanResources() {
    	return _humanResources;
    }
    
    public void setMaterialResources(MaterialResource resource) {
    	_materialResources.add(resource);
    }
    
    public List<MaterialResource> getMaterialResources() {
    	return _materialResources;
    }
    
	public void setProgress(int progress) {
		_progress = progress;
	}
	
	public int getProgress() {
		return _progress;
	}
	
	public void setStartDate(Date startDate) {
		_startDate = startDate;
	}
	
	public Date getStartDate() {
		return _startDate;
	}
	
	public void setExpectedCompletionDate(Date expectedCompletionDate) {
		_expectedCompletionDate = expectedCompletionDate;
	}
	
	public Date getExpectedCompletionDate() {
		return _expectedCompletionDate;
	}
	
	public void setReasonForStoppage(String reasonForStoppage) {
		if(_parentTask!=null)
			_parentTask.setReasonForStoppage( _parentTask.getReasonForStoppage().concat(reasonForStoppage));
		if(_assignedProject!=null)
			_assignedProject.setReasonForStoppage(_assignedProject.getReasonForStoppage().concat(reasonForStoppage));
		
		_reasonForStoppage = reasonForStoppage;
	}
	
	public String getReasonForStoppage() {
		return _reasonForStoppage;
	}
	
	public void setAssignedProject(Project assignedProject) {
		_assignedProject = assignedProject;
	}
	
	public Project getAssignedProject() {
		return _assignedProject;
	}
	
	public Task getParentTaskProject() {
		return _parentTask;
	}
	
    public void setTimeTaken(int timeTaken) {
    	_timeTaken = timeTaken;
    }
    
    public long getTimeTaken() {
    	return _timeTaken;
    }
}
