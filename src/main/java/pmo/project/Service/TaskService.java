package pmo.project.Service;

import java.util.List;
import java.util.Set;
import java.util.Map.Entry;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.repo.DocumentRepository;
import pmo.project.repo.TaskRepository;
import pmo.project.repo.TaskRequirementRepository;
import pmo.project.repo.models.Task;
import pmo.project.repo.models.TaskRequirement;
import pmo.project.utilities.Constant;

@Service
public class TaskService {
	@Autowired
	private TaskRepository _taskManagementRepo;
	@Autowired
	private DocumentRepository _documentRepo;
	@Autowired
	private TaskRequirementRepository _taskRequirementRepo;
	
	@Transactional("readWriteTM")
	public Task createTask(TaskCreationRequest requirements) {
		Task task = new Task(requirements.getDescription(), requirements.getTaskName(), requirements.getDaysTaken(), requirements.getProjectId(), requirements.getBlockingTaskId());
		task = this._taskManagementRepo.save(task);
		
		if (requirements != null) {
				Set<Entry<String, Integer>> entries = requirements.getHumanResource().entrySet();
				
				for(Entry<String, Integer> entry : entries) {
					TaskRequirement requirement = new TaskRequirement();
					
					requirement.setProjectId(task.getProjectId());
					requirement.setTaskId(task.getId());
					requirement.setType(Constant.HUMAN_LABEL);
				    requirement.setSubType(entry.getKey());
				    requirement.setQuantity(entry.getValue());
				    
				    this._taskRequirementRepo.save(requirement);
				}
				
				entries = requirements.getMaterialResource().entrySet();
				for (Entry<String, Integer> entry : entries) {
					TaskRequirement requirement = new TaskRequirement();
					
					requirement.setProjectId(task.getProjectId());
					requirement.setType(Constant.MATERIAL_LABEL);
					requirement.setTaskId(task.getId());
				    requirement.setSubType(entry.getKey());
				    requirement.setQuantity(entry.getValue());
				    
				    this._taskRequirementRepo.save(requirement);
				}
		}
		
		return task;
	}
	
	@Transactional("readWriteTM")
	public Task updateTask(Task task, long id) {
		task.setId(id);
		return this._taskManagementRepo.save(task);
	}
	
	@Transactional("readWriteTM")
	public void deleteTask(long id) {
		this._taskManagementRepo.deleteById(id);
	}
	
	@Transactional("readOnlyTM")
	public Task getTaskById(long id){
		return this._taskManagementRepo.getById(id);
	}
	
	@Transactional("readOnlyTM")
	public Task getTaskByName(String name) {
		return this._taskManagementRepo.findAll().stream().filter(project->project.getTitle().equals(name)).findFirst().get();
	}
	
	@Transactional("readOnlyTM")
	public List<Long> getTaskDocuments(long id) {
		return this._documentRepo.findAll().stream().filter(doc->doc.getReferencId() == id && doc.getReferencType().equals(Constant.TASK_LABEL)).map(doc->doc.getId()).collect(Collectors.toList());
	}
	
	@Transactional("readOnlyTM")
	public List<Long> getBlockingTask(long id) {
		return this._taskManagementRepo.findAll().stream().filter(task->task.getBlockingTaskId() == id).map(task->task.getId()).collect(Collectors.toList());
	}
	
	@Transactional("readOnlyTM")
	public List<TaskRequirement> getTaskRequirements(long id, String type) {
		return this._taskRequirementRepo.findAll().stream().filter(requirements->requirements.getTaskId() == id && requirements.getType().equals(type)).collect(Collectors.toList());
	} 
}
