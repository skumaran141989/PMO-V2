package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.repo.models.Document;

//DB partitioned or indexed on Document ID
public class DocumentRepo {
	private Map<Long, Document> _documents;
	private long _lastId;
  
	public DocumentRepo() {
		this._documents = new HashMap<Long, Document>();
		this._lastId = 0;
	}
  
	public void save(Document doc) {
		Long id = doc.getId();
		
		if (id == 0 ) {
			id = ++this._lastId;
		}
	  
		this._documents.put(id, doc);
	}
  
	public Map<Long, Document> getAll() {
		return this._documents;
	}
  
	public Document get(long docId) {
		return this._documents.get(docId);
	}
  
	public void delete(long docId) {
		this._documents.remove(docId);
	}
}
