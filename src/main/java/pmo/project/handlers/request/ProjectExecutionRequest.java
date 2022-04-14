package pmo.project.handlers.request;

import java.util.Date;

public class ProjectExecutionRequest {
    private long _projectId;
    private Date _startDate; 
    private Date _dueDate;
    
    public long getProjectId() {
    	return this._projectId;
    }
    
    public void setProjectId(long projectId) {
    	this._projectId = projectId;
    }
    
    public void setStartDate(Date startDate) {
    	this._startDate = startDate;
    }
    
    public Date getStartDate() {
    	return this._startDate;
    }
    
    public void setDueDate(Date dueDate) {
    	this._dueDate = dueDate;
    }
    
    public Date getDueDate() {
    	return this._dueDate;
    }
}
