package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.resource.models.abstraction.HumanResource;

public class SaveHumanResourceHandler extends Handler {
	
	@Override
	public void process(Object request) {
		HumanResource resource = (HumanResource) request;
		_humanResourceRepo.save(resource);
	}  
}
