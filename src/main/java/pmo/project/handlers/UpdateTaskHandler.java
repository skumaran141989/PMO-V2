package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Task;

public class UpdateTaskHandler extends Handler {

	@Override
	public HandlerResponse<Boolean> process(Object request) {
		Task resource = (Task) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(resource);
		response.setObject(true);
		
		return response;
	}
	
	@Async("Level2")
	private void execute(Task resource)
	{
		_taskManagementRepo.save(resource);
	}
}
