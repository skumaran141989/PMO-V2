package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.resource.models.HumanResource;

public class UpdateHumanResourceHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request)	{
		HumanResource resource = (HumanResource) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(resource);
		
		response.setObject(true);
		
		return response;
	}  
	
	private void execute(HumanResource resource) {
		this._humanResourceService.updateHumaResource(resource, resource.getId());
	}
}
