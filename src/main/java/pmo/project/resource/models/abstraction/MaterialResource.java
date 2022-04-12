package pmo.project.resource.models.abstraction;


public abstract class MaterialResource {
	private String _brandName;
	private long _metricValue;
	private double _price;
	private boolean _utilized;
	private boolean _isUsable;
	
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
	
	public boolean consume() {
		this._utilized = !this._utilized;
	    return this._utilized;
	}
}
