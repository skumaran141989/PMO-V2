package pmo.project.Service;

import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.repo.DocumentRepository;
import pmo.project.repo.ProjectRepository;
import pmo.project.repo.TaskExecutionRepository;
import pmo.project.repo.TaskRepository;
import pmo.project.repo.TaskRequirementRepository;
import pmo.project.repo.models.Project;
import pmo.project.repo.models.TaskExecution;
import pmo.project.repo.models.TaskRequirement;
import pmo.project.utilities.Constant;

@Service
public class ProjectService {
	@Autowired
	private ProjectRepository _projectManagementRepo;
	@Autowired
	private TaskRepository _taskManagementRepo;
	@Autowired
	private DocumentRepository _documentRepo;
	@Autowired
	private TaskRequirementRepository _taskRequirementRepo;
	@Autowired
	private TaskExecutionRepository _taskExecutionRepo;
	@Autowired
	private TaskService _taskService;

	
	@Transactional("readWriteTM")
	public Project createProject(ProjectCreationRequest projectCreationRequest) {
		Project project = new Project(projectCreationRequest.getDescription(), projectCreationRequest.getName());
		project = this._projectManagementRepo.save(project);
		
		List<TaskCreationRequest> requests = projectCreationRequest.getTaskrequests();
		
		if (requests != null) {
			for	(TaskCreationRequest request : requests) {
				this._taskService.createTask(request);
			}
		}
		
		return project;
	}
	
	@Transactional("readWriteTM")
	public Project updateProject(Project project, long id) {
		project.setId(id);
		return this._projectManagementRepo.save(project);
	}
	
	@Transactional("readWriteTM")
	public void deleteProject(long id) {
		this._projectManagementRepo.deleteById(id);
	}
	
	@Transactional("readOnlyTM")
	public Project getProjectById(long id) {
		return this._projectManagementRepo.getById(id);
	}
	
	@Transactional("readOnlyTM")
	public Project getProjectByName(String name) {
		return this._projectManagementRepo.findAll().stream().filter(project->project.getTitle().equals(name)).findFirst().get();
	}
	
	@Transactional("readOnlyTM")
	public List<Long> getProjectTasks(long id) {
		return this._taskManagementRepo.findAll().stream().filter(task->task.getProjectId() == id).map(task->task.getId()).collect(Collectors.toList());
	}
	
	@Transactional("readOnlyTM")
	public List<Long> getProjectDocuments(long id) {
		return this._documentRepo.findAll().stream().filter(doc->doc.getReferencId() == id && doc.getReferencType().equals(Constant.PROJECT_LABEL)).map(task->task.getId()).collect(Collectors.toList());
	}
	
	@Transactional("readOnlyTM")
	public  List<TaskRequirement> getProjectRequirements(long id) {
		return this._taskRequirementRepo.findAll().stream().filter(project->project.getProjectId() == id).collect(Collectors.toList());
	} 
	
	@Transactional("readOnlyTM")
	public  List<TaskExecution> getProjectFeasibilityReport(long id) {
		return this._taskExecutionRepo.findAll().stream().filter(project->project.getId() == id).collect(Collectors.toList());
	}
}
