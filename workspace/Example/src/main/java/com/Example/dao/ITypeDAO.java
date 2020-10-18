package com.Example.dao;

import java.util.List;

import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Type;
import com.Example.model.TypeDTO;


public interface ITypeDAO {
	List<Type> findAll();
	void Insert(Type type);
	List<Location> findLocation(TypeDTO type);
	List<Geometry> findGeometry(TypeDTO type);
	Type FindOne(String name);
	void Update(Type type);
}