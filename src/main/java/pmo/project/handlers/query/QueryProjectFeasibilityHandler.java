package pmo.project.handlers.query;

import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import pmo.project.handlers.abstraction.Handler;
import pmo.project.handlers.request.ProjectExecutionRequest;
import pmo.project.handlers.response.HandlerResponse;
import pmo.project.repo.models.Project;
import pmo.project.repo.models.Task;
import pmo.project.repo.models.TaskRequirement;

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
		Project project = _projectManagementservice.getProjectById(projectExecutionRequest.getProjectId());
		
		List<Long> tasks = _projectManagementservice.getProjectTasks(project.getId());
		Map<Integer, Date> levelStartDates= new HashMap<Integer, Date> ();
		levelStartDates.put(-1, projectExecutionRequest.getStartDate());
		HashSet<Long> processTasks = new HashSet<Long>();
		if(tasks!=null)
		for(Long taskId : tasks) {
			if(!estimateAllocateResources(taskId, projectExecutionRequest.getDueDate(), 0, levelStartDates, processTasks, output))
				return false;
		}
		
		return true;
	}
	
	//linear approach for task creation
	private boolean estimateAllocateResources(Long taskId, Date dueDate, int blockingtasksremaining, Map<Integer, Date> levelStartDates, HashSet<Long> processTasks, StringBuilder output) {
		List<Long> blockingtasks = _taskManagementService.getBlockingTask(taskId);
		Task task = _taskManagementService.getTaskById(taskId);
		
		if(blockingtasks!=null)
		for(Long blockingtask : blockingtasks) {
			if(!estimateAllocateResources(blockingtask, dueDate, blockingtasksremaining+1, levelStartDates, processTasks, output))
				return false;
		}
		
		Date levelDate = levelStartDates.get(blockingtasksremaining);
		if(levelDate==null)
			levelDate = levelStartDates.get(-1);
		
		Date completionDate = addDate(levelDate, task.getDaysTaken());
		Date previousLevelDate = levelStartDates.get(blockingtasksremaining-1);
		if(previousLevelDate==null || completionDate.compareTo(previousLevelDate)>=0)
			levelStartDates.put(blockingtasksremaining-1, addDate(completionDate,1));	
		
		if(completionDate.compareTo(previousLevelDate)>0)
		{
			output.append("Task - "+ taskId +" failed due to insufficient time");
		    return false;
		}
		
		List<TaskRequirement> humanResourcesRequirements = _taskManagementService.getTaskRequirements(taskId, "Human");
		List<TaskRequirement> materialResourcesRequirements = _taskManagementService.getTaskRequirements(taskId, "Material");
		
		if(humanResourcesRequirements!=null)
		for(TaskRequirement requirement: humanResourcesRequirements) {
			int count = _humanResourceService.getAvailableHumanResources(requirement.getSubType(), levelDate, completionDate).size();
			if(count<requirement.getQuantity()){
				output.append("Task - "+ taskId +" failed due to human resource availabity");
				return false;
			}	
		}
		
		if(materialResourcesRequirements!=null)
		for(TaskRequirement requirement: materialResourcesRequirements) {
			int count = _materialResourceService.getAvailableMaterialResources(requirement.getSubType()).size();
			if(count<requirement.getQuantity()) {
				output.append("Task - "+ taskId +" failed due to material availabity");
				return false;
			}
		}
		
		output.append("Task - "+ taskId +" successfully processed");
		processTasks.add(taskId);
		
		return true;
	}
	
	
	private Date addDate(Date startDate, int days) {
		Calendar c = Calendar.getInstance();
		c.setTime(startDate); 
		c.add(Calendar.DATE, days); 
		return c.getTime();
	}
}
