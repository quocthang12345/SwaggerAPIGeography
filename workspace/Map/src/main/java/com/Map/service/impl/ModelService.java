package com.Map.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.Map.model.Model;
import com.Map.repository.ModelRepository;
import com.Map.service.IModelService;
import com.google.common.collect.Lists;

@Service(value = "modelService")
public class ModelService implements IModelService {

	@Autowired
	private ModelRepository modelRepo;
	
	@Override
	public List<Model> findAll() {
		return modelRepo.findAll();
	}

	@Override
	public Model Insert(Model model) {
		return modelRepo.save(model);
	}

	@Override
	public Model findById(String id) {
		return modelRepo.findOne(id);
	}

	@Override
	public Model Update(Model oldModel) {
		return modelRepo.save(oldModel);
	}

	@Override
	public void Delete(String id) {
		modelRepo.delete(id);
		
	}

	@Override
	public List<Model> findByStartAndLimit(int start, int limit) {
		Pageable pageable = new PageRequest(start, limit);
	    Page<Model> page = modelRepo.findAll(pageable);
	    return Lists.newArrayList(page);
	}
	


}
