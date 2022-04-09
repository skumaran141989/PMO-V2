package pmo.project.handlers.request;

import java.util.Date;

public class ProjectExecutionRequest {
    private String _projectName;
    private Date _startDate; 
    private Date _dueDate;
    
    public String getProjectName() {
    	return _projectName;
    }
    
    public void setProjectName(String projectName) {
    	_projectName = projectName;
    }
    
    
    public void setStartDate(Date startDate) {
    	_startDate = startDate;
    }
    
    public Date getStartDate() {
    	return _startDate;
    }
    
    public void setDueDate(Date dueDate) {
    	_dueDate = dueDate;
    }
    
    public Date getDueDate() {
    	return _dueDate;
    }
}
