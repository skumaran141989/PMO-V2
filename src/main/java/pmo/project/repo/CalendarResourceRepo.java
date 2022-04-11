package pmo.project.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Multilevel partitioned Resource Repo
public class CalendarResourceRepo {
	private Map<Integer, List<Month>> _years;
	private Map<String, String> _totalResources;
	
	public CalendarResourceRepo(){
		_years = new  HashMap<Integer, List<Month>>();
		_totalResources = new  HashMap<String, String>();
	}
	
	public List<Month> getMonths(int year) {
		if(_years==null)
			_years = new HashMap<Integer, List<Month>>();
		List<Month> months = _years.get(year);
		
		return months==null?new ArrayList<Month>():months;
	}
	
	static class Month {
		private Map<Integer, List<Day>> _months;
		private Map<String, String> _yearlyResources;
		
		public Month(){
			_months = new  HashMap<Integer, List<Day>>();
			_yearlyResources = new  HashMap<String, String>();
		}
		
		public List<Day> getDays(int month){
			if(_months==null)
				_months = new HashMap<Integer, List<Day>>();
			List<Day> days = _months.get(month);
			
			return days==null?new ArrayList<Day>():days;
		}
		
		static class Day {
			private Map<Integer, List<Hour>> _hours;
			private Map<String, String> _monthlyResources;
			
			public Day(){
				_hours = new  HashMap<Integer, List<Hour>>();
				_monthlyResources = new  HashMap<String, String>();
			}
			
			public List<Hour> getHours(int hour){
				if(_hours==null)
					_hours = new HashMap<Integer, List<Hour>>();
				
				List<Hour> hours = _hours.get(hour);
				
				return hours==null?new ArrayList<Hour>():hours;
			}
			
			static class Hour {
				private  Map<Integer, Map<String, String>> _resources;
				private Map<String, String> _dailyResources;
				
				public Hour(){
					_resources = new  HashMap<Integer, Map<String, String>>();
					_dailyResources = new  HashMap<String, String>();
				}
				
				public Map<String, String> getResources(int hour) {
					if(_resources==null)
						_resources = new HashMap<Integer, Map<String, String>>();
					
					Map<String, String> resources = _resources.get(hour);
					
					return resources==null?new HashMap<String, String>():resources;
				}
			}
		}
	}
}
