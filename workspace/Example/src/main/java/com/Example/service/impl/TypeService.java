package com.Example.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.Example.dao.ITypeDAO;
import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Type;
import com.Example.model.TypeDTO;
import com.Example.service.ITypeService;

@Service(value = "typeService")
public class TypeService implements ITypeService {

	@Autowired
	private ITypeDAO typeDao;
	
	@Override
	public List<Type> findAll() {
		return typeDao.findAll();
	}

	@Override
	public void Insert(Type type) {
		typeDao.Insert(type);
		
	}

	@Override
	public List<Location> findLocation(TypeDTO type) {
		return typeDao.findLocation(type);
	}

	@Override
	public List<Geometry> findGeometry(TypeDTO type) {
		return typeDao.findGeometry(type);
	}

	@Override
	public Type findOne(String name) {
		return typeDao.FindOne(name);
	}

	@Override
	public void Update(Type type) {
		typeDao.Update(type);
		
	}

	
}