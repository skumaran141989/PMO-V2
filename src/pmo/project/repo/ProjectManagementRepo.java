package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.Project;

public class ProjectManagementRepo {
  private Map<String, Project> _projects;
  
  public ProjectManagementRepo() {
	  _projects = new HashMap<String, Project>();
  }
  
  public void save(Project project) {
	  _projects.put(project.getTitle(), project);
  }
  
  public Map<String, Project> getAll() {
	  return _projects;
  }
  
  public Project getProject(String projectName) {
	  return _projects.get(projectName);
  }
}
