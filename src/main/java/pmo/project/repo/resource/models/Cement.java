package pmo.project.repo.resource.models;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "Cement")
public class Cement extends MaterialResource {
	private double _granularityStrength;
	
	public Cement(double granularityStrength, String brandName, long metricValue, double price) {
		this._granularityStrength = granularityStrength;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void setGranularityStrength(double granularityStrength) {
		this._granularityStrength = granularityStrength;
	}
	
	public double getGranularityStrength() {
		return this._granularityStrength;
	}
}
