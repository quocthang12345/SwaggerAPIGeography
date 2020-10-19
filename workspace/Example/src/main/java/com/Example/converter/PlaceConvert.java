package com.Example.converter;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Place;
import com.Example.model.PlaceDTO;
import com.Example.service.IPlaceService;

@Component
public class PlaceConvert {
	
	@Autowired
	private IPlaceService typeService;
	
	public Place toEntity(PlaceDTO type) {
		Place newPlace = new Place();
		Location location = new Location();
		Geometry geometry = new Geometry();
		location.setLat(type.getLat());
		location.setLng(type.getLng());
		geometry.setCoordinates(type.getCoordinates());
		geometry.setType(type.getType());
		
		if(type.getId() != null) {
			Place oldPlace = typeService.findOneById(type.getId());
			oldPlace.setAddress(type.getAddress());
			oldPlace.setDescription(type.getDescription());
			oldPlace.setName(type.getName());
			oldPlace.setPhone(type.getPhone());
			oldPlace.setSource(type.getSource());
			oldPlace.setWebsite(type.getWebsite());
			oldPlace.setLocation(location);
			oldPlace.setGeometry(geometry);
			return oldPlace;
		}
		
		newPlace.setAddress(type.getAddress());
		newPlace.setDescription(type.getDescription());
		newPlace.setName(type.getName());
		newPlace.setPhone(type.getPhone());
		newPlace.setSource(type.getSource());
		newPlace.setWebsite(type.getWebsite());
		newPlace.setLocation(location);
		newPlace.setGeometry(geometry);
		newPlace.setTypes(type.getTypes());
		
		return newPlace;
	}

}
