package com.Example.model;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import com.fasterxml.jackson.annotation.JsonIgnore;

public class TypeDTO {
	@JsonIgnore
	private String id;
	private Double lng;
	private Double lat;
	private String address;
	private String name;
	private String type;
	private List<CoordinatePoint> coordinates = new ArrayList<>();
	private String description;
	private List<String> types;
	private String website;
	private String phone;
	private Map<String, Object> source;
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	
	
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
	public String getAddress() {
		return address;
	}
	public void setAddress(String address) {
		this.address = address;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	
	
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
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public List<String> getTypes() {
		return types;
	}
	public void setTypes(List<String> types) {
		List<String> result = new ArrayList<String>();
		for(String r : types) {
			result.add(r);
		}
		this.types = result;
	}
	public String getWebsite() {
		return website;
	}
	public void setWebsite(String website) {
		this.website = website;
	}
	public String getPhone() {
		return phone;
	}
	public void setPhone(String phone) {
		this.phone = phone;
	}
	public Map<String, Object> getSource() {
		return source;
	}
	public void setSource(Map<String, Object> source) {
		Map<String, Object> result = new HashMap<String, Object>();
		for(String key : source.keySet()) {
			result.put(key, source.get(key));
		}
		this.source = result;
	}
	
	
	
}