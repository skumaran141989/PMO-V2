package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.resource.models.MaterialResource;

//DB partitioned or indexed on Material resource Type
public class MaterialResourceRepo {
	  private Map<Long, MaterialResource> _resource;
	  private long _lastId;
	  
	  public MaterialResourceRepo() {
		  this._resource = new HashMap<Long, MaterialResource>();
		  this._lastId=0;
	  }
		
	  public long save(MaterialResource resource) {
		  Long id = resource.getId();
		  if(id==0)
			  id=++this._lastId;
		  
		  this._resource.put(id, resource);
		  return id;
	  }
	  
	  public Map<Long, MaterialResource> getAll() {
		  return this._resource;
	  }
	  
	  public MaterialResource get(long resourceId) {
		  return this._resource.get(resourceId);
	  }
	  
	  public void delete(long resourceId) {
		  this._resource.remove(resourceId);
	  }
}
