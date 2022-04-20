package pmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.*;
import pmo.project.handlers.query.QueryProjectFeasibilityHandler;
import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.response.HandlerResponse;

@RestController("/pmo/project/")
public class ProjectController {
	
	@Autowired
	CreateProjectHandler _createProjectHandler;

	@Autowired
	QueryProjectFeasibilityHandler _queryProjectFeasibilityHandler;
	
	@PostMapping
	public HandlerResponse<Boolean> createProject(ProjectCreationRequest request) {
		return _createProjectHandler.process(request);
	}
	
	@PostMapping("{id}/feasibility")
	public HandlerResponse<String> checkProjectFeasibility(ProjectExecutionRequest request, @PathVariable(required = false) String id) {
		return this._queryProjectFeasibilityHandler.process(request);
	}
}
