package pmo.project.models;

import java.util.HashMap;
import java.util.Map;

public class ProjectFeasibilityReport {

	private Map<String, String> _failureResons;
	private String _projectName;
	
	public ProjectFeasibilityReport() {
		this._failureResons = new HashMap<String, String>();
	}
	
	public Map<String, String> getFailureResons() {
		return this._failureResons;
	}
	
	public void setProjectName(String projectName) {
		this._projectName =  projectName;
	}
	
	public String getProjectName() {
		return this._projectName;
	}
}
