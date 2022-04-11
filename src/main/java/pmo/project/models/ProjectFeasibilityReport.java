package pmo.project.models;

import java.util.HashMap;
import java.util.Map;

public class ProjectFeasibilityReport {

	private Map<String, String> _failureResons;
	private String _projectName;
	
	public ProjectFeasibilityReport() {
		_failureResons = new HashMap<String, String>();
	}
	
	public Map<String, String> getFailureResons() {
		return _failureResons;
	}
	
	public void setProjectName(String projectName) {
		_projectName =  projectName;
	}
	
	public String getProjectName() {
		return _projectName;
	}
}
