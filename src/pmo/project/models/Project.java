package pmo.project.models;

import java.util.List;

import pmo.project.enums.Status;

public class Project {
	private String _description;
	private String _title;
    private List<TaskDependency> _tasks;
    private Status _status;
    private String _startDate; 
    private String _dueDate;
    private int _progress;
    
    public Project(String description, String title) {
    	_description = description;
    	_title = title;
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
    
    public void setStartDate(String startDate) {
    	_startDate = startDate;
    }
    
    public String getStartDate() {
    	return _startDate;
    }
    
    public void setDueDate(String dueDate) {
    	_dueDate = dueDate;
    }
    
    public String getDueDate() {
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
}
