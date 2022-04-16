package pmo.project.handlers.abstraction;

import pmo.project.Service.HumanResourceService;
import pmo.project.Service.MaterialResourceService;
import pmo.project.Service.ProjectService;
import pmo.project.Service.TaskService;
import pmo.project.handlers.response.HandlerResponse;

//Each handler for each type of request
public abstract class Handler {
	protected HumanResourceService _humanResourceService;
	protected MaterialResourceService _materialResourceService;
	protected ProjectService _projectManagementservice;
	protected TaskService _taskManagementService;
	
	public abstract HandlerResponse process(Object request);
}
