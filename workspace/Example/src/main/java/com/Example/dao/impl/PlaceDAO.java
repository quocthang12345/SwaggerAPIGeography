package com.Example.dao.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.mongodb.core.MongoTemplate;
import org.springframework.data.mongodb.core.aggregation.SkipOperation;
import org.springframework.data.mongodb.core.query.Criteria;
import org.springframework.data.mongodb.core.query.Query;
import org.springframework.stereotype.Repository;

import com.Example.dao.IPlaceDAO;
import com.Example.model.Geometry;
import com.Example.model.Location;
import com.Example.model.Place;
import com.Example.model.PlaceDTO;

@Repository(value = "typeDAO")
public class PlaceDAO implements IPlaceDAO {

	@Autowired
	private MongoTemplate mongoTemplate;
	
	private final String COLLECTION_NAME = "place";
	
	@Override
	public List<Place> findAll() {
		return mongoTemplate.findAll(Place.class, COLLECTION_NAME);
	}

	@Override
	public void Insert(Place type) {
		mongoTemplate.insert(type, COLLECTION_NAME);;
	}

	@Override
	public List<Location> findLocation(PlaceDTO type) {
		Query query = new Query();
		query.addCriteria(Criteria.where("latitude").is(type.getLat()).and("longitude").is(type.getLng()));
		return mongoTemplate.find(query, Location.class);
	}

	@Override
	public List<Geometry> findGeometry(PlaceDTO type) {
		Query query = new Query();
		query.addCriteria(Criteria.where("type").is(type.getType()).andOperator(Criteria.where("coordinates").is(type.getCoordinates())));
		return mongoTemplate.find(query, Geometry.class);
	}

	@Override
	public Place FindOneByName(String name) {
		Query query = new Query();
		query.addCriteria(Criteria.where("name").is(name));
		Place type = mongoTemplate.findOne(query, Place.class);
		return type;
	}

	@Override
	public void Update(Place type) {
		mongoTemplate.save(type);
	}

	@Override
	public Place findOneById(String id) {
		Query query = new Query();
		query.addCriteria(Criteria.where("_id").is(id));
		Place type = mongoTemplate.findOne(query, Place.class);
		return type;
	}

	@Override
	public void Delete(String id) {
		mongoTemplate.remove(new Query(Criteria.where("_id").is(id)), Place.class);	
	}

	@Override
	public List<Place> findListByStartAndLimit(int start, int limit) {
		Query query = new Query().skip(start).limit(limit);
		List<Place> rs = mongoTemplate.find(query, Place.class);
		return mongoTemplate.find(query, Place.class);
	}

}