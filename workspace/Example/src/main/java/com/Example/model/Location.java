package com.Example.model;

import com.fasterxml.jackson.annotation.JsonProperty;

import io.swagger.annotations.ApiModelProperty;

public class Location {
	
	@ApiModelProperty(dataType="Postion", value="position")
	@JsonProperty("position")
	private Position position;

	public Position getPosition() {
		return position;
	}

	public void setPosition(Position position) {
		this.position = position;
	}
	
	@Override
	public String toString() {
		String rs = "position: {\n " + this.position.toString() + "\t\t}"; 
		return rs;
	}
	
}


