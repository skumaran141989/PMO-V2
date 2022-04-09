package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.Task;

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
  
  public Task get(String projectName) {
	  return _tasks.get(projectName);
  }
}
