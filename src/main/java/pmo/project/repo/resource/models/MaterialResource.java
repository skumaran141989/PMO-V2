package pmo.project.repo.resource.models;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Inheritance;
import javax.persistence.InheritanceType;
import javax.persistence.Table;

@Entity
@Table(name = "MaterialResource")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class MaterialResource {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private long _id;
	
	private String _brandName;
	private long _metricValue;
	private double _price;
	private boolean _utilized;
	private boolean _isUsable;
	private String _type;
	private Date _createdAt;
	private Date _updatedAt;
	private String _createdBy;
	private String _updatedBy;
	
	public MaterialResource() {
		this._utilized = false; 
	}
	
	public void setBrandName(String brandName) {
		this._brandName = brandName;
	}
	
	public String getBrandName() {
		return this._brandName;
	}
	
	public void setMetric(long metricValue) {
		this._metricValue = metricValue;
	}
	
	public long getMetric() {
		return this._metricValue;
	}
	
	public void setPrice(double price) {
		this._price = price;
	}
	
	public double getPrice() {
		return this._price;
	}
	
	public void setType(String type) {
		this._type = type;
	}
	
	public String getType() {
		return this._type;
	}
	
	public void setUtilized(boolean utilized) {
		this._utilized = utilized;
	}
	
	public boolean getUtilized() {
		return this._utilized;
	}
	
	public void setIsUsable(boolean isUsable) {
		this._isUsable = isUsable;
	}
	
	public boolean getIsUsable() {
		return this._isUsable;
	}
	
	public void setId(long id) {
		this._id = id;
	}
	
	public long getId() {
		return this._id;
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
