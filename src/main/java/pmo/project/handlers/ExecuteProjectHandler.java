package pmo.project.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.springframework.scheduling.annotation.Async;

import pmo.project.enums.Status;
import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Project;
import pmo.project.models.Task;
import pmo.project.models.TaskDependency;
import pmo.project.resource.models.abstraction.HumanResource;
import pmo.project.resource.models.abstraction.MaterialResource;

public class ExecuteProjectHandler extends Handler {
	
	@Override
	@Async("ProjectExecutor")
	public HandlerResponse process(Object request) {
		ProjectExecutionRequest projectExecutionRequest = (ProjectExecutionRequest) request;
		Project project = _projectManagementRepo.get(projectExecutionRequest.getProjectName());
		project.setDueDate(projectExecutionRequest.getDueDate());
		project.setStartDate(projectExecutionRequest.getStartDate());
		
		Map<TaskCreationRequest, Integer> dependentTasks = project.getRequirements();
		
		long remainingHours=(project.getDueDate().getTime()-project.getStartDate().getTime())/(60*60 * 1000);
		if(remainingHours<=0)
			project.setReasonForStoppage("Project failed due to insufficientTime for Task - "+project.getTitle());
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				allocateResources(project, childtask.getKey(), remainingHours - childtask.getKey().getTimeTaken(), null, childtask.getValue());
			}
		}
		
		 return null;
	}    
	
	//linear approach for task creation
	private void allocateResources(Project project, TaskCreationRequest taskRequest, long remainingHours, Task parentTask, int weight) {
		Date startDate = project.getStartDate();
		Date dueDate = project.getDueDate();
		
		Task task = new Task(taskRequest.getDescription(), taskRequest.getName(), parentTask.getTimeTaken(), project, parentTask, weight);
		_taskManagementRepo.save(task);
		
		TaskDependency taskdependency = new TaskDependency(task, weight);
		project.setTaskDependecies(taskdependency);
		_projectManagementRepo.save(project);
		
		Map<TaskCreationRequest, Integer> dependentTasks = taskRequest.getTaskrequests();
		
		if(remainingHours<=0)
			task.setReasonForStoppage("Project failed due to insufficient Time for Task - "+taskRequest.getName());
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				allocateResources(project, childtask.getKey(), remainingHours - childtask.getKey().getTimeTaken(), task, childtask.getValue() );
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
				
				if(resource.allocate(startDate, dueDate)) {
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
