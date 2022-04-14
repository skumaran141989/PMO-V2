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
  
  public String save(Object doc, String url) {
	  if(url==null)
		  url = UUID.randomUUID().toString();
	  this._object.put(url, doc);
	  
	  return url;
  }
  
  public Map<String, Object> getAll() {
	  return this._object;
  }
  
  public Object get(String url) {
	  return this._object.get(url);
  }
  
  public void delete(String url) {
	  this._object.remove(url);
  }
  
}
