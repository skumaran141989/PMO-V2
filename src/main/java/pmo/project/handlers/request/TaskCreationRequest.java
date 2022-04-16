package pmo.project.handlers.request;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class TaskCreationRequest {
	private int _requirementsId;
	private Map<String, Integer> _humanResource;
	private Map<String, Integer> _materialResource;
	private int _daysTaken;
	private String _description;
	private long _projectId;
	private String _taskName;
	private long _blockingTaskId;
	private Date _createdDate;
	private Date _updatedDate;
	
	public TaskCreationRequest() {
		this._humanResource = new HashMap<String, Integer>();
		this._materialResource = new HashMap<String, Integer>();
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
	
	
	public void setDescription(String description) {
		this._description = description;
	}
	
	public String getDescription() {
		return this._description;
	}
	
	public void setRequirementsId(int requirementsId) {
		this._requirementsId = requirementsId;
	}
	
	public int getRequirementsId() {
		return this._requirementsId;
	}
	
	public void setProjectId(long projectId) {
		this._projectId = projectId;
	}
	
	public long getProjectId() {
		return this._projectId;
	}
	
	public void setTasktName(String taskName) {
		this._taskName = taskName;
	}
	
	public String getTaskName() {
		return this._taskName;
	}
	
	public void setBlockingTaskId(long blockingTaskId) {
		this._blockingTaskId = blockingTaskId;
	}
	
	public long getBlockingTaskId() {
		return this._blockingTaskId;
	}
	
	public int getDaysTaken() {
		return this._daysTaken;
	}
	
	public void setDaysTaken(int daysTaken) {
		this._daysTaken = daysTaken;
	}
	
	public Date getCreatedDate() {
		return this._createdDate;
	}
	
	public void setCreatedDate(Date createdDate) {
		this._createdDate = createdDate;
	}
	
	public Date getUpdatedDate() {
		return this._updatedDate;
	}
	
	public void setUpdatedDate(Date updatedDate) {
		this._updatedDate = updatedDate;
	}
}
