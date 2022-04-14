package pmo.project.Service;

import java.util.List;
import java.util.stream.Collectors;

import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.repo.DocumentRepo;
import pmo.project.repo.ProjectManagementRepo;
import pmo.project.repo.TaskManagementRepo;
import pmo.project.repo.TaskRequirementRepo;
import pmo.project.repo.models.Project;

public class ProjectService {
	private ProjectManagementRepo _projectManagementRepo;
	private TaskManagementRepo _taskManagementRepo;
	private DocumentRepo _documentRepo;
	private TaskRequirementRepo _taskRequirementRepo;
	
	public ProjectService(ProjectManagementRepo projectManagementRepo, TaskManagementRepo taskManagementRepo, 
						 DocumentRepo documentRepo, TaskRequirementRepo taskRequirementRepo){
		this._projectManagementRepo = projectManagementRepo;
		this._taskManagementRepo = taskManagementRepo;
		this._documentRepo = documentRepo;
		this._taskRequirementRepo = taskRequirementRepo;
	}
	
	public long createProject(ProjectCreationRequest projectCreationRequest){
		Project project = new Project(projectCreationRequest.getDescription(), projectCreationRequest.getName());
		long projectId=_projectManagementRepo.save(project);
		
		return projectId;
	}
	
	public Project getProjectById(long id) {
		return _projectManagementRepo.get(id);
	}
	
	//this will be a query in real time
	public Project getProjectByName(String name){
		return _projectManagementRepo.getAll().values().stream().filter(project->project.getTitle().equals(name)).findFirst().get();
	}
	
	//this will be a query in real time
	public List<Long> getProjectTasks(long id){
		return _taskManagementRepo.getAll().values().stream().filter(task->task.getProjectId()==id).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public List<Long> getProjectDocuments(long id){
		return _documentRepo.getAll().values().stream().filter(doc->doc.getReferencId()==id && doc.getReferencType().equals("Project")).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public  List<Long> getProjectRequirements(long id){
		return _taskRequirementRepo.getAll().values().stream().filter(project->project.getProjectId()==id).map(task->task.getId()).collect(Collectors.toList());
	} 
}
