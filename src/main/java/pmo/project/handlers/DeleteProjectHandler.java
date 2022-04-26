package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;

public class DeleteProjectHandler extends Handler {

	@Override
	public HandlerResponse<Boolean> process(Object request) {
		Long id = (Long) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(id);
		
		response.setObject(true);
		
		return response;
	}
	
	private void execute(Long id) {
		this._projectManagementservice.deleteProject(id);
	}
}