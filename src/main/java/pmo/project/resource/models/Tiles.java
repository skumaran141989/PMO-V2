package pmo.project.resource.models;

import pmo.project.resource.models.abstraction.MaterialResource;

public class Tiles extends MaterialResource {
	private String _grade;
	private String _color;
	
	public Tiles(String grade, String color, String brandName, long metricValue, double price) {
		this._grade = grade;
		this._color = color;
		
		super.setBrandName(brandName);
		super.setMetric(metricValue);
		super.setPrice(price);
	}
	
	public void seGrade(String grade) {
		this._grade = grade;
	}
	
	public String getGrade() {
		return this._grade;
	}
	
	public void setColor(String color) {
		this._color = color;
	}
	
	public String getColor() {
		return this._color;
	}
}
