package pmo.project.resource.models.abstraction;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import pmo.project.models.Slot;
import pmo.project.models.Task;

public abstract class HumanResource {
    private String _firstName;
    private String _lastName;
    private long _id;
    private String _contactNumber;
    private double _salary;
	private List<Task> _tasks;
	private List<Slot> _slots;
	
	public void setFirstName(String firstName) {
		_firstName = firstName;
	}
	
	public String getTaskProgress() {
		return _firstName;
	}
	
	public void setLastName(String lastName) {
		_lastName = lastName;
	}
	
	public String getLastName() {
		return _lastName;
	}
	
	public void setId(long id) {
		_id = id;
	}
	
	public long getId() {
		return _id;
	}
	
	public void setContactNumber(String contactNumber) {
		_contactNumber = contactNumber;
	}
	
	public String getContactNumber() {
		return _contactNumber;
	}
	
	public void setSalary(double salary) {
		_salary = salary;
	}
	
	public double getSalary() {
		return _salary;
	}
	
	public List<Task> getTasks() {
		if (_tasks==null)
			_tasks = new ArrayList<Task>();
		
		return _tasks;
	}
	
	public boolean allocate(Date startDate, Date dueDate) {
		if(_slots==null)
			_slots = new ArrayList<Slot>();
	    for(Slot slot: _slots)
	    	if(!slot.isSlotWithinRange(startDate, dueDate))
	    		_slots.add(new Slot(startDate, dueDate));
	    	else
	    		return false;
	    
	    return true;
	}
}
