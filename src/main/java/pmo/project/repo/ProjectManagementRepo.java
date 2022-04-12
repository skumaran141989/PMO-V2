package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

//DB partitioned or indexed on project name
import pmo.project.models.Project;

public class ProjectManagementRepo {
  private Map<String, Project> _projects;
  
  public ProjectManagementRepo() {
	  this._projects = new HashMap<String, Project>();
  }
  
  public void save(Project project) {
	  this._projects.put(project.getTitle(), project);
  }
  
  public Map<String, Project> getAll() {
	  return this._projects;
  }
  
  public Project get(String projectName) {
	  return this._projects.get(projectName);
  }
  
  public void delete(String projectTitle) {
	  this._projects.remove(projectTitle);
  }
}
