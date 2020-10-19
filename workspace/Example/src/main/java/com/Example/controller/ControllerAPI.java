package com.Example.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import com.Example.converter.PlaceConvert;
import com.Example.model.Place;
import com.Example.model.PlaceDTO;
import com.Example.service.IPlaceService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

@RestController
@Api(tags = "This page is API Place")
public class ControllerAPI {

	@Autowired
	private IPlaceService placeService;
	@Autowired
	private PlaceConvert placeConverter;

	@ApiResponses(value = { @ApiResponse(code = 300, message = "This is Error Page 300", response = Place.class),
			@ApiResponse(code = 500, message = "This is Error Page 500", response = Place.class) ,
			@ApiResponse(code = 200, message = "Successfully", response = Place.class) })

	@GetMapping(value = "/edit")
	public String getPage() {
		return placeService.findAll().toString();
	}
	
	@GetMapping(value = "/edit/{start}/{limit}")
	public String PaginationPage(@RequestParam("start") int start,@RequestParam("limit") int limit) {
		return placeService.findListByLimitAndStart(start, limit).toString();
		
	}
	@ApiOperation(value = "Add Place Model")
	@PostMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus addPage(@ApiParam(value = "Place Model", required = true)@RequestBody PlaceDTO place) {
		 Place placeEntity = placeConverter.toEntity(place); 
		 placeService.Insert(placeEntity);
		 return HttpStatus.OK;
	}

	@PutMapping(value = "/edit", consumes = MediaType.APPLICATION_JSON_VALUE)
	@ResponseBody
	public HttpStatus editPage(@RequestBody PlaceDTO type) {
		Place placeEntity = placeConverter.toEntity(type); 
		placeService.Update(placeEntity);
		return HttpStatus.OK;
	}

	@DeleteMapping(value = "/edit/{id}", consumes = MediaType.APPLICATION_JSON_VALUE)
	public HttpStatus deletePage(@RequestBody String id) {
		placeService.Delete(id.replace("\"", ""));
		return HttpStatus.OK;
	}

}
