package com.Example.converter;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Position;
import com.Example.model.Type;
import com.Example.model.TypeDTO;
import com.Example.service.ITypeService;

@Component
public class TypeConvert {
	
	@Autowired
	private ITypeService typeService;
	
	public Type toEntity(TypeDTO type) {
		Type newType = new Type();
		Location location = new Location();
		Position position = new Position();
		position.setLat(type.getLat());
		position.setLng(type.getLng());
		location.setPosition(position);
		Geometry geometry = new Geometry();
		geometry.setCoordinates(type.getCoordinates());
		geometry.setType(type.getType());
		
		if(type.getId() != null) {
			Type oldtype = typeService.findOne(type.getName());
			oldtype.setAddress(type.getAddress());
			oldtype.setDescription(type.getDescription());
			oldtype.setName(type.getName());
			oldtype.setPhone(type.getPhone());
			oldtype.setSource(type.getSource());
			oldtype.setWebsite(type.getWebsite());
			oldtype.setLocation(location);
			oldtype.setGeometry(geometry);
			return oldtype;
		}
		
		newType.setAddress(type.getAddress());
		newType.setDescription(type.getDescription());
		newType.setName(type.getName());
		newType.setPhone(type.getPhone());
		newType.setSource(type.getSource());
		newType.setWebsite(type.getWebsite());
		newType.setLocation(location);
		newType.setGeometry(geometry);
		newType.setTypes(type.getTypes());
		
		return newType;
	}

}