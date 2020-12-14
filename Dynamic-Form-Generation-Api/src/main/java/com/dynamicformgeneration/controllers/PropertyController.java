package com.dynamicformgeneration.controllers;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicformgeneration.entities.Property;
import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PropertyModel;
import com.dynamicformgeneration.services.IPropertyService;

import javassist.NotFoundException;

@RestController
@RequestMapping("api/v1/properties")
public class PropertyController {

	private final IPropertyService propertyService;
	
	@Autowired
	public PropertyController(IPropertyService propertyService) {
		this.propertyService = propertyService;
	}
	
	// Get All Properties
	@GetMapping("/{id}")
	public ResponseEntity<PropertyModel> getById(@PathVariable("id") int id) throws NotFoundException {
		return new ResponseEntity<>(propertyService.getById(id), HttpStatus.OK);
	}
	
	// Get By Id
	@GetMapping
	public ResponseEntity<PageableModel> getAllProperties(Pageable pageable, String filter) {
		return new ResponseEntity<>(propertyService.getAll(pageable, filter), HttpStatus.OK);
	}
	
	// Create Property
	@PostMapping
	public ResponseEntity<PropertyModel> createProperty(@RequestBody PropertyModel property) {
		return new ResponseEntity<>(propertyService.create(property), HttpStatus.CREATED);
	}

	// Update Property
	@PutMapping("/{id}")
	public ResponseEntity<PropertyModel> updateProperty(@PathVariable("id") int id, @RequestBody PropertyModel property) throws NotFoundException {
		return new ResponseEntity<>(propertyService.update(id, property), HttpStatus.OK);
	}
}
