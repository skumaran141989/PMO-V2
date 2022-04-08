package pmo.project.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

//Multilevel partitioned Resource Repo
public class CalendarResourceRepo {
	private Map<Integer, List<Month>> _years;
	private List<String> _totalResources;
	
	public List<Month> getMonths(int year) {
		if(_years==null)
			_years = new HashMap<Integer, List<Month>>();
		List<Month> months = _years.get(year);
		
		return months==null?new ArrayList<Month>():months;
	}
	
	static class Month {
		private Map<Integer, List<Day>> _months;
		private List<String> _yearlyResources;
		
		public List<Day> getDays(int month){
			if(_months==null)
				_months = new HashMap<Integer, List<Day>>();
			List<Day> days = _months.get(month);
			
			return days==null?new ArrayList<Day>():days;
		}
		
		static class Day {
			private Map<Integer, List<Hour>> _hours;
			private List<String> _monthlyResources;
			
			public List<Hour> getHours(int hour){
				if(_hours==null)
					_hours = new HashMap<Integer, List<Hour>>();
				
				List<Hour> hours = _hours.get(hour);
				
				return hours==null?new ArrayList<Hour>():hours;
			}
			
			static class Hour {
				private  Map<Integer, List<String>> _resources;
				private List<String> _dailyResources;
				
				public List<String> getResources(int hour) {
					if(_resources==null)
						_resources = new HashMap<Integer, List<String>>();
					
					List<String> resources = _resources.get(hour);
					
					return resources==null?new ArrayList<String>():resources;
				}
			}
		}
	}
}
