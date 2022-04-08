package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.MaterialResource;

public class Cement extends MaterialResource {
	private double _granularityStrength;
	
	public Cement(double granularityStrength, String brandName, long metricValue, double price) {
		_granularityStrength = granularityStrength;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void setGranularityStrength(double granularityStrength) {
		_granularityStrength = granularityStrength;
	}
	
	public double getGranularityStrength() {
		return _granularityStrength;
	}
}
