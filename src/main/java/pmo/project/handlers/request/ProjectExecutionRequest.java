package pmo.project.handlers.request;

import java.util.Date;

public class ProjectExecutionRequest {
    private String _projectName;
    private Date _startDate; 
    private Date _dueDate;
    
    public String getProjectName() {
    	return this._projectName;
    }
    
    public void setProjectName(String projectName) {
    	this._projectName = projectName;
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
