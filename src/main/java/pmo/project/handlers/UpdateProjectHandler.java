package pmo.project.handlers;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Project;

public class UpdateProjectHandler extends Handler {

	@Override
	public HandlerResponse<Boolean> process(Object request) {
		Project project = (Project) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(project);
		
		response.setObject(true);
		
		return response;
	}
	
	private void execute(Project project) {
		this._projectManagementservice.updateProject(project, project.getId());
	}
}