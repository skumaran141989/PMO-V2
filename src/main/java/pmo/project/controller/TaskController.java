package pmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.*;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Task;

@RestController
@RequestMapping("/pmo-task")
public class TaskController {
	@Autowired
	CreateTaskHandler _createTaskHandler;
	@Autowired
	UpdateTaskHandler _updateTaskHandler;
	@Autowired
	DeleteTaskHandler _deleteTaskHandler;
	
	@PostMapping
	public ResponseEntity<HandlerResponse<Boolean>> createTask(@RequestBody TaskCreationRequest request) {
		return ResponseEntity.ok(this._createTaskHandler.process(request));
	}
	
	@PutMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> updateTask(@RequestBody Task request, @PathVariable(required = true, value="id") long id) {
		request.setId(id);
		return ResponseEntity.ok(this._updateTaskHandler.process(request));
	}	
	
	@DeleteMapping("/{id}")
	public ResponseEntity<HandlerResponse<Boolean>> deleteTask(@PathVariable(required = true, value="id") long id) {
		return ResponseEntity.ok(this._deleteTaskHandler.process(id));
	}	
}
