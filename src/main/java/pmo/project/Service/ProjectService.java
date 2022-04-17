package pmo.project.Service;

import java.util.List;
import java.util.stream.Collectors;

import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.repo.DocumentRepo;
import pmo.project.repo.ProjectManagementRepo;
import pmo.project.repo.TaskManagementRepo;
import pmo.project.repo.TaskRequirementRepo;
import pmo.project.repo.models.Project;
import pmo.project.utilities.Constant;

public class ProjectService {
	private ProjectManagementRepo _projectManagementRepo;
	private TaskManagementRepo _taskManagementRepo;
	private DocumentRepo _documentRepo;
	private TaskRequirementRepo _taskRequirementRepo;
	private TaskService _taskService;
	
	public ProjectService(ProjectManagementRepo projectManagementRepo, TaskManagementRepo taskManagementRepo, 
						 DocumentRepo documentRepo, TaskRequirementRepo taskRequirementRepo, TaskService taskService){
		this._projectManagementRepo = projectManagementRepo;
		this._taskManagementRepo = taskManagementRepo;
		this._documentRepo = documentRepo;
		this._taskRequirementRepo = taskRequirementRepo;
		this._taskService = taskService;
	}
	
	public long createProject(ProjectCreationRequest projectCreationRequest){
		Project project = new Project(projectCreationRequest.getDescription(), projectCreationRequest.getName());
		long projectId = this._projectManagementRepo.save(project);
		List<TaskCreationRequest> requests = projectCreationRequest.getTaskrequests();
		
		if (requests != null) {
			
			for	(TaskCreationRequest request:requests) {
				
				_taskService.createTask(request);
			}
		}
		
		return projectId;
	}
	
	public Project getProjectById(long id) {
		return this._projectManagementRepo.get(id);
	}
	
	//this will be a query in real time
	public Project getProjectByName(String name){
		return this._projectManagementRepo.getAll().values().stream().filter(project->project.getTitle().equals(name)).findFirst().get();
	}
	
	//this will be a query in real time
	public List<Long> getProjectTasks(long id){
		return this._taskManagementRepo.getAll().values().stream().filter(task->task.getProjectId() == id).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public List<Long> getProjectDocuments(long id){
		return this._documentRepo.getAll().values().stream().filter(doc->doc.getReferencId() == id && doc.getReferencType().equals(Constant.PROJECT_LABEL)).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public  List<Long> getProjectRequirements(long id){
		return this._taskRequirementRepo.getAll().values().stream().filter(project->project.getProjectId() == id).map(task->task.getId()).collect(Collectors.toList());
	} 
}
