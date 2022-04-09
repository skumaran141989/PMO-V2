package pmo.project.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.handlers.response.ProjectFeasibilityResponse;
import pmo.project.models.Project;
import pmo.project.models.Task;
import pmo.project.resource.models.abstraction.HumanResource;
import pmo.project.resource.models.abstraction.MaterialResource;

public class QueryProjectFeasibility extends Handler {
	
	@Override
	@Async("ProjectExecutor")
	public HandlerResponse process(Object request) {
		ProjectFeasibilityResponse projectFeasibilityResponse = new ProjectFeasibilityResponse();
		
		ProjectExecutionRequest projectExecutionRequest = (ProjectExecutionRequest) request;
		Project project = _projectManagementRepo.get(projectExecutionRequest.getProjectName());
		project.setDueDate(projectExecutionRequest.getDueDate());
		project.setStartDate(projectExecutionRequest.getStartDate());
		
		Map<TaskCreationRequest, Integer> dependentTasks = project.getRequirements();
		
		long remainingHours=(project.getDueDate().getTime()-project.getStartDate().getTime())/(60*60 * 1000);
		if(remainingHours<=0)
			projectFeasibilityResponse.getFailureResons().put(projectExecutionRequest.getProjectName(), "Project failed due to insufficientTime for Project");
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				allocateResources(project, childtask.getKey(), remainingHours - childtask.getKey().getTimeTaken(), null, childtask.getValue(), projectFeasibilityResponse);
			}
		}
		
		return projectFeasibilityResponse;
	}    
	
	//linear approach for task creation
	private void allocateResources(Project project, TaskCreationRequest taskRequest, long remainingHours, Task parentTask, int weight, ProjectFeasibilityResponse handlerResponse) {
		Date startDate = project.getStartDate();
		Date dueDate = project.getDueDate();
		
		Task task = new Task(taskRequest.getDescription(), taskRequest.getName(), parentTask.getTimeTaken(), project, parentTask, weight);
		
		Map<TaskCreationRequest, Integer> dependentTasks = taskRequest.getTaskrequests();
		
		if(remainingHours<=0)
			handlerResponse.getFailureResons().put(task.getTitle(), "Project failed due to insufficient Time for Task - "+taskRequest.getName());
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				allocateResources(project, childtask.getKey(), remainingHours - childtask.getKey().getTimeTaken(), task, childtask.getValue(), handlerResponse);
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
				}
			}
			
			if(allocatedMaterialResources.size()<quantity)
				handlerResponse.getFailureResons().put(task.getTitle(), "Insufficient material resource of type "+ type +" for Task - "+taskRequest.getName());
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
				
				if(resource.allocate(startDate, dueDate))
					allocatedHumanResources.add(resource);
			}
			
			if(allocatedMaterialResources.size()<quantity)
				handlerResponse.getFailureResons().put(task.getTitle(), "Insufficient human resource of type "+ type +" for Task - "+taskRequest.getName());
		}
	}
}
