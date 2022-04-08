package com.project.handlers.request;

import java.util.List;

public class ProjectCreationRequest {
    private String _name;
    private String _description;
    private List<TaskCreationRequest> _taskRequests;
    
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
    
    public void setTaskrequests(TaskCreationRequest taskrequest) {
    	_taskRequests.add(taskrequest);
    }
    
    public List<TaskCreationRequest> getTaskrequests() {
    	return _taskRequests;
    }
}
