package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;

public class CreateTaskHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request) {
		
		TaskCreationRequest taskCreationRequest = (TaskCreationRequest) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(taskCreationRequest);
		response.setObject(true);
		
		return response;
	}   
	
	private void execute(TaskCreationRequest taskCreationRequest) {
		_taskManagementService.createTask(taskCreationRequest);
    }
}
