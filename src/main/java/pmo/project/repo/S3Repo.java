package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class S3Repo {
  private Map<String, Object> _object;
  
  //S3 object repo
  public S3Repo() {
	  this._object = new HashMap<String, Object>();
  }
  
  public String save(Object doc, String id) {
	  if(id==null)
		  id = UUID.randomUUID().toString();
	  this._object.put(id, doc);
	  
	  return id;
  }
  
  public Map<String, Object> getAll() {
	  return this._object;
  }
  
  public Object get(String docId) {
	  return this._object.get(docId);
  }
  
  public void delete(String docId) {
	  this._object.remove(docId);
  }
  
}
