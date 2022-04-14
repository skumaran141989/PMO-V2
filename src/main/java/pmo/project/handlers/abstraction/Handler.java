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
	
//	public Handler(HumanResourceRepo humanResourceRepo, MaterialResourceRepo materialResourceRepo, ProjectManagementRepo projectManagementRepo, 
//				   TaskManagementRepo taskManagementRepo, DocumentRepo documentRepo, ResourceTimeSlotRepo timeSlotRepo, TaskRequirementRepo taskRequirementRepo) {
//		_humanResourceRepo = humanResourceRepo;
//		_materialResourceRepo = materialResourceRepo;
//		_projectManagementRepo = projectManagementRepo;
//		_taskManagementRepo = taskManagementRepo;
//		_documentRepo = documentRepo;
//		_timeSlotRepo = timeSlotRepo;
//		_taskRequirementRepo = taskRequirementRepo;
//	}
	
	public abstract HandlerResponse process(Object request);
}
