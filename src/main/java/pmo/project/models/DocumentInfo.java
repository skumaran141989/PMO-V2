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
		this._docuemtURL = _s3repo.save(object, null);
		this._createDate = new Date();
		this._docId = id;
	}

	public void setDocumentURL(String docuemtURL) {
		this._docuemtURL = docuemtURL;
	}

	public String getDocumentURL() {
		return this._docuemtURL;
	}

	public void setCreateDate(Date createDate) {
		this._createDate = createDate;
	}

	public Date getCreateDate() {
		return this._createDate;
	}

	public void setDocumentId(String docId) {
		this._docId = docId;
	}

	public String getDocumentId() {
		return this._docId;
	}
}
