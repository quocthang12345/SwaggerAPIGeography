package com.Example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Position {
	@ApiModelProperty(dataType = "string",value = "longitude")
	@JsonProperty("lng")
	private Double lng;
	
	@ApiModelProperty(dataType = "string",value = "latitude")
	@JsonProperty("lat")
	private Double lat;

	public Double getLng() {
		return lng;
	}

	public void setLng(Double lng) {
		this.lng = lng;
	}

	public Double getLat() {
		return lat;
	}

	public void setLat(Double lat) {
		this.lat = lat;
	}
	
	@Override
	public String toString() {
		String rs = "\t\t\tlatitude: " + this.lat + ",\n" + "\t\t\tlongitude: " + this.lng + "\n";
		return rs;
	}
}
