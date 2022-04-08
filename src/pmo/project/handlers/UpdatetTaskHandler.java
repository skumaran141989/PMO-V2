package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.models.Task;

public class UpdatetTaskHandler extends Handler {

	@Override
	public void process(Object request) {
		Task resource = (Task) request;
		_taskManagementRepo.save(resource);
	}  
}
