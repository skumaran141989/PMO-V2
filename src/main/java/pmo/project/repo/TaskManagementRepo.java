package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.models.Task;

//DB partitioned or indexed on Task ID
public class TaskManagementRepo {
	  private Map<Long, Task> _tasks;
	  private long _lastId;
	  
	  public TaskManagementRepo() {
		  this._tasks = new HashMap<Long, Task>();
		  this._lastId=0;
	  }
	  
	  public long save(Task task) {
		  Long id = task.getId();
		  if(id==0)
			  id=++this._lastId;
		  
		  //this will be a async process	  
		  this._tasks.put(id, task);
		  
		  return id;
	  }
	  
	  public Map<Long, Task> getAll() {
		  return this._tasks;
	  }
	  
	  public Task get(Long taskId) {
		  return this._tasks.get(taskId);
	  }
	  
	  public void delete(Long taskId) {
		  this._tasks.remove(taskId);
	  }
}
