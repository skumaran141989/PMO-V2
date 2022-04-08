package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.Task;

public class TaskManagementRepo {
  private Map<String, Task> _projects;
  
  public TaskManagementRepo() {
	  _projects = new HashMap<String, Task>();
  }
  
  public void save(Task task) {
	  _projects.put(task.getTitle(), task);
  }
  
  public Map<String, Task> getAll() {
	  return _projects;
  }
  
  public Task get(String projectName) {
	  return _projects.get(projectName);
  }
}
