package pmo.project.handlers.query;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Task;

public class QueryTaskInfoExecutor extends Handler {
	
	@Override
	public HandlerResponse<Task> process(Object request) {
		HandlerResponse<Task> response = new HandlerResponse<Task>();
		
		try {
			
			String TaskId = (String) request;
		
			Task task = this._taskManagementService.getTaskByName(TaskId);
			response.setObject(task);
		}
		catch(Exception ex) {
			response.getErrorResponse().add(ex.getMessage());
		}
		
		return response;
	}
}
