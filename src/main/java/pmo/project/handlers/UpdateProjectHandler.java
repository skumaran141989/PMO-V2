package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Project;

public class UpdateProjectHandler extends Handler {

	@Override
	public HandlerResponse<Boolean> process(Object request) {
		Project resource = (Project) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(resource);
		response.setObject(true);
		
		return response;
	}  
	
	@Async("Level2")
	private void execute(Project resource)
	{
		_projectManagementRepo.save(resource);
	}

}
