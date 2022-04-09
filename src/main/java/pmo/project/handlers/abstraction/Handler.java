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
	
    public void setHumanResourceRepo(HumanResourceRepo humanResourceRepo) {
    	_humanResourceRepo = humanResourceRepo;
    }
    
    public void setMaterialResourceRepo(MaterialResourceRepo materialResourceRepo) {
    	_materialResourceRepo = materialResourceRepo;
    }
    
    public void setProjectManagementRepo(ProjectManagementRepo projectManagementRepo) {
    	_projectManagementRepo = projectManagementRepo;
    }
    
    public void setProjectManagementRepo(TaskManagementRepo taskManagementRepo) {
    	_taskManagementRepo = taskManagementRepo;
    }
	
	public abstract HandlerResponse process(Object request);
}
