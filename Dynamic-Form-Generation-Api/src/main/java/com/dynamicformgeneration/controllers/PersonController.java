package com.dynamicformgeneration.controllers;

import java.util.List;

import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PersonValueModel;
import com.dynamicformgeneration.services.IPersonService;

import javassist.NotFoundException;

@CrossOrigin(origins = "*")
@RestController
@RequestMapping("api/v1/persons")
public class PersonController {
	
	private final IPersonService personService;
	
	public PersonController(IPersonService personService) {
		this.personService = personService;
	}

	// Get All Properties
	@GetMapping
	public ResponseEntity<PageableModel> getAllProperties(Pageable pageable, String filter) {
		return new ResponseEntity<>(personService.getAll(pageable, filter), HttpStatus.OK);
	}
	
	// Create Property
	@PostMapping
	public ResponseEntity<List<PersonValueModel>> createPerson(@RequestBody List<PersonValueModel> values) {
		return new ResponseEntity<>(personService.create(values), HttpStatus.CREATED);
	}

	// Get By Id
	@GetMapping("/{id}")
	public ResponseEntity<List<PersonValueModel>> getById(@PathVariable("id") int personId) throws NotFoundException {
		return new ResponseEntity<>(personService.getByPersonId(personId), HttpStatus.OK);
	}

}
