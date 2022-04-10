package pmo.project.handlers.query;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.handlers.response.ProjectFeasibilityReport;

public class QueryProjectFeasibilityReportExecutor extends Handler {
	
	@Override
	public HandlerResponse<ProjectFeasibilityReport> process(Object request) {
		HandlerResponse<ProjectFeasibilityReport> response = new HandlerResponse<ProjectFeasibilityReport>();
		try {
		
			String reportId = (String) request;
		
			ProjectFeasibilityReport report = _reportRepo.get(reportId);
			response.setObject(report);
		}
		catch(Exception ex) {
			response.getErrorResponse();
		}
		
		return response;
	}
}
