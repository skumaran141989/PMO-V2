package pmo.project.Service;

import java.util.List;
import java.util.stream.Collectors;

import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.repo.DocumentRepository;
import pmo.project.repo.ProjectRepository;
import pmo.project.repo.TaskRepository;
import pmo.project.repo.TaskRequirementRepository;
import pmo.project.repo.models.Project;
import pmo.project.repo.models.TaskRequirement;
import pmo.project.utilities.Constant;

public class ProjectService {
	private ProjectRepository _projectManagementRepo;
	private TaskRepository _taskManagementRepo;
	private DocumentRepository _documentRepo;
	private TaskRequirementRepository _taskRequirementRepo;
	private TaskService _taskService;
	
	public ProjectService(ProjectRepository projectManagementRepo, TaskRepository taskManagementRepo, 
						  DocumentRepository documentRepo, TaskRequirementRepository taskRequirementRepo, 
						  TaskService taskService) {
		
		this._projectManagementRepo = projectManagementRepo;
		this._taskManagementRepo = taskManagementRepo;
		this._documentRepo = documentRepo;
		this._taskRequirementRepo = taskRequirementRepo;
		this._taskService = taskService;
	}
	
	public Project createProject(ProjectCreationRequest projectCreationRequest) {
		Project project = new Project(projectCreationRequest.getDescription(), projectCreationRequest.getName());
		project = this._projectManagementRepo.save(project);
		
		List<TaskCreationRequest> requests = projectCreationRequest.getTaskrequests();
		
		if (requests != null) {
			for	(TaskCreationRequest request : requests) {
				_taskService.createTask(request);
			}
		}
		
		return project;
	}
	
	public Project getProjectById(long id) {
		return this._projectManagementRepo.getById(id);
	}
	
	//this will be a query in real time
	public Project getProjectByName(String name) {
		return this._projectManagementRepo.findAll().stream().filter(project->project.getTitle().equals(name)).findFirst().get();
	}
	
	//this will be a query in real time
	public List<Long> getProjectTasks(long id) {
		return this._taskManagementRepo.findAll().stream().filter(task->task.getProjectId() == id).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public List<Long> getProjectDocuments(long id) {
		return this._documentRepo.findAll().stream().filter(doc->doc.getReferencId() == id && doc.getReferencType().equals(Constant.PROJECT_LABEL)).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public  List<TaskRequirement> getProjectRequirements(long id) {
		return this._taskRequirementRepo.findAll().stream().filter(project->project.getProjectId() == id).collect(Collectors.toList());
	} 
}
