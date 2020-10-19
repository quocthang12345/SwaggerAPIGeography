package com.Example.dao;

import java.util.List;

import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Place;
import com.Example.model.PlaceDTO;


public interface IPlaceDAO {
	List<Place> findAll();
	void Insert(Place type);
	List<Location> findLocation(PlaceDTO type);
	List<Geometry> findGeometry(PlaceDTO type);
	Place FindOneByName(String name);
	Place findOneById(String id);
	void Update(Place type);
	void Delete(String id);
	List<Place> findListByStartAndLimit(int start , int limit);
}
