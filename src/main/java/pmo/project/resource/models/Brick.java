package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.MaterialResource;

public class Brick extends MaterialResource {
	private String _strengthGrade;
	private String _color;
	
	public Brick(String strengthGrade, String color, String brandName, long metricValue, double price) {
		this._strengthGrade = strengthGrade;
		this._color= color;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void setStrengthGrade(String strengthGrade) {
		this._strengthGrade = strengthGrade;
	}
	
	public String getStrengthGrade() {
		return this._strengthGrade;
	}
	
	public void setColor(String color) {
		this._color = color;
	}
	
	public String getColor() {
		return this._color;
	}
}
