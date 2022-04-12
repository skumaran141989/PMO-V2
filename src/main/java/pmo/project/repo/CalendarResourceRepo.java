package pmo.project.repo;

import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pmo.project.repo.CalendarResourceRepo.Month.Day;
import pmo.project.repo.CalendarResourceRepo.Month.Day.Hour;

//Multilevel partitioned Resource Repo
public class CalendarResourceRepo {
	private Map<Integer, Month> _years;
	private Map<String, List<String>> _totalResources;
	
	public CalendarResourceRepo(){
		this._years = new  HashMap<Integer, Month>();
		this._totalResources = new  HashMap<String, List<String>>();
	}
	
	public Map<String, List<String>> getTotalResources() {
		return this._totalResources;
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
	
		List<String> resources = _totalResources.get(type);
	    if( resources == null)
	    	resources = new ArrayList<String>();
	    resources.add(resource);
		
		Month months = this._years.get(year);
	    if( months == null)
		   months = new Month();
	    resources = months._yearlyResources.get(type);
	    if( resources == null)
	    	resources = new ArrayList<String>();
	    resources.add(resource);
	    months._yearlyResources.put(type, resources);
	    
		Day days = months.getDays(month);
	    if( days == null)
		   days = new Day();
	    resources =  days._monthlyResources.get(type);
	    if( resources == null)
	    	resources = new ArrayList<String>();
	    resources.add(resource);
	    days._monthlyResources.put(type, resources);
	    
	    Hour hours = days.getHours(day); 
	    if( hours == null)
	    	hours = new Hour();
	    resources =  hours._dailyResources.get(type);
	    if( resources == null)
	    	resources = new ArrayList<String>();
	    resources.add(resource);
	    hours._dailyResources.put(type, resources);
	    hours.setResource(hour, type, resource);
	}
	
	static class Month {
		private Map<Integer, Day> _months;
		private Map<String, List<String>> _yearlyResources;
		
		public Month(){
			this._months = new  HashMap<Integer,Day>();
			this._yearlyResources = new  HashMap<String,List<String>>();
		}
		
		public Map<String, List<String>> getYearlyResources() {
			return this._yearlyResources;
		}
		
		public Day getDays(int month){
			if(this._months==null)
				this._months = new HashMap<Integer, Day>();
			Day days = this._months.get(month);
			
			return days==null?new Day():days;
		}
		
		static class Day {
			private Map<Integer, Hour> _hours;
			private Map<String, List<String>> _monthlyResources;
			
			public Day(){
				this._hours = new  HashMap<Integer,Hour>();
				this._monthlyResources = new  HashMap<String, List<String>>();
			}
			
			public Map<String, List<String>> getMonthlyResources() {
				return this._monthlyResources;
			}
			
			public Hour getHours(int hour){
				if(this._hours==null)
					this._hours = new HashMap<Integer, Hour>();
				
				Hour hours = this._hours.get(hour);
				
				return hours==null?new Hour():hours;
			}
			
			static class Hour {
				private  Map<Integer, Map<String, List<String>>> _resources;
				private Map<String, List<String>> _dailyResources;
				
				public Hour(){
					this._resources = new  HashMap<Integer, Map<String, List<String>>>();
					this._dailyResources = new  HashMap<String,List<String>>();
				}
				
				public Map<String, List<String>> getResources(int hour) {
					Map<String, List<String>> resources = this._resources.get(hour);
					
					return resources==null?new HashMap<String,List<String>>():resources;
				}
				
				public Map<String, List<String>> getDailyResourcess() {
					return this._dailyResources;
				}
				
				public void setResource(int hour, String type, String resource) {
					Map<String, List<String>> hourlyresources = this._resources.get(hour);
					hourlyresources = hourlyresources==null?new HashMap<String,List<String>>():hourlyresources;
					List<String> resources = hourlyresources.get(type);
				    if( resources == null)
				    	resources = new ArrayList<String>();
				    resources.add(resource);
				    hourlyresources.put(type, resources);
				    this._resources.put(hour, hourlyresources);
				}
			}
		}
	}
}
