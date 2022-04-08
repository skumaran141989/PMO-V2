package pmo.project.models;

import java.util.Date;

public class Slot {
	private Date _startDate;
	private Date _endDate;
	
	public Slot(Date startDate, Date endDate) {
		_startDate = startDate;
		_endDate = endDate;
	}
	
	public boolean isSlotWithinRange(Date startDate, Date endDate) {
		if(startDate.after(_startDate) && startDate.before(_endDate))
			return true;
		
		if(endDate.after(_startDate) && endDate.before(_endDate))
			return true;
		
		return false;
	}
}
