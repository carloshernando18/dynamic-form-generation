package com.dynamicformgeneration.services;

import org.springframework.data.domain.Pageable;

import com.dynamicformgeneration.entities.Property;
import com.dynamicformgeneration.models.PageableModel;
import com.dynamicformgeneration.models.PropertyModel;

import javassist.NotFoundException;

public interface IPropertyService {

	PageableModel getAll(Pageable pageable, String filter);

	PropertyModel create(PropertyModel property);

	PropertyModel update(int id, PropertyModel property) throws NotFoundException;

	PropertyModel getById(int id) throws NotFoundException;
	
}
