package pmo.project.models;

import java.util.Date;
import java.util.List;
import java.util.Map;

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
    
    public Project(String description, String title, Map<TaskCreationRequest,Integer> requirements) {
    	_description = description;
    	_title = title;
    	_requirements = requirements;
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
    }
    
    public Status getStatus() {
    	return _status;
    }
    
    public void setStartDate(Date startDate) {
    	_startDate = startDate;
    }
    
    public Date getStartDate() {
    	return _startDate;
    }
    
    public void setDueDate(Date dueDate) {
    	_dueDate = dueDate;
    }
    
    public Date getDueDate() {
    	return _dueDate;
    }
    
    public void setTaskDependecies(TaskDependency task) {
    	_tasks.add(task);
    }
    
    public List<TaskDependency> getTaskDependecie() {
    	return _tasks;
    }
    
	public void setProgress(int progress) {
		_progress = progress;
	}
	
	public int getProgress() {
		return _progress;
	}
	
	public void setReasonForStoppage(String reasonForStoppage) {
		_reasonForStoppage = reasonForStoppage;
	}
	
	public String getReasonForStoppage() {
		return _reasonForStoppage;
	}
	
	public Map<TaskCreationRequest,Integer> getRequirements() {
		return _requirements;
	}
}
