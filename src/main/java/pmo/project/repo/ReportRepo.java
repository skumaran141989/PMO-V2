package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.handlers.response.ProjectFeasibilityReport;

public class ReportRepo {
  private Map<String, ProjectFeasibilityReport> _projectFeasibilityReport;
  
  public ReportRepo() {
	  _projectFeasibilityReport = new HashMap<String, ProjectFeasibilityReport>();
  }
  
  public void save(ProjectFeasibilityReport projectFeasibilityReport) {
	  _projectFeasibilityReport.put(projectFeasibilityReport.getReportId(), projectFeasibilityReport);
  }
  
  public Map<String, ProjectFeasibilityReport> getAll() {
	  return _projectFeasibilityReport;
  }
  
  public ProjectFeasibilityReport get(String reportId) {
	  return _projectFeasibilityReport.get(reportId);
  }
}
