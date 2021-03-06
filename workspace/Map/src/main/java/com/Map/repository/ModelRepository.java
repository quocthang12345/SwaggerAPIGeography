package com.Map.repository;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.Map.model.Model;


@Repository
public interface ModelRepository extends MongoRepository<Model, String>{
	Model findOne(String id);
}
