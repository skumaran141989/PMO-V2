package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;

public class DeleteHumanResourceHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request)	{
		Long Id = (Long) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(Id);
		
		response.setObject(true);
		
		return response;
	}  
	
	private void execute(Long id) {
		this._humanResourceService.deleteHumaResource(id);
	}
}
