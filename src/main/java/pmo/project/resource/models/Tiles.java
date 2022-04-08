package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.MaterialResource;

public class Tiles extends MaterialResource {
	private String _grade;
	private String _color;
	
	public Tiles(String grade, String color, String brandName, long metricValue, double price) {
		_grade = grade;
		_color = color;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void seGrade(String grade) {
		_grade = grade;
	}
	
	public String getGrade() {
		return _grade;
	}
	
	public void setColor(String color) {
		_color = color;
	}
	
	public String getColor() {
		return _color;
	}
}
