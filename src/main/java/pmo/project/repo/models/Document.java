package pmo.project.repo.models;

public class Document {
	private String _docuemtURL;
	private long _id;
	private long _referencId;
	private String _refereType;
	
	public void setDocumentURL(String docuemtURL) {
		this._docuemtURL = docuemtURL;
	}

	public String getDocumentURL() {
		return this._docuemtURL;
	}

	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
	}
	
	public void setReferencId(long referencId) {
		this._referencId = referencId;
	}
	
	public long getReferencId() {
		return this._referencId;
	}
	
	public void setReferencType(String refereType) {
		this._refereType = refereType;
	}
	
	public String getReferencType() {
		return this._refereType;
	}
}
