package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.models.Project;

public class UpdateProjectHandler extends Handler {

	@Override
	public void process(Object request) {
		Project resource = (Project) request;
		_projectManagementRepo.save(resource);
	}  

}
