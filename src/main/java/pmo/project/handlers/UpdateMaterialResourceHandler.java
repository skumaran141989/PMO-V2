
package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.resource.models.MaterialResource;

public class UpdateMaterialResourceHandler extends Handler {

	@Override
	public HandlerResponse<Boolean> process(Object request) {
		MaterialResource resource = (MaterialResource) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(resource);
		
		response.setObject(true);
		
		return response;
	} 
	
	private void execute(MaterialResource resource)	{
		this._materialResourceService.updateMaterialResource(resource, resource.getId());
	}
}
