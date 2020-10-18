package com.Example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.Example.dao.ITypeDAO;
import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Type;
import com.Example.model.TypeDTO;

@Repository(value = "typeDAO")
public class TypeDAO implements ITypeDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private final String COLLECTION_NAME = "type";
	
	@Override
	public List<Type> findAll() {
		return mongoTemplate.findAll(Type.class, COLLECTION_NAME);
	}

	@Override
	public void Insert(Type type) {
		mongoTemplate.insert(type, COLLECTION_NAME);;
	}

	@Override
	public List<Location> findLocation(TypeDTO type) {
		Query query = new Query();
		query.addCriteria(Criteria.where("latitude").is(type.getLat()).and("longitude").is(type.getLng()));
		return mongoTemplate.find(query, Location.class);
	}

	@Override
	public List<Geometry> findGeometry(TypeDTO type) {
		Query query = new Query();
		query.addCriteria(Criteria.where("type").is(type.getType()).andOperator(Criteria.where("coordinates").is(type.getCoordinates())));
		return mongoTemplate.find(query, Geometry.class);
	}

	@Override
	public Type FindOne(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		Type type = mongoTemplate.findOne(query, Type.class);
		return type;
	}

	@Override
	public void Update(Type type) {
		mongoTemplate.save(type);
	}

}
