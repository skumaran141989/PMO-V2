package pmo.project.repo;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pmo.project.resource.models.abstraction.HumanResource;

//DB partitioned or indexed on Human resource Type
public class HumanResourceRepo {
	  private Map<String, List<HumanResource>> _employees;
	  
	  public HumanResourceRepo() {
		  this._employees = new HashMap<String, List<HumanResource>>();
	  }
		
	  public void save(HumanResource resource) {
		  List<HumanResource> resourceList =  this._employees.get(resource.getClass().toString());
		  
		  if (resourceList==null)
			  resourceList = new ArrayList<HumanResource>();
		  else
			  resourceList.add(resource);
		  
		  this._employees.put(resource.getClass().toString(), resourceList);
	 }
	  
	  public void delete(HumanResource resource ) {
		  
		  List<HumanResource> resourceList =  this._employees.get(resource.getClass().toString());
		  
		  if (resourceList!=null) {
			  resourceList.remove(resource);
			  this._employees.put(resource.getClass().toString(), resourceList);
		  }
	  }
	  
	  public  List<HumanResource> get(String type) {
		  return this._employees.get(type);
	  }
}
