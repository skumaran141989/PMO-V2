package pmo.project.handlers.request;

import java.util.HashMap;
import java.util.Map;

public class TaskCreationRequest {
    private Map<String, Integer> _humanResource;
    private Map<String, Integer> _materialResource;
    private Map<TaskCreationRequest, Integer> _taskRequests;
    private long _timeTaken;
    private String _name;
    private String _description;
    private String _projectName;
    private long _remainingHours;
    
    public TaskCreationRequest() {
    	_humanResource = new HashMap<String, Integer>();
    	_materialResource = new HashMap<String, Integer>();
    	_taskRequests = new HashMap<TaskCreationRequest, Integer>();
    }
    
    public void setHumanResource(String resource, int quantity) {
    	_humanResource.put(resource, quantity);
    }
    
    public Map<String, Integer> getHumanResource() {
    	return _humanResource;
    }
    
    public void setMaterialResource(String resource, int quantity) {
    	_materialResource.put(resource, quantity);
    }
    
    public Map<String, Integer> getMaterialResource() {
    	return _materialResource;
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
    
    public void setTimeTaken(long timeTaken) {
    	_timeTaken = timeTaken;
    }
    
    public long getTimeTaken() {
    	return _timeTaken;
    }
    
    public void setTaskrequests(TaskCreationRequest taskrequest, int weight) {
    	_taskRequests.put(taskrequest, weight);
    }
    
    public Map<TaskCreationRequest, Integer> getTaskrequests() {
    	return _taskRequests;
    }
    
    public void setProjectName(String projectName) {
    	_projectName = projectName;
    }
    
    public String getProjectName() {
    	return _projectName;
    }
    
    public void setRemainingHours(long remainingHours) {
    	_remainingHours = remainingHours;
    }
    
    public long getRemainingHours() {
    	return _remainingHours;
    }
}
