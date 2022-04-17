package pmo.project.Service;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.repo.DocumentRepo;
import pmo.project.repo.TaskManagementRepo;
import pmo.project.repo.TaskRequirementRepo;
import pmo.project.repo.models.Task;
import pmo.project.repo.models.TaskRequirement;
import pmo.project.utilities.Constant;

public class TaskService {
	private TaskManagementRepo _taskManagementRepo;
	private DocumentRepo _documentRepo;
	private TaskRequirementRepo _taskRequirementRepo;
	
	public TaskService(TaskManagementRepo taskManagementRepo, DocumentRepo documentRepo, TaskRequirementRepo taskRequirementRepo){
		this._taskManagementRepo = taskManagementRepo;
		this._documentRepo = documentRepo;
		this._taskRequirementRepo = taskRequirementRepo;
	}
	
	public long createTask(TaskCreationRequest requirements){
		Task task = new Task(requirements.getDescription(), requirements.getTaskName(), requirements.getDaysTaken(), requirements.getProjectId(), requirements.getBlockingTaskId());
		long taskId = this._taskManagementRepo.save(task);
		
		if(requirements != null) {
				Set<Entry<String, Integer>> entries = requirements.getHumanResource().entrySet();
				
				for(Entry<String, Integer> entry : entries) {
					
					TaskRequirement requirement = new TaskRequirement();
					
					requirement.setProjectId(task.getProjectId());
					requirement.setTaskId(taskId);
					requirement.setType(Constant.HUMAN_LABEL);
				    requirement.setSubType(entry.getKey());
				    requirement.setQuantity(entry.getValue());
				    
				    this._taskRequirementRepo.save(requirement);
				}
				
				entries = requirements.getMaterialResource().entrySet();
				for(Entry<String, Integer> entry : entries) {
					
					TaskRequirement requirement = new TaskRequirement();
					
					requirement.setProjectId(task.getProjectId());
					requirement.setType(Constant.MATERIAL_LABEL);
					requirement.setTaskId(taskId);
				    requirement.setSubType(entry.getKey());
				    requirement.setQuantity(entry.getValue());
				    
				    this._taskRequirementRepo.save(requirement);
				}
		}
		
		return taskId;
	}
	
	public Task getTaskById(long id){
		return this._taskManagementRepo.get(id);
	}
	
	//this will be a query in real time
	public Task getTaskByName(String name){
		return this._taskManagementRepo.getAll().values().stream().filter(project->project.getTitle().equals(name)).findFirst().get();
	}
	
	//this will be a query in real time
	public List<Long> getTaskDocuments(long id){
		return this._documentRepo.getAll().values().stream().filter(doc->doc.getReferencId() == id && doc.getReferencType().equals(Constant.TASK_LABEL)).map(doc->doc.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public List<Long> getBlockingTask(long id){
		return this._taskManagementRepo.getAll().values().stream().filter(task->task.getBlockingTaskId() == id).map(task->task.getId()).collect(Collectors.toList());
	}
	
	//this will be a query in real time
	public List<TaskRequirement> getTaskRequirements(long id, String type){
		return this._taskRequirementRepo.getAll().values().stream().filter(requirements->requirements.getTaskId() == id && requirements.getType().equals(type)).collect(Collectors.toList());
	} 
}
