package pmo.project.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Async;

import java.util.Set;

import pmo.project.enums.Status;
import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Project;
import pmo.project.models.Task;
import pmo.project.models.TaskDependency;
import pmo.project.resource.models.abstraction.HumanResource;
import pmo.project.resource.models.abstraction.MaterialResource;

public class CreateTaskHandler extends Handler {
	
	@Override
	public HandlerResponse<Boolean> process(Object request) {
		
		TaskCreationRequest taskCreationRequest = (TaskCreationRequest) request;
		HandlerResponse<Boolean> response = new HandlerResponse<Boolean>();
		
		execute(taskCreationRequest);
		response.setObject(true);
		
		return response;
	}   
	
	@Async("Level3")
	private void execute(TaskCreationRequest taskCreationRequest)
	{
		Project project = _projectManagementRepo.get(taskCreationRequest.getProjectName());
		Task parent = _taskManagementRepo.get(taskCreationRequest.getTaskName());

		Task task = new Task(taskCreationRequest.getDescription(), taskCreationRequest.getName(), taskCreationRequest.getTimeTaken(), project, parent, taskCreationRequest.getWeightToParent());
		_taskManagementRepo.save(task);
		
		long remainingHours = taskCreationRequest.getRemainingHours();
		if(remainingHours<=0)
			task.setReasonForStoppage("Project failed due to insufficient Time for Task - "+task.getTitle());
		
		TaskDependency taskdependency = new TaskDependency(task, taskCreationRequest.getWeightToParent());
		project.setTaskDependecies(taskdependency);
		_projectManagementRepo.save(project);
		
		allocateResources(project, taskCreationRequest, task);
	}
	
	//Distributed approach
	private void allocateResources(Project project, TaskCreationRequest taskRequest, Task task) {
		Date startDate = project.getStartDate();
		Date dueDate = project.getDueDate();
		
		Map<TaskCreationRequest, Integer> dependentTasks = taskRequest.getTaskrequests();

		if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				CreateTaskHandler createTaskHandler = new CreateTaskHandler();
				childtask.getKey().setRemainingHours(taskRequest.getRemainingHours()- childtask.getKey().getTimeTaken());
				createTaskHandler.process(childtask.getKey());
			}
		}
		
		List<MaterialResource> allocatedMaterialResources = new ArrayList<MaterialResource>();
		
		Map<String, Integer> materialResource = taskRequest.getMaterialResource();
		Set<String> materialResourceKeyset = materialResource.keySet();
		
		for(String type:materialResourceKeyset) {
			int quantity = materialResource.get(type);
			List<MaterialResource> resources = _materialResourceRepo.get(type);
			for(MaterialResource resource:resources) {
				
				if(allocatedMaterialResources.size()==quantity)
					break;
				
				if(resource.consume()) {
					allocatedMaterialResources.add(resource);
					_materialResourceRepo.save(resource);
				}
			}
			
			if(allocatedMaterialResources.size()<quantity)
				task.setReasonForStoppage("Insufficient material resource of type "+ type +" for Task - "+taskRequest.getName());
		}
		
		List<HumanResource> allocatedHumanResources = new ArrayList<HumanResource>();
		
		Map<String, Integer> humanResource = taskRequest.getHumanResource();
		Set<String> humanResourceKeyset = humanResource.keySet();
		
		for(String type:humanResourceKeyset) {
			int quantity = humanResource.get(type);
			List<HumanResource> resources = _humanResourceRepo.get(type);
			for(HumanResource resource:resources) {
				if(allocatedHumanResources.size()==quantity)
					break;
				
				if(resource.allocate(startDate, dueDate)){
					allocatedHumanResources.add(resource);
					_humanResourceRepo.save(resource);
				}
			}
			
			if(allocatedMaterialResources.size()<quantity)
				task.setReasonForStoppage("Insufficient human resource of type "+ type +" for Task - "+taskRequest.getName());
		}
		
		task.getHumanResources().addAll(allocatedHumanResources);
		task.getMaterialResources().addAll(allocatedMaterialResources);
		
		if (task.getReasonForStoppage()!=null){
			task.setStatus(Status.PAUSED);
			project.setStatus(Status.PAUSED);
		}
		
		_projectManagementRepo.save(project);
		
		_taskManagementRepo.save(task);
	}
}
