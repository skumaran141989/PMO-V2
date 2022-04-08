package pmo.project.handlers.abstraction;

import pmo.project.repo.*;

//Each handler for each type of request
public abstract class Handler {
	protected HumanResourceRepo _humanResourceRepo;
	protected MaterialResourceRepo _materialResourceRepo;
	protected ProjectManagementRepo _projectManagementRepo;
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
    	_taskManagementRepo = _taskManagementRepo;
    }
	
	public abstract void process(Object request);	
}
