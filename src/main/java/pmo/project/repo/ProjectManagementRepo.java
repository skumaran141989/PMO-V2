package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.models.Project;

public class ProjectManagementRepo {
	  private Map<Long, Project> _projects;
	  private long _lastId=0;
	  
	  public ProjectManagementRepo() {
		  this._projects = new HashMap<Long, Project>();
		  this._lastId = 0;
	  }
	  
	  public long save(Project project) {
		  Long id = project.getId();
		  if(id==0)
			  id=++_lastId;
			  
		  this._projects.put(id, project);
		  
		  return id;
	  }
	  
	  public Map<Long, Project> getAll() {
		  return this._projects;
	  }
	  
	  public Project get(Long projectId) {
		  return this._projects.get(projectId);
	  }
	  
	  public void delete(Long projectId) {
		  this._projects.remove(projectId);
	  }
}
