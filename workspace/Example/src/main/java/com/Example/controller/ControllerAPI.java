package com.Example.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Example.converter.TypeConvert;
import com.Example.model.Type;
import com.Example.model.TypeDTO;
import com.Example.service.ITypeService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "This page edit Type Model")
public class ControllerAPI {

	@Autowired
	private ITypeService typeService;
	@Autowired
	private TypeConvert typeConverter;

	@ApiResponses(value = { @ApiResponse(code = 300, message = "This is Error Page 300", response = Type.class),
			@ApiResponse(code = 500, message = "This is Error Page 500", response = Type.class) })

	@GetMapping(value = "/edit")
	public String getPage() {
		return typeService.findAll().toString();
	}

	@ApiOperation(value = "Add Type Model")
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus addPage(@ApiParam(value = "Type Model", required = true)@RequestBody TypeDTO type) {
		 Type typeEntity = typeConverter.toEntity(type); 
		 typeService.Insert(typeEntity);
		 return HttpStatus.OK;
	}

	@PutMapping(value = "/edit/${name}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpStatus editPage(@RequestBody TypeDTO type) {
		Type typeEntity = typeConverter.toEntity(type); 
		typeService.Update(typeEntity);
		return HttpStatus.OK;
	}

	@DeleteMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public void deletePage(@PathVariable Long id) {

	}

}
