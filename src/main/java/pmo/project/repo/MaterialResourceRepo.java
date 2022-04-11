package pmo.project.repo;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import pmo.project.resource.models.abstraction.MaterialResource;


//DB partitioned or indexed on Material resource Type
public class MaterialResourceRepo {
	 private Map<String, List<MaterialResource>> _materials;
	 
	  public MaterialResourceRepo() {
		  _materials = new HashMap<String, List<MaterialResource>>();
	  }
	
	  public void save(MaterialResource resource) {
		  List<MaterialResource> resourceList = _materials.get(resource.getClass().toString());
		  
		  if (resourceList==null)
			  resourceList = new ArrayList<MaterialResource>();
		  else
			  resourceList.add(resource);
		  
		  _materials.put(resource.getClass().toString(), resourceList);
	  }
	  
	  public void delete(MaterialResource resource ) {
		  
		  List<MaterialResource> resourceList =  _materials.get(resource.getClass().toString());
		  
		  if (resourceList!=null) {
			  resourceList.remove(resource);
			  _materials.put(resource.getClass().toString(), resourceList);
		  }
	  }
	  
	  public  List<MaterialResource> get(String type) {
		  return _materials.get(type);
	  }
}
