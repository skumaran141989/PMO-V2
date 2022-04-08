package pmo.project.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectCreationRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.models.Project;
import pmo.project.models.Task;
import pmo.project.models.TaskDependency;
import pmo.project.resource.models.abstraction.HumanResource;
import pmo.project.resource.models.abstraction.MaterialResource;

public class CreateTaskHandler extends Handler {
	
	@Override
	public void process(Object request) {
		TaskCreationRequest taskCreationRequest = (TaskCreationRequest) request;
		
		Project project = _projectManagementRepo.getProject(taskCreationRequest.getProjectName());
		
	}    
	
	
	//Distributed Approach
	private HandlerResponse allocateResources(Project project, TaskCreationRequest taskRequest, Date startDate, Date dueDate, long remainingHours, Task parentTask, int weight) {
		HandlerResponse handlerResponse = new HandlerResponse();
		
		Map<TaskCreationRequest, Integer> dependentTasks = taskRequest.getTaskrequests();
		
		if(remainingHours<=0)
			handlerResponse.getErrorResponse().add("Project failed due to insufficientTime for Task - "+taskRequest.getName());
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				handlerResponse.getErrorResponse().addAll(allocateResources(project, childtask.getKey(), startDate, dueDate, remainingHours - childtask.getKey().getTimeTaken(), parentTask, childtask.getValue()).getErrorResponse());
			}
		}
		
	//resources are allocated for leaf task is allocated first
		
		
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
		}
		
		List<HumanResource> allocatedHumanResources = new ArrayList<HumanResource>();
		
		Map<String, Integer> humanResource = taskRequest.getHumanResource();
		Set<String> humanResourceKeyset = humanResource.keySet();
		
		for(String humanResourcetype:humanResourceKeyset) {
			int quantity = humanResource.get(humanResourcetype);
			List<HumanResource> resources = _humanResourceRepo.get(humanResourcetype);
			for(HumanResource resource:resources) {
				if(allocatedHumanResources.size()==quantity)
					break;
				
				if(resource.allocate(startDate, dueDate))
					allocatedHumanResources.add(resource);
			}
		}
		
		return handlerResponse;
	}
}
