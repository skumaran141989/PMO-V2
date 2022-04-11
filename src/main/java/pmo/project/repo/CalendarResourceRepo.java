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
		_years = new  HashMap<Integer, Month>();
		_totalResources = new  HashMap<String, List<String>>();
	}
	
	public Map<String, List<String>> getTotalResources() {
		return _totalResources;
	}
	
	public Month getMonths(int year) {
		Month months = _years.get(year);
		
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
		
		Month months = _years.get(year);
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
			_months = new  HashMap<Integer,Day>();
			_yearlyResources = new  HashMap<String,List<String>>();
		}
		
		public Map<String, List<String>> getYearlyResources() {
			return _yearlyResources;
		}
		
		public Day getDays(int month){
			if(_months==null)
				_months = new HashMap<Integer, Day>();
			Day days = _months.get(month);
			
			return days==null?new Day():days;
		}
		
		static class Day {
			private Map<Integer, Hour> _hours;
			private Map<String, List<String>> _monthlyResources;
			
			public Day(){
				_hours = new  HashMap<Integer,Hour>();
				_monthlyResources = new  HashMap<String, List<String>>();
			}
			
			public Map<String, List<String>> getMonthlyResources() {
				return _monthlyResources;
			}
			
			public Hour getHours(int hour){
				if(_hours==null)
					_hours = new HashMap<Integer, Hour>();
				
				Hour hours = _hours.get(hour);
				
				return hours==null?new Hour():hours;
			}
			
			static class Hour {
				private  Map<Integer, Map<String, List<String>>> _resources;
				private Map<String, List<String>> _dailyResources;
				
				public Hour(){
					_resources = new  HashMap<Integer, Map<String, List<String>>>();
					_dailyResources = new  HashMap<String,List<String>>();
				}
				
				public Map<String, List<String>> getResources(int hour) {
					Map<String, List<String>> resources = _resources.get(hour);
					
					return resources==null?new HashMap<String,List<String>>():resources;
				}
				
				public Map<String, List<String>> getDailyResourcess() {
					return _dailyResources;
				}
				
				public void setResource(int hour, String type, String resource) {
					Map<String, List<String>> hourlyresources = _resources.get(hour);
					hourlyresources = hourlyresources==null?new HashMap<String,List<String>>():hourlyresources;
					List<String> resources = hourlyresources.get(type);
				    if( resources == null)
				    	resources = new ArrayList<String>();
				    resources.add(resource);
				    hourlyresources.put(type, resources);
				    _resources.put(hour, hourlyresources);
				}
			}
		}
	}
}
