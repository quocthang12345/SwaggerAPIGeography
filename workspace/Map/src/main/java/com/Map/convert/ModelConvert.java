package com.Map.convert;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.Map.model.Geometry;
import com.Map.model.Location;
import com.Map.model.Model;
import com.Map.model.ModelDTO;
import com.Map.service.IModelService;


@Component
public class ModelConvert {
	
	@Autowired
	private IModelService modelService;
	
	public Model toEntity(ModelDTO model) {
		Model newModel = new Model();
		Location location = new Location();
		Geometry geometry = new Geometry();
		location.setLat(model.getLat());
		location.setLng(model.getLng());
		geometry.setCoordinates(model.getCoordinates());
		geometry.setType(model.getType());
		
		if(model.getId() != null) {
			Model oldModel = modelService.findById(model.getId());
			oldModel.setAddress(model.getAddress());
			oldModel.setDescription(model.getDescription());
			oldModel.setName(model.getName());
			oldModel.setPhone(model.getPhone());
			oldModel.setSource(model.getSource());
			oldModel.setWebsite(model.getWebsite());
			oldModel.setLocation(location);
			oldModel.setGeometry(geometry);
			return oldModel;
		}
		
		newModel.setAddress(model.getAddress());
		newModel.setDescription(model.getDescription());
		newModel.setName(model.getName());
		newModel.setPhone(model.getPhone());
		newModel.setSource(model.getSource());
		newModel.setWebsite(model.getWebsite());
		newModel.setLocation(location);
		newModel.setGeometry(geometry);
		newModel.setTypes(model.getTypes());
		
		return newModel;
	}

}
