package pmo.project.repo;

import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.UserCalendarRepo.Month.Day;
import pmo.project.repo.UserCalendarRepo.Month.Day.Hour;

//Multilevel partitioned Resource Repo
public class UserCalendarRepo {
	private Map<Integer, Month> _years;
	
	public UserCalendarRepo(){
		this._years = new  HashMap<Integer, Month>();
	}
	
	public Month getMonths(int year) {
		Month months = this._years.get(year);
		
		return months==null?new Month():months;
	}
	
	public void add(Date date, String type, String resource) {
		Calendar calendar = new GregorianCalendar();
		calendar.setTime(date);
		int year = calendar.get(Calendar.YEAR)+1900;
		int month = calendar.get(Calendar.MONTH) + 1;
		int day = calendar.get(Calendar.DAY_OF_MONTH);
		int hour = calendar.get(Calendar.HOUR_OF_DAY);
		
		Month months = this._years.get(year);
	    if( months == null)
		   months = new Month();
		Day days = months.getDays(month);
	    if( days == null)
		   days = new Day();
	    Hour hours = days.getHours(day); 
	    if( hours == null)
	    	hours = new Hour();
	    
	    if(hours.getHourAvailability(hour)) {
	       months._yearlyHours-=1;
	       days._monthlyHours-=1;
	       hours._dailyHours-=1;
	       hours.setHourAvailability(hour, false);
	    }
	}
	
	static class Month {
		private Map<Integer, Day> _months;
		private int _yearlyHours;
		
		public Month(){
			this._months = new  HashMap<Integer,Day>();
			this._yearlyHours = 8784;
		}
		
		public int getYearlyHours() {
			return this._yearlyHours;
		}
		
		public Day getDays(int month){
			if(_months==null)
				this._months = new HashMap<Integer, Day>();
			Day days = this._months.get(month);
			
			return days==null?new Day():days;
		}
		
		static class Day {
			private Map<Integer, Hour> _hours;
			private int _monthlyHours;
			
			public Day(){
				this._hours = new  HashMap<Integer,Hour>();
				this._monthlyHours = 744;
			}
			
			public int getMonthlyResources() {
				return this._monthlyHours;
			}
			
			public Hour getHours(int hour){
				if(this._hours==null)
					this._hours = new HashMap<Integer, Hour>();
				
				Hour hours = this._hours.get(hour);
				
				return hours==null?new Hour():hours;
			}
			
			static class Hour {
				private  Map<Integer, Boolean> _isAvailable;
				private int _dailyHours;
				
				public Hour(){
					this._isAvailable = new  HashMap<Integer, Boolean>();
					this._dailyHours = 24;
				}
				
				public Boolean getHourAvailability(int hour) {
					return this._isAvailable.get(hour);
				}
				int getDailyResourcess() {
					return this._dailyHours;
				}
				
				public void setHourAvailability(int hour, boolean isAvailable) {
					this._isAvailable.put(hour, isAvailable);
				}
			}
		}
	}
}
