package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Task;

public class UpdatetTaskHandler extends Handler {

	@Override
	@Async("TaskExecutor")
	public HandlerResponse process(Object request) {
		Task resource = (Task) request;
		_taskManagementRepo.save(resource);
		
		return null;
	}  
}
