package com.Example.model;

public class CoordinatePoint {
	
	private Double coordinatePointX;
	private Double coordinatePointY;
	public Double getCoordinatePointX() {
		return coordinatePointX;
	}
	public void setCoordinatePointX(Double coordinatePointX) {
		this.coordinatePointX = coordinatePointX;
	}
	public Double getCoordinatePointY() {
		return coordinatePointY;
	}
	public void setCoordinatePointY(Double coordinatePointY) {
		this.coordinatePointY = coordinatePointY;
	}
	
	@Override
	public String toString() {
		String rs = "[" + this.coordinatePointX + ", " + this.coordinatePointY + "]";
		return rs;
	}
	
	
}