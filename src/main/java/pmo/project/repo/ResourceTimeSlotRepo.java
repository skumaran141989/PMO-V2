package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.models.TimeSlot;

//DB partitioned or indexed on Document ID
public class ResourceTimeSlotRepo {
	  private Map<Long, TimeSlot> _timeSlots;
	  private long _lastId;
	  
	  public ResourceTimeSlotRepo() {
		  this._timeSlots = new HashMap<Long, TimeSlot>();
		  this._lastId = 0;
	  }
	  
	  public void save(TimeSlot slot) {
		  Long id = slot.getId();
			
		  if (id == 0 ) {
			  id = ++this._lastId;
		  }
		  
		  this._timeSlots.put(slot.getId(), slot);
	  }
	  
	  public Map<Long, TimeSlot> getAll() {
		  return this._timeSlots;
	  }
	  
	  public TimeSlot get(long slotId) {
		  return this._timeSlots.get(slotId);
	  }
	  
	  public void delete(long slotId) {
		  this._timeSlots.remove(slotId);
	  }
}
