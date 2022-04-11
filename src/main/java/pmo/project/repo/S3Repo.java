package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

public class S3Repo {
  private Map<String, Object> _object;
  
  //S3 object repo
  public S3Repo() {
	  _object = new HashMap<String, Object>();
  }
  
  public String save(Object doc, String id) {
	  if(id==null)
		  id = UUID.randomUUID().toString();
	  _object.put(id, doc);
	  
	  return id;
  }
  
  public Map<String, Object> getAll() {
	  return _object;
  }
  
  public Object get(String docId) {
	  return _object.get(docId);
  }
  
  public void delete(String docId) {
	  _object.remove(docId);
  }
  
}
