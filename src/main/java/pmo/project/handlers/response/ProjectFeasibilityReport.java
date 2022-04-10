package pmo.project.handlers.response;

import java.util.HashMap;
import java.util.Map;

public class ProjectFeasibilityReport {

	private Map<String, String> _failureResons;
	private String _reportId;
	private String _projectName;
	
	public ProjectFeasibilityReport() {
		_failureResons = new HashMap<String, String>();
	}
	
	public Map<String, String> getFailureResons() {
		return _failureResons;
	}
	
	public void setReportId(String reportId) {
		_reportId =  reportId;
	}
	
	public String getReportId() {
		return _reportId;
	}
	
	public void setProjectName(String projectName) {
		_projectName =  projectName;
	}
	
	public String getProjectName() {
		return _projectName;
	}
}
