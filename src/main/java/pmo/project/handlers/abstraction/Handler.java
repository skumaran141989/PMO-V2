package pmo.project.handlers.abstraction;

import org.springframework.beans.factory.annotation.Autowired;

import pmo.project.Service.HumanResourceService;
import pmo.project.Service.MaterialResourceService;
import pmo.project.Service.ProjectService;
import pmo.project.Service.TaskService;
import pmo.project.handlers.response.HandlerResponse;

//Each handler for each type of request
public abstract class Handler {
	@Autowired
	protected HumanResourceService _humanResourceService;
	@Autowired
	protected MaterialResourceService _materialResourceService;
	@Autowired
	protected ProjectService _projectManagementservice;
	@Autowired
	protected TaskService _taskManagementService;
	
	public abstract HandlerResponse process(Object request);
}
