package pmo.project.models;

import java.util.Date;

public class Slot {
	private Date _startDate;
	private Date _endDate;
	
	public Slot(Date startDate, Date endDate) {
		this._startDate = startDate;
		this._endDate = endDate;
	}
	
	public boolean isSlotWithinRange(Date startDate, Date endDate) {
		if(startDate.after(this._startDate) && startDate.before(this._endDate))
			return true;
		
		if(endDate.after(this._startDate) && endDate.before(this._endDate))
			return true;
		
		return false;
	}
}
