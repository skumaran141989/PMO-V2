package pmo.project.Service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

import pmo.project.repo.HumanResourceRepo;
import pmo.project.repo.ResourceTimeSlotRepo;
import pmo.project.repo.models.TimeSlot;
import pmo.project.repo.resource.models.HumanResource;

public class HumanResourceService {
	private HumanResourceRepo _humanResourceRepo;
	private ResourceTimeSlotRepo _resourceTimeSlotRepo;
	
	public HumanResourceService(HumanResourceRepo humanResourceRepo, ResourceTimeSlotRepo resourceTimeSlotRepo){
		this._humanResourceRepo = humanResourceRepo;
		this._resourceTimeSlotRepo = resourceTimeSlotRepo;
	}
	
	public long createHumaResource(HumanResource resource){
		return this._humanResourceRepo.save(resource);
	}
	
	public HumanResource getHumanResourceById(long id){
		return this._humanResourceRepo.get(id);
	}
	
	//this will be a query in real time
	public List<HumanResource> getHumanResourcesByType(String type) {
		return this._humanResourceRepo.getAll().values().stream().filter(resource->resource.getType() == type).collect(Collectors.toList());
	}
	
	//this will be a join query in real time
	public List<HumanResource> getAvailableHumanResources(String type, Date startDate, Date endDate) {
		List<HumanResource> humanresources = getHumanResourcesByType(type);
		List<HumanResource> availablehumanresources  = new ArrayList<HumanResource>();
		
		for (HumanResource humanResource : humanresources) {
			
			List<TimeSlot> timeslots = this._resourceTimeSlotRepo.getAll().values().stream().filter(slot->slot.getResourceId() == humanResource.getId()).collect(Collectors.toList());
	        boolean isAvailable = true; 
	        
         	for (TimeSlot slot : timeslots) {
         		
         		isAvailable=!checkSlotWithinRange(slot, startDate, endDate);
         		
         		if (!isAvailable) {
	        	    break;
         		}
	         }
         	
         	if (isAvailable) {
         		
         		availablehumanresources.add(humanResource);
         	}
		}
        
		return availablehumanresources;
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
