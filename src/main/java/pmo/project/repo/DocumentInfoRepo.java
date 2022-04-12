package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.DocumentInfo;

//DB partitioned or indexed on Document ID
public class DocumentInfoRepo {
  private Map<String, DocumentInfo> _documents;
  private static S3Repo _s3repo = new S3Repo();
  
  public DocumentInfoRepo() {
	  this._documents = new HashMap<String, DocumentInfo>();
  }
  
  public void save(DocumentInfo doc) {
	  doc.setDocumentURL(_s3repo.save(doc, null));
	  this._documents.put(doc.getDocumentId(), doc);
  }
  
  public Map<String, DocumentInfo> getAll() {
	  return this._documents;
  }
  
  public DocumentInfo get(String docId) {
	  return this._documents.get(docId);
  }
  
  public void delete(String docId) {
	  this._documents.remove(docId);
  }
}
