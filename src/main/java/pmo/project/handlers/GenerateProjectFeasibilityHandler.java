package pmo.project.handlers;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.UUID;
import java.util.Map.Entry;

import org.springframework.scheduling.annotation.Async;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.request.TaskCreationRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.handlers.response.ProjectFeasibilityReport;
import pmo.project.models.Project;
import pmo.project.resource.models.abstraction.HumanResource;
import pmo.project.resource.models.abstraction.MaterialResource;

public class GenerateProjectFeasibilityHandler extends Handler {
	
	@Override
	public HandlerResponse<String> process(Object request) {
		
		HandlerResponse<String> response = new HandlerResponse<String>();
		
		ProjectExecutionRequest projectExecutionRequest = (ProjectExecutionRequest) request;
		String Id=projectExecutionRequest.getProjectName()+"-"+UUID.randomUUID().toString();
		response.setObject(Id);
		
		return response;
	}   
	
	@Async("Level2")
	private void execute(String Id, ProjectExecutionRequest projectExecutionRequest)
	{
		ProjectFeasibilityReport projectFeasibilityReport = new ProjectFeasibilityReport();
		projectFeasibilityReport.setReportId(Id);
		projectFeasibilityReport.setProjectName(projectExecutionRequest.getProjectName());
		
		Project project = _projectManagementRepo.get(projectExecutionRequest.getProjectName());
		project.setDueDate(projectExecutionRequest.getDueDate());
		project.setStartDate(projectExecutionRequest.getStartDate());
		
		Map<TaskCreationRequest, Integer> dependentTasks = project.getRequirements();
		
		long remainingHours=(project.getDueDate().getTime()-project.getStartDate().getTime())/(60*60 * 1000);
		if(remainingHours<=0)
			projectFeasibilityReport.getFailureResons().put(projectExecutionRequest.getProjectName(), "Project failed due to insufficient time for Project");
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				estimateAllocateResources(project, childtask.getKey(), remainingHours - childtask.getKey().getTimeTaken(), projectFeasibilityReport);
			}
		}
		
		_reportRepo.save(projectFeasibilityReport);
	}
	
	
	//linear approach for task creation
	private void estimateAllocateResources(Project project, TaskCreationRequest taskRequest, long remainingHours, ProjectFeasibilityReport handlerResponse) {
		Date startDate = project.getStartDate();
		Date dueDate = project.getDueDate();
		
		Map<TaskCreationRequest, Integer> dependentTasks = taskRequest.getTaskrequests();
		
		if(remainingHours<=0)
			handlerResponse.getFailureResons().put(taskRequest.getName(), "Project failed due to insufficient Time for Task");
		else if (dependentTasks != null) {
			for(Entry<TaskCreationRequest, Integer> childtask : dependentTasks.entrySet()) {
				estimateAllocateResources(project, childtask.getKey(), remainingHours - childtask.getKey().getTimeTaken(), handlerResponse);
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
				handlerResponse.getFailureResons().put(taskRequest.getName(), "Insufficient material resource of type "+ type);
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
				handlerResponse.getFailureResons().put(taskRequest.getName(), "Insufficient human resource of type "+ type);
		}
	}
}
