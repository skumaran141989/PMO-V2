package pmo.project.repo.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "TaskRequirement")
public class TaskRequirement {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
	private String _type;
	private String _subType;
	private int _quantity;
	private long _projectId;
	private long _taskId;
	private Date _createdAt;
	private Date _updatedAt;
	private String _createdBy;
	private String _updatedBy;
   
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
   
	public void setCreatedDate(Date createdAt) {
		this._createdAt = createdAt;
	}
   
	public Date getCreatedDate() {
		return this._createdAt;
	}
   
	public void setUpdatedDate(Date updatedAt) {
		this._updatedAt = updatedAt;
	}
   
	public Date getUpdatedDate() {
		return this._updatedAt;
	}
   
	public void setCreatedBy(String createdBy) {
		this._createdBy = createdBy;
	}
   
	public String getcreatedBy() {
		return this._createdBy;
	}
   
	public void setUpdatedBy(String updatedBy) {
		this._updatedBy = updatedBy;
	}
   
	public String getUpdatedBy() {
		return this._updatedBy;
	}
}
