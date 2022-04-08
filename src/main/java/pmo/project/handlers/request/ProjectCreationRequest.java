package pmo.project.handlers.request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ProjectCreationRequest {
    private String _name;
    private String _description;
    private Map<TaskCreationRequest, Integer> _taskRequests;
    private Date _startDate; 
    private Date _dueDate;
    
    public ProjectCreationRequest() {
    	_taskRequests = new HashMap<TaskCreationRequest, Integer>();
    }
    
    public void setName(String name) {
    	_name = name;
    }
    
    public String getName() {
    	return _name;
    }
    
    public void setDescription(String description) {
    	_description = description;
    }
    
    public String getDescription() {
    	return _description;
    }
    
    public void setTaskrequests(TaskCreationRequest taskrequest, int weight) {
    	_taskRequests.put(taskrequest, weight);
    }
    
    public Map<TaskCreationRequest, Integer> getTaskrequests() {
    	return _taskRequests;
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
}
