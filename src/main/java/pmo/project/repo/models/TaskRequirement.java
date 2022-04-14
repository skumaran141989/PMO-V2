package pmo.project.repo.models;

public class TaskRequirement {
   private long _id;
   private String _type;
   private String _subType;
   private int _quantity;
   private long _projectId;
   private long _taskId;
   
   public void setType( String type) {
	   this._type =  type;
   }
   
   public String getType() {
	   return this._type;
   }
   
   public void setSubType( String subType) {
	   this._subType =  subType;
   }
   
   public String getSubType() {
	   return this._subType;
   }
   
   public void setQuantity( int quantity) {
	   this._quantity =  quantity;
   }
   
   public int getQuantity() {
	   return this._quantity;
   }
   
   public void setId(long id) {
	   this._id = id;
   }
	
   public long getId() {
	   return this._id;
   }
   
   public void setProjectId(long projectId) {
	   this._projectId = projectId;
   }
	
   public long getProjectId() {
	   return this._projectId;
   }
   
   public void setTaskId(long taskId) {
	   this._taskId = taskId;
   }
	
   public long getTaskId() {
	   return this._taskId;
   }
}
