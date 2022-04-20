package pmo.project.handlers.query;

import java.util.AbstractMap;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Project;
import pmo.project.repo.models.Task;
import pmo.project.repo.models.TaskRequirement;
import pmo.project.repo.models.TimeSlot;
import pmo.project.utilities.Constant;

public class QueryProjectFeasibilityHandler extends Handler {
	
	@Override
	public HandlerResponse<String> process(Object request) {
		HandlerResponse<String> response = new HandlerResponse<String>();
			
		try {
			
			ProjectExecutionRequest projectExecutionRequest = (ProjectExecutionRequest) request;
			StringBuilder output = new StringBuilder();
			execute(projectExecutionRequest, output);
			
			response.setObject(output.toString());
		}
		catch (Exception ex) {
			response.getErrorResponse().add(ex.getMessage());
		}
		
		return response;
	}
	
	private void execute(ProjectExecutionRequest projectExecutionRequest, StringBuilder output) {
		Project project = this._projectManagementservice.getProjectById(projectExecutionRequest.getProjectId());
		List<Long> tasks = this._projectManagementservice.getProjectTasks(project.getId());
		
		Map<Integer, Date> levelStartDates= new HashMap<Integer, Date> ();
		HashSet<Long> processedTasks = new HashSet<Long>();
		Map<String, Integer> allocatedlHumanResources = new HashMap<String, Integer>();
		Map<String, Set<Entry<TimeSlot, Integer>>> allocatedMaterialResources = new HashMap<String, Set<Entry<TimeSlot, Integer>>>();
		
		levelStartDates.put(-1, projectExecutionRequest.getStartDate());
		
		if (tasks != null) {
			for (Long taskId : tasks) {
				if (!processedTasks.contains(taskId)) {
					estimateAllocateTimeResources(taskId, projectExecutionRequest.getDueDate(), 0, levelStartDates, processedTasks, output, allocatedlHumanResources, allocatedMaterialResources);
					if (output.length()	> 0) {
						return;
					}
				}
			}
		}
	}
	
	private void estimateAllocateTimeResources(Long taskId, Date dueDate, int level, Map<Integer, Date> levelStartDates, HashSet<Long> processedTasks, 
			                        		  StringBuilder output,  Map<String, Integer> allocatedMaterialResources, 
			                        		  Map<String, Set<Entry<TimeSlot, Integer>>> allocatedlHumanResources) {
		List<Long> blockingtasks = this._taskManagementService.getBlockingTask(taskId);
		Task task = this._taskManagementService.getTaskById(taskId);
		
		if (blockingtasks != null) {
			for (Long blockingtask : blockingtasks) {
				if (!processedTasks.contains(taskId)) {
					estimateAllocateTimeResources(blockingtask, dueDate, level+1, levelStartDates, processedTasks, output, allocatedMaterialResources, allocatedlHumanResources );
					if (output.length() > 0) {
						return;
					}
				}
			}
		}
		
		Date levelStartDate = levelStartDates.get(level);
		if (levelStartDate == null) {
			levelStartDate = levelStartDates.get(-1);
		}

		Date completionDate = addDate(levelStartDate, task.getDaysTaken());
		Date previousLevelDate = levelStartDates.get(level-1);
		
		if (previousLevelDate == null || completionDate.compareTo(previousLevelDate) >= 0) {
			levelStartDates.put(level-1, addDate(completionDate,1));	
		}
		
		if (completionDate.compareTo(previousLevelDate) > 0) {
			output.append("Task - "+ taskId +" failed due to insufficient time");
			return;
		}
		
		List<TaskRequirement> humanResourcesRequirements = this._taskManagementService.getTaskRequirements(taskId, Constant.HUMAN_LABEL);
		
		if (humanResourcesRequirements != null) {
			for (TaskRequirement requirement: humanResourcesRequirements) {
				int count = this._humanResourceService.getAvailableHumanResources(requirement.getSubType(), levelStartDate, completionDate).size();
				int requiredCount = requirement.getQuantity();
				
				Set<Entry<TimeSlot, Integer>> currentLevelAllocatedlHumanResource = allocatedlHumanResources.get(requirement.getSubType());
			   
				if (currentLevelAllocatedlHumanResource == null) {
			    	currentLevelAllocatedlHumanResource = new HashSet<Entry<TimeSlot, Integer>>();
				}
			    
			    Entry<TimeSlot, Integer> timeSlot = null;
			    
				for (Entry<TimeSlot, Integer> resourceCount: currentLevelAllocatedlHumanResource) {
					if (checkSlotWithinRange(resourceCount.getKey(), levelStartDate, completionDate)) {
					    timeSlot = resourceCount;
					    break;
					}
			    }
				
				if(timeSlot==null) {
					timeSlot = new AbstractMap.SimpleEntry<TimeSlot, Integer>(new TimeSlot(levelStartDate, completionDate, 0, 0), 0);
				}
				else {
					currentLevelAllocatedlHumanResource.remove(timeSlot);
				}
				
				if(count-timeSlot.getValue() < requiredCount) {
					output.append("Task - "+ taskId +" failed due to human resource availabity");
					return;
				}
				else {
					timeSlot.setValue(count+requiredCount);
					currentLevelAllocatedlHumanResource.add(timeSlot);
					allocatedlHumanResources.put(requirement.getSubType(), currentLevelAllocatedlHumanResource);
				}
			}
		}
		
		List<TaskRequirement> materialResourcesRequirements = this._taskManagementService.getTaskRequirements(taskId, Constant.MATERIAL_LABEL);
		
		if(materialResourcesRequirements != null) {
			for (TaskRequirement requirement: materialResourcesRequirements) {
				int count = this._materialResourceService.getAvailableMaterialResources(requirement.getSubType()).size();
				int requiredCount = allocatedMaterialResources.get(requirement.getSubType());
				
				if ((count-requiredCount) < (requirement.getQuantity())) {
					output.append("Task - "+ taskId +" failed due to material availabity");
					return;
				}
				else {
					allocatedMaterialResources.put(requirement.getSubType(), requiredCount+count);
				}
			}
		}
		
		output.append("Task - "+ taskId +" successfully processed");
		processedTasks.add(taskId);
	}
	
	private Date addDate(Date startDate, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate); 
		c.add(Calendar.DATE, days); 
		
		return c.getTime();
	}
	
	private boolean checkSlotWithinRange(TimeSlot slot, Date startDate, Date endDate) {
		if (startDate.after(slot.getStartDate()) && startDate.before(slot.getEndDate())) {
			return true;
		}

		if (endDate.after(slot.getStartDate()) && endDate.before(slot.getEndDate())) {
			return true;
		}
		
		return false;
	}
}
