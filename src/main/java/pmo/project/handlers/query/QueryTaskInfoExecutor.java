package pmo.project.handlers.query;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Task;

public class QueryTaskInfoExecutor extends Handler {
	
	@Override
	public HandlerResponse<Task> process(Object request) {
		HandlerResponse<Task> response = new HandlerResponse<Task>();
		try {
		
			String TaskId = (String) request;
		
			Task task = _taskManagementRepo.get(TaskId);
			response.setObject(task);
		}
		catch(Exception ex) {
			response.getErrorResponse().add(ex.getMessage());
		}
		
		return response;
	}
}
