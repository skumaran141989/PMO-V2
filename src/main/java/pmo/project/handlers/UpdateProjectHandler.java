package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Project;

public class UpdateProjectHandler extends Handler {

	@Override
	@Async("ProjectTaskExecutor")
	public HandlerResponse process(Object request) {
		Project resource = (Project) request;
		_projectManagementRepo.save(resource);
		
		return null;
	}  

}
