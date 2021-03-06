package com.Map.model;
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
	private List<Object> coordinates = new ArrayList<>();
	
	
	
	public String getType() {
		return type;
	}

	public void setType(String type) {
		this.type = type;
	}

	public List<Object> getCoordinates() {
		return coordinates;
	}

	public void setCoordinates(List<Object> coordinates) {
		this.coordinates = coordinates;
	}
	
	@Override
	public String toString() {
		String rs = "type: \"" + this.type + "\" ,\n" + 
				"\t\tcoordinates: " + this.coordinates.toString().replace("{", "[").replace("}", "]").replace("coordinatePointX=", "").replace("coordinatePointY=", "").replace("x=","").replace("y=", "") + "\n" ;				
		return rs;
	}
	


}
