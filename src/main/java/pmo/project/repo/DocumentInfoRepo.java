package pmo.project.repo;

import java.util.HashMap;
import java.util.Map;

import pmo.project.models.DocumentInfo;

//DB partitioned or indexed on Document ID
public class DocumentInfoRepo {
  private Map<String, DocumentInfo> _documents;
  private static S3Repo _s3repo = new S3Repo();
  
  public DocumentInfoRepo() {
	  _documents = new HashMap<String, DocumentInfo>();
  }
  
  public void save(DocumentInfo doc) {
	  doc.setDocumentURL(_s3repo.save(doc, null));
	  _documents.put(doc.getDocumentId(), doc);
  }
  
  public Map<String, DocumentInfo> getAll() {
	  return _documents;
  }
  
  public DocumentInfo get(String docId) {
	  return _documents.get(docId);
  }
  
  public void delete(String docId) {
	  _documents.remove(docId);
  }
}
