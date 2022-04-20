package pmo.project.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

import pmo.project.handlers.*;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;

@RestController("/pmo/task/")
public class TaskController {
	@Autowired
	CreateTaskHandler _createTaskHandler;
	
	@PostMapping("task")
	public HandlerResponse<Boolean> createTask(TaskCreationRequest request) {
		return _createTaskHandler.process(request);
	}
}
