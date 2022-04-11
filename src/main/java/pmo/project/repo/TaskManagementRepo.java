package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.Task;

//DB partitioned or indexed on Task ID
public class TaskManagementRepo {
  private Map<String, Task> _tasks;
  
  public TaskManagementRepo() {
	  _tasks = new HashMap<String, Task>();
  }
  
  public void save(Task task) {
	  _tasks.put(task.getTitle(), task);
  }
  
  public Map<String, Task> getAll() {
	  return _tasks;
  }
  
  public Task get(String taskName) {
	  return _tasks.get(taskName);
  }
  
  public void delete(String taskName) {
	  _tasks.remove(taskName);
  }
}
