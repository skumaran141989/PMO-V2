package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.models.TaskRequirement;

//DB partitioned or indexed on Document ID
public class TaskRequirementRepo {
	  private Map<Long, TaskRequirement> _taskRequirements;
	  private long _lastId;
	  
	  public TaskRequirementRepo() {
		  this._taskRequirements = new HashMap<Long, TaskRequirement>();
		  this._lastId=0;
	  }
	  
	  public long save(TaskRequirement requirement) {
		  Long id = requirement.getId();
		  if(id==0)
			  id=++this._lastId;
		  
		  this._taskRequirements.put(requirement.getId(), requirement);
		  return id;
	  }
	  
	  public Map<Long, TaskRequirement> getAll() {
		  return this._taskRequirements;
	  }
	  
	  public TaskRequirement get(long requirementId) {
		  return this._taskRequirements.get(requirementId);
	  }
	  
	  public void delete(long requirementId) {
		  this._taskRequirements.remove(requirementId);
	  }
}
