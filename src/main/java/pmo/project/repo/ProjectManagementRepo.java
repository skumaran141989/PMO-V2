package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

//DB partitioned or indexed on project name
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
  
  public Project get(String projectName) {
	  return _projects.get(projectName);
  }
  
  public void delete(String projectTitle) {
	  _projects.remove(projectTitle);
  }
}
