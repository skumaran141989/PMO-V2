package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.Task;

//DB partitioned or indexed on Task ID
public class TaskManagementRepo {
  private Map<String, Task> _tasks;
  
  public TaskManagementRepo() {
	  this._tasks = new HashMap<String, Task>();
  }
  
  public void save(Task task) {
	  this._tasks.put(task.getTitle(), task);
  }
  
  public Map<String, Task> getAll() {
	  return this._tasks;
  }
  
  public Task get(String taskName) {
	  return this._tasks.get(taskName);
  }
  
  public void delete(String taskName) {
	  this._tasks.remove(taskName);
  }
}
