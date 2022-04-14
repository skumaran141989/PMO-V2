package pmo.project.handlers.request;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class ProjectCreationRequest {
    private String _name;
    private String _description;
    private List<TaskCreationRequest> _taskRequests;
    private Date _startDate; 
    private Date _dueDate;
    
    public ProjectCreationRequest() {
    	this._taskRequests = new ArrayList<TaskCreationRequest>();
    }
    
    public void setName(String name) {
    	this._name = name;
    }
    
    public String getName() {
    	return this._name;
    }
    
    public void setDescription(String description) {
    	this._description = description;
    }
    
    public String getDescription() {
    	return this._description;
    }
    
    public void setTaskrequests(List<TaskCreationRequest> taskRequests) {
    	this._taskRequests=taskRequests;
    }
    
    public List<TaskCreationRequest> getTaskrequests() {
    	return this._taskRequests;
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
}
