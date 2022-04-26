package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Task;

public class UpdateTaskHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request) {
		Task task = (Task) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(task);
		
		response.setObject(true);
		
		return response;
	}   
	
	private void execute(Task task) {
		this._taskManagementService.updateTask(task, task.getId());
    }
}
