package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.resource.models.abstraction.HumanResource;

public class SaveHumanResourceHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request) {
		HumanResource resource = (HumanResource) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(resource);
		response.setObject(true);
		
		return response;
	}  
	
	@Async("Level1")
	private void execute(HumanResource resource)
	{
		_humanResourceRepo.save(resource);
	}
}