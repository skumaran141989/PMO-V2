package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.resource.models.abstraction.HumanResource;

public class SaveHumanResourceHandler extends Handler {
	
	@Override
	@Async("ResourceExecutor")
	public HandlerResponse process(Object request) {
		HumanResource resource = (HumanResource) request;
		_humanResourceRepo.save(resource);
		
		return null;
	}  
}
