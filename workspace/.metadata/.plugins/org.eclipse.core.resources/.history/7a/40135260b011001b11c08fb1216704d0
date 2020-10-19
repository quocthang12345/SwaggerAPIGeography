package com.Example.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;



public class Geometry {
	

	@ApiModelProperty(dataType = "String",value = "type")
	@JsonProperty("type")
	private String type;
	
	@ApiModelProperty(dataType = "List<CoordinatePoint>",value = "coordinates")
	@JsonProperty("coordinates")
	private List<CoordinatePoint> coordinates = new ArrayList<>();
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<CoordinatePoint> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<CoordinatePoint> coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public String toString() {
		String rs = "\"type\": \"" + this.type + "\" ,\n" + 
				"\t\t\"coordinates\": " + this.coordinates.toString()+ "\n" ;
				
		return rs;
	}



	
	
	

}
