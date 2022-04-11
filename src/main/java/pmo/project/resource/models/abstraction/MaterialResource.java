package pmo.project.resource.models.abstraction;


public abstract class MaterialResource {
	private String _brandName;
	private long _metricValue;
	private double _price;
	private boolean _utilized;
	private boolean _isUsable;
	
	public MaterialResource() {
		_utilized = false; 
	}
	
	public void setBrandName(String brandName) {
		_brandName = brandName;
	}
	
	public String getBrandName() {
		return _brandName;
	}
	
	public void setMetric(long metricValue) {
		_metricValue = metricValue;
	}
	
	public long getMetric() {
		return _metricValue;
	}
	
	public void setPrice(double price) {
		_price = price;
	}
	
	public double getPrice() {
		return _price;
	}
	
	public void setUtilized(boolean utilized) {
		_utilized = utilized;
	}
	
	public boolean getUtilized() {
		return _utilized;
	}
	
	public void setIsUsable(boolean isUsable) {
		_isUsable = isUsable;
	}
	
	public boolean getIsUsable() {
		return _isUsable;
	}
	
	public boolean consume() {
		_utilized = !_utilized;
	    return _utilized;
	}
}
