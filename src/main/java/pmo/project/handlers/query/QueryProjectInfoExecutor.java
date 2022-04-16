package pmo.project.handlers.query;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Project;

public class QueryProjectInfoExecutor extends Handler {
	
	@Override
	public HandlerResponse<Project> process(Object request) {
		
		HandlerResponse<Project> response = new HandlerResponse<Project>();
		
		try {
		
			String projectId = (String) request;
		
			Project project = this._projectManagementservice.getProjectByName(projectId);
			response.setObject(project);
		}
		catch(Exception ex) {
			response.getErrorResponse();
		}
		
		return response;
	}
}
