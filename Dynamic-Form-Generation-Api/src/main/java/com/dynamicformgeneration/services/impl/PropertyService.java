package com.dynamicformgeneration.services.impl;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import com.dynamicformgeneration.entities.Property;
import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PropertyModel;
import com.dynamicformgeneration.repository.IPropertyRepository;
import com.dynamicformgeneration.services.IPropertyService;
import com.dynamicformgeneration.util.PropertyConverter;

import javassist.NotFoundException;

@Service
public class PropertyService implements IPropertyService{
	
	private final IPropertyRepository propertyRepository;

	@Autowired
	public PropertyService(IPropertyRepository propertyRepository) {
		this.propertyRepository = propertyRepository;
	}
	
	@Override
	public PageableModel getAll(Pageable pageable, String filter) {

	    if (filter == null || filter.length() > 0 || filter.trim().isEmpty()) {
	      filter = "";
	    } 
	    
	    PageableModel model = new PageableModel();
	    Page<Property> entityProperties = propertyRepository.findAllByNameContainingIgnoreCaseOrTypePropertyContainingIgnoreCase(filter, filter, pageable);
	    List<PropertyModel> properties = new ArrayList<>();
	    for (Property property : entityProperties) {
	    	properties.add(PropertyConverter.toPropertyModel(property));
		}
		
	    model.setItems(properties);
	    model.setTotalRows(entityProperties.getTotalElements());
	    
		return model;
	}

	@Override
	public PropertyModel create(PropertyModel property) {
		Property enity = PropertyConverter.toProperty(property);
		enity.setCreatedDate(new Date());		
		enity.setModifiedDate(new Date());
		return PropertyConverter.toPropertyModel(propertyRepository.save(enity));
	}

	@Override
	public PropertyModel update(int id, PropertyModel property) throws NotFoundException {
		Optional<Property> entity = propertyRepository.findById(id);
		if (entity.isPresent()) {
			Property propertyEntity = entity.get();
			propertyEntity.setModifiedDate(new Date());
			propertyEntity.setMaxLength(property.getMaxLength());
			propertyEntity.setName(property.getName());
			propertyEntity.setOrderProperty(property.getOrderProperty());
			propertyEntity.setTypeProperty(property.getTypeProperty());
			propertyEntity.setPattern(property.getPattern());
			propertyEntity.setRequired(property.isRequired());
			property = PropertyConverter.toPropertyModel(propertyRepository.save(propertyEntity));
		} else {
			throw new  NotFoundException("No encontrado");
		}
			
		return property;
	}

	@Override
	public PropertyModel getById(int id) throws NotFoundException {
		Optional<Property> entity = propertyRepository.findById(id);
		if (!entity.isPresent()) {
			throw new NotFoundException("No encontrado.");
		}
		return PropertyConverter.toPropertyModel(entity.get());
	}
	
}
