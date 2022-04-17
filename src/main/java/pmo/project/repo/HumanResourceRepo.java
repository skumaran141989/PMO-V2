package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.resource.models.HumanResource;

//DB partitioned or indexed on Human resource Type
public class HumanResourceRepo {
	private Map<Long, HumanResource> _resource;
	private long _lastId;
  
	public HumanResourceRepo() {
		this._resource = new HashMap<Long, HumanResource>();
		this._lastId = 0;
	}
	
	public long save(HumanResource resource) {
		Long id = resource.getId();
		
		if (id == 0 ) {
			id = ++this._lastId;
		}
	  
		this._resource.put(id, resource);
	  
		return id;
	}
  
	public Map<Long, HumanResource> getAll() {
		return this._resource;
	}
  
	public HumanResource get(long resourceId) {
		return this._resource.get(resourceId);
	}
  
	public void delete(long resourceId) {
		this._resource.remove(resourceId);
	}
}
