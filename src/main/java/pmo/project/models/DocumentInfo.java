package pmo.project.models;

import java.util.Date;

import pmo.project.repo.S3Repo;

public class DocumentInfo {
	private String _docuemtURL;
	private Date _createDate;
	private String _docId;

	private static S3Repo _s3repo = new S3Repo();
	
	public DocumentInfo(String id, Object object)
	{
		_docuemtURL = _s3repo.save(object, null);
		_createDate = new Date();
		_docId = id;
	}

	public void setDocumentURL(String docuemtURL) {
		_docuemtURL = docuemtURL;
	}

	public String getDocumentURL() {
		return _docuemtURL;
	}

	public void setCreateDate(Date createDate) {
		_createDate = createDate;
	}

	public Date getCreateDate() {
		return _createDate;
	}

	public void setDocumentId(String docId) {
		_docId = docId;
	}

	public String getDocumentId() {
		return _docId;
	}
}
