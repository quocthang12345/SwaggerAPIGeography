package com.Example.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;

import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;

import com.fasterxml.jackson.annotation.JsonIgnore;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;

@ApiModel(description = "Place")
@Document(collection = "place")
public class Place extends Common {


	
	@ApiModelProperty(dataType = "Location",value = "Location in this place")
	private Location location;
	
	@ApiModelProperty(dataType = "String",value = "Name Address")
	private String address;
	
	@ApiModelProperty(dataType = "String",value = "Name in this place")
	private String name;
	
	@ApiModelProperty(dataType = "Geometry",value = "Geometry")
	private Geometry geometry;
	
	@ApiModelProperty(dataType = "String",value = "Description in this place")
	private String description;
	
	@ApiModelProperty(dataType = "List<String>",value = "types")
	private List<String> types;
	
	@ApiModelProperty(dataType = "String",value = "Url Website")
	private String website;
	
	@ApiModelProperty(dataType = "String",value = "Phone Number")
	private String phone;
	
	@ApiModelProperty(dataType = "Map<String, Object>",value = "Source")
	private Map<String, Object> source;
	
	
	
	public Location getLocation() {
		return location;
	}
	
	public void setLocation(Location location) {
		this.location = location;
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
		List<String> rs = new ArrayList<String>();
		for(String r: types) {
			rs.add(r);
		}
		this.types = rs;
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
		this.source = source;
	}
	
	public Geometry getGeometry() {
		return geometry;
	}
	
	public void setGeometry(Geometry geometry) {
		this.geometry = geometry;
	}
	
	@Override
	public String toString() {
		
		String rs = "\n{\n"+
				"\tname: \"" + this.name + "\",\n" + 
				"\tphone: \"" + this.phone + "\",\n" + 
				"\taddress: \"" + this.address + "\",\n" + 
				"\tdescription: \"" + this.description + "\",\n" +
				"\tlocation: {\n"  
						 + this.location.toString()+ "\n" +
						"\t},\n" +
				"\tgeometry: {\n"  
						+"\t\t" + this.geometry.toString() +
						"\t },\n" +
				"\tsource: {\n\t\t" 
						+ Arrays.toString(prettyMap().toArray()).replace("[", "").replace("]", "") + "\n" + 
				"\t},\n" +
				"\ttypes: "  + prettyString().toString() + ",\n" +
				"\twebsite: \"" + this.website + "\"\n" +
				"}\n";
								
		return rs;
	}
	
	public List<String> prettyString(){
		List<String> rs = new ArrayList<String>();
		for(String s: this.types) {
			rs.add("\"" + s + "\"");
		}
		return rs;
	}
	
	public List<String>  prettyMap() {
		int i = -1;
		List<String> rs = new ArrayList<String>();
		for(Entry e : this.source.entrySet()) {
			rs.add("\"additionalProp"+ ++i + "\": {\"" +e.getKey() + "\" = \"" + e.getValue() +"\"}");
		}
		return rs;
	}
	
    
}
