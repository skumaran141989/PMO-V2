package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Project;

public class CreateProjectHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request) { 
		
		ProjectCreationRequest projectCreationRequest = (ProjectCreationRequest) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(projectCreationRequest);
		response.setObject(true);
		
		return response;
	} 
	
	@Async("Level3")
	private void execute(ProjectCreationRequest projectCreationRequest)
	{	
		Project project = new Project(projectCreationRequest.getDescription(), projectCreationRequest.getName(), projectCreationRequest.getTaskrequests());
		_projectManagementRepo.save(project);
	}
}