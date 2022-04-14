package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.response.HandlerResponse;

public class CreateProjectHandler extends Handler {

	@Override
	public HandlerResponse<Boolean> process(Object request) { 
		
		ProjectCreationRequest projectCreationRequest = (ProjectCreationRequest) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(projectCreationRequest);
		response.setObject(true);
		
		return response;
	}
	
	private void execute(ProjectCreationRequest projectCreationRequest)
	{	
		this._projectManagementservice.createProject(projectCreationRequest);
	}
}