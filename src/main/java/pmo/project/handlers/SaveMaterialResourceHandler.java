package pmo.project.handlers;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.resource.models.abstraction.MaterialResource;

public class SaveMaterialResourceHandler extends Handler {

	@Override
	@Async("ResourceExecutor")
	public HandlerResponse process(Object request) {
		MaterialResource resource = (MaterialResource) request;
		_materialResourceRepo.save(resource);
		
		return null;
	} 
}
