package pmo.project.resource.models.abstraction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pmo.project.models.Slot;
import pmo.project.models.Task;
import pmo.project.repo.UserCalendarRepo;

public abstract class HumanResource {
    private String _firstName;
    private String _lastName;
    private long _id;
    private String _contactNumber;
    private double _salary;
	private List<Task> _tasks;
	private List<Slot> _slots;
	private boolean _isUsable;
	private UserCalendarRepo _usercalendar;
	
	public void setFirstName(String firstName) {
		this._firstName = firstName;
	}
	
	public String getTaskProgress() {
		return this._firstName;
	}
	
	public void setLastName(String lastName) {
		this._lastName = lastName;
	}
	
	public String getLastName() {
		return this._lastName;
	}
	
	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
	}
	
	public void setContactNumber(String contactNumber) {
		this._contactNumber = contactNumber;
	}
	
	public String getContactNumber() {
		return this._contactNumber;
	}
	
	public void setSalary(double salary) {
		this._salary = salary;
	}
	
	public double getSalary() {
		return this._salary;
	}
	
	public List<Task> getTasks() {
		if (this._tasks==null)
			this._tasks = new ArrayList<Task>();
		
		return this._tasks;
	}
	
	public void setIsUsable(boolean isUsable) {
		
		if(isUsable==true && !this._tasks.isEmpty())
		for(Task task : this._tasks)
		   task.setReasonForStoppage("Resource "+_id+" unavailable.");
			
		this._isUsable = isUsable;
	}
	
	public boolean getIsUsable() {
		return this._isUsable;
	}
	
	public  List<Slot> getSlots(){
		if(this._slots==null)
			this._slots = new ArrayList<Slot>();
		return this._slots;
	}
	
	public boolean allocate(Date startDate, Date dueDate) {
		if(this._slots==null)
			this._slots = new ArrayList<Slot>();
	    for(Slot slot: this._slots)
	    	if(!slot.isSlotWithinRange(startDate, dueDate))
	    		this._slots.add(new Slot(startDate, dueDate));
	    	else
	    		return false;

	    return true;
	}
	
	public UserCalendarRepo getUserCalendar() {
		return this._usercalendar;
	}
}
