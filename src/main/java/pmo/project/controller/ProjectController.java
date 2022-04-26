package pmo.project.controller;

import java.util.Date;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.*;
import pmo.project.handlers.query.QueryProjectFeasibilityHandler;
import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Project;

@RestController
@RequestMapping("/pmo-project")
public class ProjectController {
	@Autowired
	CreateProjectHandler _createProjectHandler;
	@Autowired
	UpdateProjectHandler _updateProjectHandler;
	@Autowired
	DeleteProjectHandler _deleteProjectHandler;
	@Autowired
	QueryProjectFeasibilityHandler _queryProjectFeasibilityHandler;
	
	@PostMapping
	public ResponseEntity<HandlerResponse<Boolean>> createProject(@RequestBody ProjectCreationRequest request) {
		return ResponseEntity.ok(this._createProjectHandler.process(request));
	}
	
	@PostMapping("/{id}/feasibility)")
	public ResponseEntity<HandlerResponse<String>> checkProjectFeasibility(@RequestParam(required = true, value="DueDate") Date dueDate, @RequestParam(required = true, value="StartDate") Date startDate,  @PathVariable(required = true, value="id") Long id) {
		
		ProjectExecutionRequest request = new ProjectExecutionRequest();
		request.setDueDate(dueDate);
		request.setStartDate(startDate);
		request.setProjectId(id);
		
		return ResponseEntity.ok(this._queryProjectFeasibilityHandler.process(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> updateProject(Project request, @PathVariable(required = true, value="id") long id) {
		request.setId(id);
		return ResponseEntity.ok(this._updateProjectHandler.process(request));
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> deleteProject(@PathVariable(required = true, value="id") long id) {
		return ResponseEntity.ok(this._deleteProjectHandler.process(id));
	}	
}
