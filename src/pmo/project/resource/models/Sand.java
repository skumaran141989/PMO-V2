package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.MaterialResource;

public class Sand extends MaterialResource {
	private double _granularity;
	
	public Sand(double granularity, String brandName, long metricValue, double price) {
		_granularity = granularity;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void setGranularity(double granularity) {
		_granularity = granularity;
	}
	
	public double getGranularity() {
		return _granularity;
	}
}
