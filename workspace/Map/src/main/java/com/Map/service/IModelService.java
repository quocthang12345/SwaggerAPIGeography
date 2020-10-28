package com.Map.service;

import java.util.List;

import com.Map.model.Model;


public interface IModelService {
	List<Model> findAll();
	Model Insert(Model model);
	Model findById(String id);
	Model Update(Model oldModel);
	void Delete(String id);
	List<Model> findByStartAndLimit(int start, int limit);
}
