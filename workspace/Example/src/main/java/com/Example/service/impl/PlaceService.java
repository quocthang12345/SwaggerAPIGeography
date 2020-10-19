package com.Example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.dao.IPlaceDAO;
import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Place;
import com.Example.model.PlaceDTO;
import com.Example.service.IPlaceService;

@Service(value = "typeService")
public class PlaceService implements IPlaceService {

	@Autowired
	private IPlaceDAO typeDao;
	
	@Override
	public List<Place> findAll() {
		return typeDao.findAll();
	}

	@Override
	public void Insert(Place type) {
		typeDao.Insert(type);
		
	}

	@Override
	public List<Location> findLocation(PlaceDTO type) {
		return typeDao.findLocation(type);
	}

	@Override
	public List<Geometry> findGeometry(PlaceDTO type) {
		return typeDao.findGeometry(type);
	}

	@Override
	public Place findOneByName(String name) {
		return typeDao.FindOneByName(name);
	}

	@Override
	public void Update(Place type) {
		typeDao.Update(type);
		
	}

	@Override
	public Place findOneById(String id) {
		// TODO Auto-generated method stub
		return typeDao.findOneById(id);
	}

	@Override
	public void Delete(String id) {
		typeDao.Delete(id);
		
	}

	@Override
	public List<Place> findListByLimitAndStart(int start, int limit) {
		// TODO Auto-generated method stub
		return typeDao.findListByStartAndLimit(start, limit);
	}

	
}