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
    private int _weightToParent;
    private String _taskName;
    
    public TaskCreationRequest() {
    	this._humanResource = new HashMap<String, Integer>();
    	this._materialResource = new HashMap<String, Integer>();
    	this._taskRequests = new HashMap<TaskCreationRequest, Integer>();
    }
    
    public void setHumanResource(String resource, int quantity) {
    	this._humanResource.put(resource, quantity);
    }
    
    public Map<String, Integer> getHumanResource() {
    	return this._humanResource;
    }
    
    public void setMaterialResource(String resource, int quantity) {
    	this._materialResource.put(resource, quantity);
    }
    
    public Map<String, Integer> getMaterialResource() {
    	return this._materialResource;
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
    
    public void setTimeTaken(long timeTaken) {
    	this._timeTaken = timeTaken;
    }
    
    public long getTimeTaken() {
    	return this._timeTaken;
    }
    
    public void setTaskrequests(TaskCreationRequest taskrequest, int weight) {
    	this._taskRequests.put(taskrequest, weight);
    }
    
    public Map<TaskCreationRequest, Integer> getTaskrequests() {
    	return this._taskRequests;
    }
    
    public void setProjectName(String projectName) {
    	this._projectName = projectName;
    }
    
    public String getProjectName() {
    	return this._projectName;
    }
    
    public void setRemainingHours(long remainingHours) {
    	this._remainingHours = remainingHours;
    }
    
    public long getRemainingHours() {
    	return this._remainingHours;
    }
    
    public void setWeightToParent(int weightToParent) {
    	this._weightToParent = weightToParent;
    }
    
    public int getWeightToParent() {
    	return this._weightToParent;
    }
    
    
    public void setTasktName(String taskName) {
    	this._taskName = taskName;
    }
    
    public String getTaskName() {
    	return this._taskName;
    }
}
