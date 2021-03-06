package pmo.project.repo.resource.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Sand")
public class Sand extends MaterialResource {
	private double _granularity;
	
	public Sand(double granularity, String brandName, long metricValue, double price) {
		this._granularity = granularity;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void setGranularity(double granularity) {
		this._granularity = granularity;
	}
	
	public double getGranularity() {
		return this._granularity;
	}
}
