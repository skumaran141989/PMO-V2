package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.models.TaskExecution;

//DB partitioned or indexed on TaskExecution ID
public class TaskExecutionManagementRepo {
	  private Map<Long, TaskExecution> _taskExecutions;
	  private long _lastId;
	  
	  public TaskExecutionManagementRepo() {
		  this._taskExecutions = new HashMap<Long, TaskExecution>();
		  this._lastId=0;
	  }
	  
	  public long save(TaskExecution taskExecution) {
		  Long id = taskExecution.getId();
			
		  if (id == 0 ) {
			  id = ++this._lastId;
		  }
		  
		  //this will be a async process	  
		  this._taskExecutions.put(id, taskExecution);
		  
		  return id;
	  }
	  
	  public Map<Long, TaskExecution> getAll() {
		  return this._taskExecutions;
	  }
	  
	  public TaskExecution get(Long taskId) {
		  return this._taskExecutions.get(taskId);
	  }
	  
	  public void delete(Long taskId) {
		  this._taskExecutions.remove(taskId);
	  }
}
