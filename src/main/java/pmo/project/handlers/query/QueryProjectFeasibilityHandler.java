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
	public HandlerResponse<StringBuilder> process(Object request) {
		
		HandlerResponse<StringBuilder> response = new HandlerResponse<StringBuilder>();
		
		ProjectExecutionRequest projectExecutionRequest = (ProjectExecutionRequest) request;
		StringBuilder output = new StringBuilder();
		execute(projectExecutionRequest, output);
		
		response.setObject(output);
		
		return response;
	}   
	
	private boolean execute(ProjectExecutionRequest projectExecutionRequest, StringBuilder output)
	{
		Project project = this._projectManagementservice.getProjectById(projectExecutionRequest.getProjectId());
		List<Long> tasks = this._projectManagementservice.getProjectTasks(project.getId());
		
		//Temporary tables
		Map<Integer, Date> levelStartDates= new HashMap<Integer, Date> ();
		HashSet<Long> processedTasks = new HashSet<Long>();
		Map<Integer,  Map<String, Integer>> levelAllocatedlHumanResources = new HashMap<Integer, Map<String, Integer>>();
		Map<Integer, Map<String, Set<Entry<TimeSlot, Integer>>>> levelAllocatedMaterialResources = new HashMap<Integer, Map<String, Set<Entry<TimeSlot, Integer>>>>();
		
		levelStartDates.put(-1, projectExecutionRequest.getStartDate());
		if(tasks!=null)
		for(Long taskId : tasks) {
			if(!processedTasks.contains(taskId))
				if(!estimateAllocateResources(taskId, projectExecutionRequest.getDueDate(), 0, levelStartDates, processedTasks, output, levelAllocatedlHumanResources, levelAllocatedMaterialResources))
					return false;
		}
		
		return true;
	}
	
	//linear approach for task creation
	private boolean estimateAllocateResources(Long taskId, Date dueDate, int level, Map<Integer, Date> levelStartDates, HashSet<Long> processedTasks, 
			                        		  StringBuilder output, Map<Integer, Map<String, Integer>> levelAllocatedMaterialResources, Map<Integer, Map<String, Set<Entry<TimeSlot, Integer>>>> levelAllocatedlHumanResources) {
		List<Long> blockingtasks = this._taskManagementService.getBlockingTask(taskId);
		Task task = this._taskManagementService.getTaskById(taskId);
		
		if(blockingtasks!=null)
		for(Long blockingtask : blockingtasks) {
			if(!processedTasks.contains(taskId))
				if(!estimateAllocateResources(blockingtask, dueDate, level+1, levelStartDates, processedTasks, output, levelAllocatedMaterialResources, levelAllocatedlHumanResources ))
					return false;
		}
		
		Date levelStartDate = levelStartDates.get(level);
		if(levelStartDate==null)
			levelStartDate = levelStartDates.get(-1);
		
		Map<String, Integer> currentLevelAllocatedMaterialResources = levelAllocatedMaterialResources.get(level);
		if(currentLevelAllocatedMaterialResources==null)
			currentLevelAllocatedMaterialResources = new HashMap<String, Integer>();
		
		Map<String, Set<Entry<TimeSlot, Integer>>> currentLevelAllocatedlHumanResources = levelAllocatedlHumanResources.get(level);
		if(currentLevelAllocatedlHumanResources==null)
			currentLevelAllocatedlHumanResources = new HashMap<String,Set<Entry<TimeSlot, Integer>>>();
		
		Date completionDate = addDate(levelStartDate, task.getDaysTaken());
		Date previousLevelDate = levelStartDates.get(level-1);
		if(previousLevelDate==null || completionDate.compareTo(previousLevelDate)>=0)
			levelStartDates.put(level-1, addDate(completionDate,1));	
		
		if(completionDate.compareTo(previousLevelDate)>0) {
			output.append("Task - "+ taskId +" failed due to insufficient time");
		    return false;
		}
		
		List<TaskRequirement> humanResourcesRequirements = this._taskManagementService.getTaskRequirements(taskId, Constant.HUMAN_LABEL);
		List<TaskRequirement> materialResourcesRequirements = this._taskManagementService.getTaskRequirements(taskId, Constant.MATERIAL_LABEL);
		
		if(humanResourcesRequirements!=null)
		for(TaskRequirement requirement: humanResourcesRequirements) {
			int count = this._humanResourceService.getAvailableHumanResources(requirement.getSubType(), levelStartDate, completionDate).size();
			int requiredCount = requirement.getQuantity();
			Set<Entry<TimeSlot, Integer>> currentLevelAllocatedlHumanResource = currentLevelAllocatedlHumanResources.get(requirement.getSubType());
		    if(currentLevelAllocatedlHumanResource == null)
		    	currentLevelAllocatedlHumanResource = new HashSet<Entry<TimeSlot, Integer>>();
		    Entry<TimeSlot, Integer> timeSlot = null;
			for(Entry<TimeSlot, Integer> resourceCount: currentLevelAllocatedlHumanResource){
				if(checkSlotWithinRange(resourceCount.getKey(), levelStartDate, completionDate)) {
				    timeSlot = resourceCount;
				    break;
				}
		    }
			
			if(timeSlot!=null)
				requiredCount-=timeSlot.getValue();
			else
				timeSlot = new AbstractMap.SimpleEntry<TimeSlot, Integer>(new TimeSlot(levelStartDate, completionDate, 0, 0), 0);
				
			if(count<requiredCount){
				output.append("Task - "+ taskId +" failed due to human resource availabity");
				timeSlot.setValue(count+timeSlot.getValue());
				currentLevelAllocatedlHumanResource.remove(timeSlot);
				return false;
			}	
		}
		
		if(materialResourcesRequirements!=null)
		for(TaskRequirement requirement: materialResourcesRequirements) {
			int count = this._materialResourceService.getAvailableMaterialResources(requirement.getSubType()).size();
			int resourcecount = currentLevelAllocatedMaterialResources.get(requirement.getSubType());
			if(count-resourcecount<(requirement.getQuantity())) {
				output.append("Task - "+ taskId +" failed due to material availabity");
				return false;
			}
			else
				currentLevelAllocatedMaterialResources.put(requirement.getSubType(), resourcecount+count);
		}
		
		levelAllocatedMaterialResources.put(level, currentLevelAllocatedMaterialResources);
		output.append("Task - "+ taskId +" successfully processed");
		processedTasks.add(taskId);
		
		return true;
	}
	
	private Date addDate(Date startDate, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate); 
		c.add(Calendar.DATE, days); 
		return c.getTime();
	}
	
	private boolean checkSlotWithinRange(TimeSlot slot, Date startDate, Date endDate) {
		if(startDate.after(slot.getStartDate()) && startDate.before(slot.getEndDate()))
			return true;
		
		if(endDate.after(slot.getStartDate()) && endDate.before(slot.getEndDate()))
			return true;
		
		return false;
	}
}
