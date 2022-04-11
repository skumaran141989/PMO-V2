package pmo.project.handlers.abstraction;

import org.springframework.beans.factory.annotation.Autowired;

import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.*;

//Each handler for each type of request
public abstract class Handler {
	@Autowired
	protected HumanResourceRepo _humanResourceRepo;
	@Autowired
	protected MaterialResourceRepo _materialResourceRepo;
	@Autowired
	protected ProjectManagementRepo _projectManagementRepo;
	@Autowired
	protected TaskManagementRepo _taskManagementRepo;
	@Autowired
	protected DocumentInfoRepo _documentRepo;
	
	public abstract HandlerResponse process(Object request);
}
