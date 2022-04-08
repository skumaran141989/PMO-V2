package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.MaterialResource;

public class Brick extends MaterialResource {
	private String _strengthGrade;
	private String _color;
	
	public Brick(String strengthGrade, String color, String brandName, long metricValue, double price) {
		_strengthGrade = strengthGrade;
		_color= color;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void setStrengthGrade(String strengthGrade) {
		_strengthGrade = strengthGrade;
	}
	
	public String getStrengthGrade() {
		return _strengthGrade;
	}
	
	public void setColor(String color) {
		_color = color;
	}
	
	public String getColor() {
		return _color;
	}
}
